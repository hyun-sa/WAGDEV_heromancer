package com.yupodong.heromancer.object;

import com.yupodong.heromancer.R;
import com.yupodong.heromancer.object.data.Damage;

public class Archer extends Mob{


    public Archer(){
        super();
        MaxHP=45;     //최대hp
        MaxMP=0;     //최대 mp
        HP=MaxHP;        //현재 hp
        MP=MaxMP;        //현재 mp
        Atk=20;       //공격력
        Mag=0;       //마력
        Def=10;       //방어력
        MRt=15;       //마법 저항력
        Dieimage=R.drawable.archerdie;

    }

    @Override
    public Damage Attack() {

        return new Damage(Atk,0);
    }

    @Override
    public void Die() {
        live=false;
    }
}
