package com.wagdev.heromancer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wagdev.heromancer.R;
import com.wagdev.heromancer.DataBase;

public class Store extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store);
        int money = DataBase.getMoney();
        TextView moneyview = (TextView)findViewById(R.id.moneytext);
        moneyview.setText(money + " ");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
    }

    @SuppressLint("SetTextI18n")
    public void onButtonHPClicked(View view) {
        int money = DataBase.getMoney();
        int morality = DataBase.getMorality();

        if (money - (int)(35 - Math.round(0.3)*morality) > 0){
            DataBase.plus_money(-(int)(35 - Math.round(0.3)*morality));
            money  = money-(int)(35 - Math.round(0.3)*morality);
            DataBase.plus_hppotion(1);
        }
        TextView moneyview = (TextView)findViewById(R.id.moneytext);
        moneyview.setText(money + " ");
    }

    public void onButtonMPClicked(View view) {

        int money = DataBase.getMoney();
        int morality = DataBase.getMorality();

        if (money - (int)(35 - Math.round(0.3)*morality) > 0){
            DataBase.plus_money(-(int)(35 - Math.round(0.3)*morality));
            money  = money-(int)(35 - Math.round(0.3)*morality);
            DataBase.plus_hppotion(1);
        }
        TextView moneyview = (TextView)findViewById(R.id.moneytext);
        moneyview.setText(money + " ");
    }
}
