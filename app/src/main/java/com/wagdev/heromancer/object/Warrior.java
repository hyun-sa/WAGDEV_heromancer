package com.wagdev.heromancer.object;

import com.wagdev.heromancer.object.data.Damage;
import com.wagdev.heromancer.R;

public class Warrior extends Mob{
    public Warrior(){
        super();
        MaxHP=50;     //최대hp
        MaxMP=0;     //최대 mp
        HP=MaxHP;        //현재 hp
        MP=MaxMP;        //현재 mp
        Atk=15;       //공격력
        Mag=0;          //마력
        Def=20;       //방어력
        MRt=15;       //마법 저항력
        Dieimage= R.drawable.warriordie;
    }

    @Override
    public Damage Attack() {
        return new Damage(Atk,0,0);//수정해야됨
    }

    @Override
    public void Die() {
        live=false;
    }
}
