package com.yupodong.heromancer.object;


import com.yupodong.heromancer.object.data.Damage;
import com.yupodong.heromancer.object.data.Heal;

abstract public class Player extends Entity{

    protected int equipment;//임시, 수정해야함

    private double skillmp[]={100,100,100,100};//스킬 마나 0:공격스킬,1:치료스킬,2:버프,3:디버프

    public Player(){
        super();
    }

    @Override
    public void Hurt(Damage damage){ //데미지를 입을때
        HP-=damage.Physical*(100.0/Def);
        HP-=damage.Magic*(100.0/MRt);
        HP-=damage.trued;
        if(HP<=0){
            HP=0;
            Die();
        }
    }

    public Heal heal(double HP,double MP){
        MP-=skillmp[1];
        return new Heal(HP,MP);
    }

    public Damage attackskill(){
        MP-=skillmp[0];
        return new Damage(Atk,Mag,50);
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
