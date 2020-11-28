package com.yupodong.heromancer;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.yupodong.heromancer.object.Archer;
import com.yupodong.heromancer.object.Knight;
import com.yupodong.heromancer.object.MagicKnight;
import com.yupodong.heromancer.object.Mob;
import com.yupodong.heromancer.object.Player;
import com.yupodong.heromancer.object.Warrior;
import com.yupodong.heromancer.object.data.Damage;
import com.yupodong.heromancer.object.data.Heal;

import java.util.Random;

public class BattleHandler {
    //기본리소스
    private Mob[] enemy;
    private  Player[] friendly;
    private ChatUI chatui;
    private BattleWindow window;
    private Random random=new Random();

    //상태
    private int turn=0;//0~maxturn-1까지만
    private int maxturn;
    private int dieplayer=0;
    private int diemob=0;
    private Handler handler=new Handler();
    private int totalTurn=1;


    private int mobnum=4;
    private int playernum=4;
    public BattleHandler(LinearLayout chat, ConstraintLayout window, Context con, clickevent c){
        random.setSeed(System.currentTimeMillis());
        //이곳에서 맵같은걸로 초기화
        //임시제작
        if(mobnum<playernum) maxturn=playernum; else maxturn=mobnum;
        enemy=new Mob[mobnum];
        friendly= new Player[playernum];


        c.setHandler(this);
        set_per();


        this.chatui=new ChatUI(chat,con,c);
        this.window=new BattleWindow(window,con,playernum,mobnum,c);

        for (int i=0;i<playernum;i++){
            switch (random.nextInt(2)){
                case 0:
                    enemy[i]= new Warrior();
                    //이미지 변경
                    break;
                case 1:
                    enemy[i]= new Archer();
                    break;
                case 2:
                    enemy[i]= new Knight();
                    break;
            }
            friendly[i]=new MagicKnight();
        }

    }



    private int[] attack_per={1,1,1,1};//플레이어가 공격당할 확률
    private void set_per(){//플레이어가 타겟이될 확율 셋팅
        int per=100/(playernum-dieplayer);
        int mul=1;

        for (int i=0;i<playernum;i++){
            if(attack_per[i]!=0){
                attack_per[i]=mul*per;
                if(per==33&&mul==3) attack_per[i]++;
                mul++;
            }
        }
    }



