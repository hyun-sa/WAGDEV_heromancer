package com.wagdev.heromancer.object;

import com.wagdev.heromancer.object.data.Damage;

public class MagicKnight extends Player {


//주인공
    public MagicKnight(){
        super();
        MaxHP=500;     //최대hp
        HP=MaxHP;        //현재 hp
        MaxMP=500;     //최대 mp
        MP=MaxMP;        //현재 mp
        Atk=50;       //공격력
        Mag=50;       //마력
        Def=150;       //방어력ㅁ
        MRt=100;       //마법 저항력
    }

    @Override
    public Damage Attack() {
        return new Damage(Atk,0,300);//임시등록
    }



    @Override
    public void Die() {
        live=false;
    }
}
