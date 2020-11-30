package com.yupodong.heromancer.object;

import com.yupodong.heromancer.object.data.Damage;

public class subplayer extends Player{
    public subplayer(){
        super();
        MaxHP=100;     //최대hp
        MaxMP=100;     //최대 mp
        HP=MaxHP;      //현재 hp
        MP=MaxMP;      //현재 mp
        Atk=45;       //공격력
        Mag=30;       //마력
        Def=20;       //방어력
        MRt=15;       //마법 저항력
        subplayer_kind=0;//몹종류
    }

    @Override
    public Damage Attack() {
        return new Damage(Atk,0,0);//임시등록
    }



    @Override
    public void Die() {
        live=false;
    }
}
