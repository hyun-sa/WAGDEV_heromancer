package com.wagdev.heromancer;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.wagdev.heromancer.R;
import com.wagdev.heromancer.DataBase;

public class Store extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
    }

    public void onButtonHPClicked(View view) {
        //int money DataBase.getMoney();
    }

    public void onButtonMPClicked(View view) {
        //데이터베이스 작업 필요
    }
}
