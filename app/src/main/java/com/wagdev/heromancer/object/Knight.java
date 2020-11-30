package com.wagdev.heromancer.object;

import com.wagdev.heromancer.object.data.Damage;
import com.wagdev.heromancer.R;

public class Knight extends Mob{
    public Knight(){
        super();
        MaxHP=600;     //최대hp
        HP=MaxHP;        //현재 hp
        MaxMP=500;     //최대 mp
        MP=MaxMP;        //현재 mp
        Atk=100;       //공격력
        Mag=0;       //마력
        Def=100;       //방어력
        MRt=100;       //마법 저항력
        Dieimage= R.drawable.knightdie;
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
