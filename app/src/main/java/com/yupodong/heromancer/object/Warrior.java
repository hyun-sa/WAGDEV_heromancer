package com.yupodong.heromancer.object;

import com.yupodong.heromancer.object.data.Damage;

public class Warrior extends Mob{
    public Warrior(){
        super();
        MaxHP=700;     //최대hp
        HP=MaxHP;        //현재 hp
        MaxMP=500;     //최대 mp
        MP=MaxMP;        //현재 mp
        Atk=50;       //공격력
        Mag=0;          //마력
        Def=200;       //방어력
        MRt=70;       //마법 저항력
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