    //스레드가 끝나고 일정시간이 있어야 적용되서 이러는 것으로 추정 (스레드 안에서 스레드를 한번서 실행 시키는 것으로 해결)
    //재귀지만 길어야 4번 실행이기에 문제 없음
    public void next_turn()  {
        //다음턴으로
        if (turn <= mobnum && enemy[turn].isLive()) {//공격자가 있는지 확인

            int target=random.nextInt(99);
            for (int i=0; i<4;i++){
                if(target<attack_per[i]){
                    target=i;
                    break;
                }
            }
            if(target>3) return; //혹시 타겟 설정이 안되었을 경우를 대비한 것


            PlayerAttack(target);
            window.test(Integer.toString(attack_per[0])+" "+Integer.toString(attack_per[1])+" "+Integer.toString(attack_per[2])+" "+Integer.toString(attack_per[3]));

        }
        turn = (turn + 1) % maxturn;
        //(디)버프 처리
        totalTurn++;
        for (int i=0;i<4;i++){
            if(enemy[i].isLive()){
                enemy[i].buff_check(totalTurn);
            }
            if(friendly[i].isLive()){
                friendly[i].buff_check(totalTurn);
            }
        }

        if (turn>=playernum || !friendly[turn].isLive()) {//turn이 플레이어 수보다 같고고높거나, 현재 차례인 애가 살아있지 않을 시
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    next_turn();
                }
            },750);
        }
        else {
            window.setnowturn(turn,true);
            chatui.draw_startmenu();
        }

    }




    //turn번째가 공격할 것이기에 공격자가 누구인지 지정하지 않아도 된다
    public void PlayerAttack(int target){
        /*플레이어를 공격*/
        friendly[target].Hurt(enemy[turn].Attack());
        window.setplayerHP(target,friendly[target].get_HPper());

        if(!friendly[target].isLive()){
            playerdie(target);
        }
    }

    private void playerdie(int target){//플레이어 사망시 처리
        window.playerdie(target,friendly[target].getDieimage());
        dieplayer++;
        attack_per[target]=0;
        set_per();
        if(dieplayer==playernum) end(false);//패배 (수정필요)
    }


    public void MobAttack(int target){
        /*몬스터를 공격*/
        enemy[target].Hurt(friendly[turn].Attack());
        window.setmobHP(target,enemy[target].get_HPper());

        //생존확인
        if(!enemy[target].isLive()){
            mobdie(target);
        }
        //몬스터 다죽을 시 추가 필요


        //다음턴 넘기기
        //중간 정지를 위해 스레드로
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                next_turn();
            }
        },750);


    }

    public void MobAttackskill(int target){
        /*몬스터를 공격*/
        enemy[target].Hurt(friendly[turn].attackskill());
        window.setmobHP(target,enemy[target].get_HPper());

        //생존확인
        if(!enemy[target].isLive()){
            mobdie(target);
        }
        //몬스터 다죽을 시 추가 필요


        //다음턴 넘기기
        //중간 정지를 위해 스레드로
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                next_turn();
            }
        },750);


    }

    private void mobdie(int target){//죽었을 시 이벤트 처리
        window.mobdie(target,enemy[target].getDieimage());
        diemob++;
        if(dieplayer==playernum) end(true);
    }


    public void PlayerHeal(int target){
        /*플레이어를 회복시킴*/
        friendly[target].recovery(friendly[turn].heal(500,0));
        window.setplayerHP(target,friendly[target].get_HPper());
        window.setplayerMP(turn,friendly[turn].get_MPper());

        //다음턴
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                next_turn();
            }
        },750);
    }


    public void debuff(int target){
        enemy[target].add_buff(friendly[turn].usebuff(totalTurn,true));

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                next_turn();
            }
        },750);
    }

    public void buff(int target){
        friendly[target].add_buff(friendly[turn].usebuff(totalTurn,false));

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                next_turn();
            }
        },750);
    }




    @RequiresApi(api = Build.VERSION_CODES.M)
    public void select_mob (Drawable d){
        //적선택
        for (int i=0;i<mobnum;i++){
            if(enemy[i].isLive())
                window.select_mob(d,i);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean finish_select_mob(int target){
        if(!enemy[target].isLive())
            return false;
        window.setnowturn(turn,false);
        for (int i=0;i<mobnum;i++){
            if(enemy[i].isLive())
                window.finish_select_mob(i);
        }
        chatui.clear();//선택이 끝났기에 일이 처리되는 동안 선택불가
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean finish_select_mob()
    {
        for (int i=0;i<mobnum;i++){
            if(enemy[i].isLive())
                window.finish_select_mob(i);
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void select_player(Drawable d){
        //아군선택
        for (int i=0;i<playernum;i++){
            if(friendly[i].isLive())
                window.select_player(d,i);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean finish_select_player(int target){
        if(!friendly[target].isLive())
            return false;
        window.setnowturn(turn,false);
        for (int i=0;i<playernum;i++){
            if(friendly[i].isLive())
                window.finish_select_player(i);
        }
        chatui.clear();//선택이 끝났기에 일이 처리되는 동안 선택불가
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean finish_select_player()
    {
        for (int i=0;i<playernum;i++){
            if(friendly[i].isLive())
                window.finish_select_player(i);
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)

    public void execution(int num, final boolean isred, Drawable d)
    {
        if(num>0){
            if(isred){
                window.setforeground(d);
                chatui.setforeground(d);
            }
            else {
                num--;
                window.setforeground(null);
                chatui.setforeground(null);
            }
            final int finalNum = num;
            final Drawable fd=d;
            handler.postDelayed(new Runnable() {
                @Override
                public void run()
                {
                    execution(finalNum, !isred, fd);
                }
            },500);

        }
        else
        {
            for (int i=0;i<mobnum;i++)
            {
                if(enemy[i].isLive())
                {
                    enemy[i].Hurt(new Damage(0,0,99999));
                    window.setmobHP(i,enemy[i].get_HPper());
                    mobdie(i);
                }
            }

        }
    }

    public boolean Executionable()
    {//처형가능성
        return (mobnum/2<=diemob);//죽은 몹이 적의 절반이상일경우
    }

    public boolean can_useusePotion(int num){//0:hp,1:mp,2:전체중 하나라도 가능
        switch (num){
            case 0://hp
                if(true) {
                    return true;
                }
                break;
            case 1://mp
                if(true) {
                    return true;
                }
                break;
            case 2://전체
                if(true) {
                    return true;
                }
                break;
        }
        return false;
    }

    public void usePotion(int target,boolean ishp)
    {
        //포션개수깎기 필요
        if(ishp){
            friendly[target].recovery(new Heal(500,0));
            window.setplayerHP(target,friendly[target].get_HPper());
        }
        else {
            friendly[target].recovery(new Heal(0,500));
            window.setplayerMP(target,friendly[target].get_MPper());
        }
        //다음턴
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                next_turn();
            }
        },750);
    }


    public void draw_skillmenu(){
        chatui.draw_skillmenu();
    }



    //chatui
    public void draw_startmenu(){
        chatui.draw_startmenu();
    }

    public void draw_itemmenu(){
        chatui.draw_itemmenu();
    }

    public void chatuiclear(){
        chatui.clear();
    }

    public boolean can_useSkill(int skill){
        return friendly[turn].can_useskill(skill);
    }

    private void end(boolean win){

        if(win)
        {

        }
        else
        {

        }
    }












}
