package com.wagdev.heromancer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.wagdev.heromancer.R;

public class SubMain extends AppCompatActivity {
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submain);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        mediaPlayer = MediaPlayer.create(this, R.raw.in_main_bgm);
        //사운드 on/off 변경된 설정 반영하기 위해
        if(!Setting.soundOnOff){
            mediaPlayer.pause();
        }
        else if (!mediaPlayer.isPlaying() || Setting.soundOnOff)
        {
            mediaPlayer.start();
        }
    }

    @Override
    protected void onResume()
    {
        //사운드 on/off 변경된 설정 반영하기 위해
        super.onResume();
        if(!Setting.soundOnOff){
            mediaPlayer.pause();
        }
        else if (!mediaPlayer.isPlaying() || Setting.soundOnOff)
        {
            mediaPlayer.start();
        }
    }

    @Override
    public void onBackPressed()
    {
        mediaPlayer.stop();
        finish();
        super.onBackPressed();
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
        mediaPlayer.pause();
        startActivity(intent);
        if(!DataBase.isWin()){
            onBackPressed();
        }
    }
}
