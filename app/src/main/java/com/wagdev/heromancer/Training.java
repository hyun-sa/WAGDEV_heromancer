package com.wagdev.heromancer;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wagdev.heromancer.R;

import java.util.Random;
import java.util.zip.DataFormatException;

public class Training extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.training);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
    }

    public void onClickTraining(View view){
        int traingCost = CheckTraingCost();
        int money = DataBase.getMoney();
        TextView text1 = (TextView)findViewById(R.id.trainingText);
        TextView text2 = (TextView)findViewById(R.id.moneytext);

        if(money < traingCost)
            text1.setText("보유한 골드가 부족합니다.");
        else {
            DataBase.plus_money(-traingCost);
            money -= traingCost;
            text2.setText(money+" ");
            traingStart();
        }

    }

    //도덕성에 따른 훈련 비용 결정
    public int CheckTraingCost(){
        int morality = DataBase.getMorality();

        if(morality < 0)
            return 8;
        else if(morality > 100)
            return 48;
        else
            return 4*(morality/10)+8;
    }

    //랜덤 스탯 생성 후 각 아군의 스탯에 plus
    public void traingStart(){
        int randomStat = (int)(Math.random()*3)+1;
        int subnum = DataBase.getSubnum();
        int[] player = DataBase.getPlayerStat();
        int[][] subplayer = DataBase.getSubStat();

        TextView text1 = (TextView)findViewById(R.id.trainingText);
        text1.setText("모든 아군의 스탯을 " +randomStat+"증가합니다.");

        //주인공 스탯
        for(int i=0;i<6;i++)
            player[i]+= randomStat;
        //아군 스탯 증가
        for(int j=0;j<subnum;j++)
            for(int k=0;k<6;k++)
                subplayer[j][k]+= randomStat;
    }
}
