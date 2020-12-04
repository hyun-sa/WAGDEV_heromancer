package com.wagdev.heromancer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Manual extends AppCompatActivity {
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_manual);


        TextView manual = (TextView)findViewById(R.id.manual);
        String str  = "전장\n"
                + "필드에서 전투할 수 있다.\n\n"
                + "상점\n"
                + "HP포션과 MP포션을 구매할 수 있다.\n\n"
                + "훈련\n"
                + "아군의 능력치를 향상할 수 있다.\n\n"
                + "무덤\n"
                + "시체를 부활시켜 아군으로 만들 수 있다.\n\n"
                ;

        manual.setText(str);
    }

}
