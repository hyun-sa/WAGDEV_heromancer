package com.wagdev.heromancer.object;


import com.wagdev.heromancer.object.data.Buff;
import com.wagdev.heromancer.object.data.Damage;
import com.wagdev.heromancer.object.data.Heal;

abstract public class Player extends Entity{

    private double skillmp[]={45,45,45,45};//스킬 마나 0:공격스킬,1:치료스킬,2:버프,3:디버프
    protected int subplayer_kind=-1;

    public Player(){
        super();
    }

    public int getSubplayer_kind() {
        return subplayer_kind;
    }

    @Override
    public void Hurt(Damage damage){ //데미지를 입을때
        HP-=damage.Physical*(1-(Def/(Def+100)));
        HP-=damage.Magic*(1-(MRt/(MRt+100)));
        HP-=damage.trued;
        if(HP<=0){
            HP=0;
            Die();
        }
    }

    public Damage attackskill(){
        MP-=skillmp[0];
        return new Damage(Atk,Mag,50);
    }

    public Heal heal(){
        MP-=skillmp[1];
        return new Heal(Mag*2,0);
    }


    //버프효과는 고정
    public Buff usebuff(int nowturn, boolean isdebuff){


        if(isdebuff){
            MP-=skillmp[2];
            return new Buff(nowturn+3,0,0,-3*(Mag*0.02),0);
        }
        else{
            MP-=skillmp[3];
            return new Buff(nowturn+3,3*(Mag*0.04),0,10,10);
        }
    }



    @Override
    public void recovery(Heal heal){
        HP+=heal.HP;
        MP+=heal.MP;
        if(HP>MaxHP)
            HP=MaxHP;
        if(MP>MaxMP)
            MP=MaxMP;
    }


    public boolean can_useskill(int skill) {
        return MP>=skillmp[skill];
    }

}
