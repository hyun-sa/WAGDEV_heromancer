package com.wagdev.heromancer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
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
import com.wagdev.heromancer.DataBase;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private static final DataBase database = new DataBase();
    //쫄다구 최대수
    public static int CHARACTER_NUMBER = 2;
    //플레이어 스텟 수
    public static final int PLAYER_STAT = 6;

    static MediaPlayer mediaPlayer;
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
        //현재 설정을 건드려야 모든 사운드 실행(수정필요!!)
        if (!mediaPlayer.isPlaying())
        {
            mediaPlayer.start();
        }
    }

    @Override
    protected void onResume()
    {
        //사운드 on/off 변경 후
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

                init();
                database.loadFile(getBaseContext());
                database.saveFile(getBaseContext());
                Intent intent = new Intent(getApplicationContext(), SubMain.class);
                startActivity(intent);
                mediaPlayer.pause();

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

    //파일 초기화
    public void init(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(getFilesDir() + "gamedata.txt", false));

            bw.write("0" + " " + "0" + " " + "0" + " " + "0" + " " + "0" + " " + "0" + " " + "\n");

            for(int i=0; i < PLAYER_STAT; i++) {
                bw.write("0" + " ");
            }
            bw.write("\n");

            for(int i=0; i < CHARACTER_NUMBER; i++) {
                for (int j = 0; j < 7; j++) {
                    bw.write("0"+" ");
                }
                bw.write("\n");
            }
            bw.close();

            Toast.makeText(this, "파일생성완료", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        database.saveFile(getBaseContext());
    }
}