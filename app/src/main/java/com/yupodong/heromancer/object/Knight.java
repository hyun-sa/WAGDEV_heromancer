package com.yupodong.heromancer.object;

import com.yupodong.heromancer.R;
import com.yupodong.heromancer.object.data.Damage;

public class Knight extends Mob{
    public Knight(){
        super();
        MaxHP=100;     //최대hp
        MaxMP=0;     //최대 mp
        HP=MaxHP;        //현재 hp
        MP=MaxMP;        //현재 mp
        Atk=10;       //공격력
        Mag=0;       //마력
        Def=35;       //방어력
        MRt=5;       //마법 저항력
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
