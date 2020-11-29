package com.yupodong.heromancer.object;

import com.yupodong.heromancer.object.data.Damage;

public class subplayer extends Player{
    public subplayer(){
        super();
        MaxHP=500;     //최대hp
        HP=MaxHP;        //현재 hp
        MaxMP=0;     //최대 mp
        MP=MaxMP;        //현재 mp
        Atk=50;       //공격력
        Mag=50;       //마력
        Def=150;       //방어력
        MRt=100;       //마법 저항력
        subplayer_kind=0;//몹종류
    }

    @Override
    public Damage Attack() {
        return new Damage(Atk,0,200);//임시등록
    }



    @Override
    public void Die() {
        live=false;
    }
}
