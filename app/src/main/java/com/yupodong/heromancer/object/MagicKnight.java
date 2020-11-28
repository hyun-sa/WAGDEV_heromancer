package com.yupodong.heromancer.object;

import com.yupodong.heromancer.object.data.Damage;

public class MagicKnight extends Player {



    public MagicKnight(){
        super();
        MaxHP=500;     //최대hp
        HP=MaxHP;        //현재 hp
        MaxMP=500;     //최대 mp
        MP=MaxMP;        //현재 mp
        Atk=50;       //공격력
        Mag=50;       //마력
        Def=150;       //방어력
        MRt=100;       //마법 저항력
    }

    @Override
    public Damage Attack() {
        return new Damage(Atk,Mag,1000);//임시등록
    }



    @Override
    public void Die() {
        live=false;
    }
}
