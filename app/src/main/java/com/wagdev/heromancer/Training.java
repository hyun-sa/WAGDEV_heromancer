package com.wagdev.heromancer;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.wagdev.heromancer.object.MagicKnight;
import com.wagdev.heromancer.object.subplayer;

import com.wagdev.heromancer.R;

import java.util.Random;
import java.util.zip.DataFormatException;

public class Training extends AppCompatActivity {
    private int traingCost;
    private int randomStat;
    private int morality = DataBase.getMorality();
    private int money = DataBase.getMoney();
    private int subnum = DataBase.getSubnum();
    private int[] player = DataBase.getPlayerStat();
    private int[][] subplayer = DataBase.getSubStat();
    TextView text1 = (TextView)findViewById(R.id.trainingText);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.training);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
    }

    public void onClickTraining(View view){
        traingCost = CheckTraingCost();

        if(money < traingCost)
            text1.setText("보유한 골드가 부족합니다.");
        else {
            //training 가능하므로 골드 차감
            DataBase.plus_money(-traingCost);
            money = DataBase.getMoney();
            traingStart();
        }

    }

    //도덕성에 따른 훈련 비용 결정
    public int CheckTraingCost(){
        if(morality < 0)
            return 8;
        else if(morality > 100)
            return 48;
        else
            return 4*(morality/10)+8;
    }

    //랜덤 스탯 생성 후 각 아군의 스탯에 plus
    public void traingStart(){
        randomStat = (int)(Math.random()*3)+1;

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
