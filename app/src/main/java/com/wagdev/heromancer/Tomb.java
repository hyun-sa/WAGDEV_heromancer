package com.wagdev.heromancer;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wagdev.heromancer.R;

public class Tomb extends AppCompatActivity {
    private TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tomb);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        t=(TextView)findViewById(R.id.morality);
        t.setText("도덕성: "+Integer.toString(DataBase.getMorality()));
    }

    private int selectnum()
    {
        for (int i=0;i<2;i++){
            //빈게나올때까지
            if(!DataBase.getsub(i)){
                DataBase.setsub(i,true);
                return i;
            }
        }
        return -1;
    }

    public void warriorbtn(View v){
        if(DataBase.getMorality()<50||DataBase.getSubnum()<2){
            return;
        }
        DataBase.plus_Morality(-50);
        t.setText("도덕성: "+Integer.toString(DataBase.getMorality()));

        int i=selectnum();

    }

    public void Archerbtn(View v){
        if(DataBase.getMorality()<50||DataBase.getSubnum()<2){
            return;
        }
        DataBase.plus_Morality(-50);
        t.setText("도덕성: "+Integer.toString(DataBase.getMorality()));

        int i=selectnum();

    }

    public void Knightbtn(View v){
        if(DataBase.getMorality()<50||DataBase.getSubnum()<2){
            return;
        }
        DataBase.plus_Morality(-50);
        t.setText("도덕성: "+Integer.toString(DataBase.getMorality()));

        int i=selectnum();




    }
}
