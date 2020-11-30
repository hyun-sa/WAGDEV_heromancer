package com.wagdev.heromancer.object.data;

public class Buff implements Comparable<Buff>{
    public double Atk;       //공격력
    public double Mag;       //마력
    public double Def;       //방어력
    public double MRt;       //마법 저항력

    public int end_turn;

    public Buff(int turn,double atk,double mag,double Def, double mrt){
        this.end_turn=turn;
        this.Atk=atk;
        this.Mag=mag;
        this.Def=Def;
        this.MRt=mrt;
    }

    @Override
    public int compareTo(Buff o) {
        if(this.end_turn<o.end_turn)
            return 1;
        else if(this.end_turn> o.end_turn)
            return -1;
        return 0;
    }

}
