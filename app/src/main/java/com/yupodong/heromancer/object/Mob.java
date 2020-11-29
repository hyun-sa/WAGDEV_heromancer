package com.yupodong.heromancer.object;

import com.yupodong.heromancer.object.data.Damage;
import com.yupodong.heromancer.object.data.Heal;

abstract public class Mob extends Entity{


    public Mob(){
        super();
    }

    @Override
    public void Hurt(Damage damage){ //데미지를 입을때
        HP-=damage.Physical*(100.0/Def);
        HP-=damage.Magic*(100.0/MRt);
        HP-=damage.trued;
        if(HP<=0) {
            HP=0;
            Die();
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
}
