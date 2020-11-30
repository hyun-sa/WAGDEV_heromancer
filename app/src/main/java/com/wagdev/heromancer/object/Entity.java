package com.wagdev.heromancer.object;


import com.wagdev.heromancer.object.data.Buff;
import com.wagdev.heromancer.object.data.Damage;
import com.wagdev.heromancer.object.data.Heal;
import com.wagdev.heromancer.R;

import java.util.PriorityQueue;

abstract class Entity {

    //스텟
    protected double MaxHP;     //최대hp
    protected double HP;        //현재 hp
    protected double MaxMP;     //최대 mp
    protected double MP;        //현재 mp
    protected double Atk;       //공격력
    protected double Mag;       //마력
    protected double Def;       //방어력
    protected double MRt;       //마법 저항력

    //상태등
    protected boolean live=true;//생존?
    protected int Dieimage= R.drawable.wall_img;//죽었을때 이미지 임시설정
    protected PriorityQueue<Buff> hasbuff;//버프 디버프 통합관리



    public Entity(){
        hasbuff=new PriorityQueue<>();
    }


    abstract public void Hurt(Damage damage);    //데미지를 입을때 0:물리 1:마법 2: 고정
    abstract public Damage Attack();
    abstract public void recovery(Heal heal);
    abstract public void Die();

    public double getHP() {
        return HP;
    }

    public double getMP() {
        return MP;
    }


    public void buff_check(int turn){
        while (!hasbuff.isEmpty()&&hasbuff.peek().end_turn==turn){
            Buff b=hasbuff.poll();
            this.Atk-=b.Atk;
            this.Mag-=b.Mag;
            this.Def-=b.Def;
            this.MRt-=b.MRt;
        }
    }

    public void add_buff(Buff b){
        hasbuff.offer(b);
        this.Atk+=b.Atk;
        this.Mag+=b.Mag;
        this.Def+=b.Def;
        this.MRt+=b.MRt;
    }


    public int get_HPper(){
        return (int)(HP/MaxHP*100);
    }

    public int get_MPper(){
        return (int)(MP/MaxMP*100);
    }



    public boolean isLive() {
        return live;
    }

    public int getDieimage() {
        return Dieimage;
    }


}
