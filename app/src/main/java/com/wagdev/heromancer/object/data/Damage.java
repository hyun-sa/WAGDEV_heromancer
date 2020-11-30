package com.wagdev.heromancer.object.data;

public class Damage {
    public double Physical;
    public double Magic;
    public double trued;

    public Damage(double phy, double mag, double t){
        if(phy>0)
            Physical=phy;
        else
            Physical=0;
        if(mag>0)
            Magic=mag;
        else
            Magic=0;
        if(t>0)
            trued=t;
        else
            trued=0;
    }

    public Damage(double phy, double mag){
        if(phy>0)
            Physical=phy;
        else
            Physical=0;
        if(mag>0)
            Magic=mag;
        else
            Magic=0;
        trued=0;
    }
}
