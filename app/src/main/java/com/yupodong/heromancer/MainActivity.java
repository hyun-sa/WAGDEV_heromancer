package com.yupodong.heromancer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
    }

    public void OnClickHandler(View view)
    {
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
                //데이터 베이스 불러오기 필요
                //없으면 생성 있으면 그대로
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void ClicktoSetting(View view)
    {
        Intent intent = new Intent(this, Setting.class);
        intent.putExtra("data", "Setting Popup");
        startActivityForResult(intent, 1);
    }

    public void onClick_finish(View view)
    {
        finish();
    }
}