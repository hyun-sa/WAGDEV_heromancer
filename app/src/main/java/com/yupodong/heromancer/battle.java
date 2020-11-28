package com.yupodong.heromancer;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class battle extends AppCompatActivity {

    BattleHandler handler;
    clickevent c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//가로고정
        setContentView(R.layout.activity_battle);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//바 없애기
        getSupportActionBar().hide();

        c=new clickevent(getResources());
        handler=new BattleHandler((LinearLayout)findViewById(R.id.chat),(ConstraintLayout)findViewById(R.id.window),this,c);

    }
}