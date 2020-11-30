package com.wagdev.heromancer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.wagdev.heromancer.R;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    SoundPool soundPool;
    int soundID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC,0);
        soundID = soundPool.load(this,R.raw.click,1);
        mediaPlayer = MediaPlayer.create(this, R.raw.main_bgm);
        if (!mediaPlayer.isPlaying())
        {
            mediaPlayer.start();
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        if (!mediaPlayer.isPlaying())
        {
            mediaPlayer.start();
        }
    }

    @Override
    public void onBackPressed()
    {

    }

    public void OnClickHandler(View view)
    {
        soundPool.play(soundID,1f,1f,0,0,1f);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("게임시작");

        builder.setItems(R.array.LAN, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int pos)
            {
                String[] items = getResources().getStringArray(R.array.LAN);
                Toast.makeText(getApplicationContext(),items[pos],Toast.LENGTH_LONG).show();


                Intent intent = new Intent(getApplicationContext(), SubMain.class);
                startActivity(intent);
                mediaPlayer.pause();
                //데이터 베이스 불러오기 필요
                //없으면 생성 있으면 그대로
            }
        });


        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void ClicktoSetting(View view)
    {
        soundPool.play(soundID,1f,1f,0,0,1f);
        Intent intent = new Intent(this, Setting.class);
        intent.putExtra("data", "Setting Popup");
        startActivityForResult(intent, 1);
    }

    public void onClick_finish(View view)
    {
        soundPool.play(soundID,1f,1f,0,0,1f);
        mediaPlayer.pause();
        ActivityCompat.finishAffinity(this);
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}