package com.yupodong.heromancer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class SubMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submain);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
    }

    public void onButtonTombClicked(View view) {
        Intent intent = new Intent(getApplicationContext(), Tomb.class);
        startActivity(intent);
    }

    public void onButtonStoreClicked(View view) {
        Intent intent = new Intent(getApplicationContext(), Store.class);
        startActivity(intent);
    }

    public void onButtonTrainingClicked(View view) {
        Intent intent = new Intent(getApplicationContext(), Training.class);
        startActivity(intent);
    }

    public void onButtonFieldClicked(View view) {
        Intent intent = new Intent(getApplicationContext(), battle.class);
        startActivity(intent);
    }
}
