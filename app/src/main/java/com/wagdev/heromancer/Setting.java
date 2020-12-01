package com.wagdev.heromancer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.wagdev.heromancer.R;

public class Setting extends AppCompatActivity {
    public static boolean soundOnOff = true;     //스위치 on/off 여부 저장

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_setting);

        //sound on/off 관련
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch sound =(Switch)findViewById(R.id.soundSwitch);
        sound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    soundOnOff = true;
                    MainActivity.mediaPlayer.start();
                    //게임의 모든 사운드 on
                }
                else{
                    soundOnOff = false;
                    MainActivity.mediaPlayer.pause();
                    //게임의 모든 사운드 off
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}