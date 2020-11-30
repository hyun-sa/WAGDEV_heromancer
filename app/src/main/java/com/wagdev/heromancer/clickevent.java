package com.wagdev.heromancer;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.wagdev.heromancer.R;

public class clickevent {
    private Resources resources;
    private BattleHandler handler;

    //상태
    private int state=0;
    private boolean mobtarget=false;
    private boolean playertarget=false;
    //리스너
    public View.OnClickListener[] startbtn=new View.OnClickListener[4]; //0공격 1스킬 2아이템 3강제처형
    public View.OnClickListener mob;
    public View.OnClickListener[] skillbtn=new View.OnClickListener[4]; //0공격 1스킬 2아이템 3강제처형
    public View.OnClickListener player;
    public View.OnClickListener[] itembtn=new View.OnClickListener[2];//0:hp포션, 1:mp포션
    public View.OnClickListener backbtn;

    public clickevent(Resources r){
        this.resources=r;
    }
    public Resources getResources() {
        return resources;
    }
    public void setMobtarget(boolean mobtarget) {
        this.mobtarget = mobtarget;
    }
    public void setPlayertarget(boolean playertarget) {
        this.playertarget = playertarget;
    }
    public void setHandler(BattleHandler handler) {
        this.handler = handler;
        init_click();
    }

    @SuppressLint("UseCompatLoadingForDrawables")

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setState(int i){
        state = i;
    }

    private void init_click(){

        //돌아가기버튼
        backbtn=new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if(mobtarget){
                    handler.finish_select_mob();
                }
                if (playertarget){
                    handler.finish_select_player();
                }
                state=0;
                handler.draw_startmenu();
            }
        };

        //시작메뉴
        //공격 시, 상태 1
        startbtn[0]=new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            final Drawable d=resources.getDrawable(R.color.강제처형);//임시설정
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if(state!=1) {
                    if(state==3||state==4){
                        handler.finish_select_player();
                    }
                    state = 1;
                    handler.select_mob(d);
                }
            }
        };

        //스킬창
        startbtn[1]=new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if(handler.can_useSkill(0)){//일단은 모든 스킬의 마나가 같기에 이렇게 함
                    if(state==1){
                        handler.finish_select_mob();
                        state=0;
                    }
                    handler.draw_skillmenu();
                }
            }
        };

        //아이템
        startbtn[2]=new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if(handler.can_useusePotion(2)){
                    if(state==1){
                        handler.finish_select_mob();
                        state=0;
                    }
                    handler.draw_itemmenu();
                }
            }
        };

        //강제처형
        startbtn[3]=new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            final Drawable d=resources.getDrawable(R.color.강제처형);
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if(handler.Executionable())
                {
                    handler.chatuiclear();
                    handler.execution(3,true,d);
                }
            }
        };

        //스킬메뉴
        //공격스킬
        skillbtn[0]=new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            final Drawable d=resources.getDrawable(R.color.강제처형);//임시설정
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if(!handler.can_useSkill(0)) return;
                if(state!=2) {
                    if(state==3||state==4){
                        handler.finish_select_player();
                    }
                    state = 2;
                    handler.select_mob(d);
                }
            }
        };

        //치유
        skillbtn[1]=new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            final Drawable d=resources.getDrawable(R.color.아군타겟);
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if(!handler.can_useSkill(1)) return;
                if(state!=3){
                    if(state==2||state==5){
                        handler.finish_select_mob();
                    }
                    state=3;
                    handler.select_player(d);
                }
            }
        };

        //버프
        skillbtn[2]=new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            final Drawable d=resources.getDrawable(R.color.아군타겟);
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if(!handler.can_useSkill(2)) return;
                if(state!=4){
                    if(state==2||state==5){
                        handler.finish_select_mob();
                    }
                    state=4;
                    handler.select_player(d);
                }
            }
        };

        //디버프
        skillbtn[3]=new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            final Drawable d=resources.getDrawable(R.color.강제처형);//임시설정
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if(!handler.can_useSkill(3)) return;
                if(state!=5) {
                    if(state==3||state==4){
                        handler.finish_select_player();
                    }
                    state = 5;
                    handler.select_mob(d);
                }
            }
        };

        //아이템(수정필요)
        //hp포션
        itembtn[0]=new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            final Drawable d=resources.getDrawable(R.color.아군타겟);
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if(!handler.can_useusePotion(0)) return;
                if(state!=6){
                    state=6;
                    handler.select_player(d);
                }
            }
        };

        //mp포션
        itembtn[1]=new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            final Drawable d=resources.getDrawable(R.color.아군타겟);
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if(!handler.can_useusePotion(1)) return;

                if(!handler.finish_select_player(0)) return;//포션은 플레이어에게만 사용가능
                handler.usempPotion();
            }
        };

        //mob 이미지버튼에 들어갈 리스너
        mob=new View.OnClickListener() {
            @SuppressLint("NonConstantResourceId")
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if(mobtarget){
                    int target;
                    switch (v.getId()){
                        case R.id.mob1:
                            target=0;
                            break;
                        case R.id.mob2:
                            target=1;
                            break;
                        case R.id.mob3:
                            target=2;
                            break;
                        case R.id.mob4:
                            target=3;
                            break;
                        case R.id.mob5:
                            target=4;
                            break;
                        default:
                            return;
                    }
                    if(!handler.finish_select_mob(target))
                        return;

                    switch (state){
                        case -1:
                        case 1://공격
                            handler.MobAttack(target);
                            break;
                        case 2://공격스킬
                            handler.MobAttackskill(target);
                            break;
                        case 5://디버프
                            handler.debuff(target);
                            break;
                    }
                    state=0;
                }//if끝
            }//함수 끝
        };
        //player 이미지버튼에 들어갈 리스너
        player=new View.OnClickListener() {
            @SuppressLint("NonConstantResourceId")
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if(playertarget){
                    int target;
                    switch (v.getId()){
                        case R.id.player1:
                            target=0;
                            break;
                        case R.id.player2:
                            target=1;
                            break;
                        case R.id.player3:
                            target=2;
                            break;
                        default:
                            return;
                    }

                    if(!handler.finish_select_player(target))
                        return;

                    switch (state) {
                        case 3://힐
                            handler.PlayerHeal(target);
                            break;
                        case 4://버프
                            handler.buff(target);
                            break;
                        case 6://hp포션
                            handler.usehpPotion(target);
                            break;
                    }
                }//if끝
            }//함수 끝
        };
    }
}