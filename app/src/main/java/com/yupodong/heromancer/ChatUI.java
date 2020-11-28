package com.yupodong.heromancer;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;


public class ChatUI {


    private LinearLayout layout;
    private Context context;
    private com.yupodong.heromancer.clickevent clickevent;



    public ChatUI(LinearLayout l, Context con, com.yupodong.heromancer.clickevent clickevent){
        this.layout=l;
        this.context=con;
        this.clickevent=clickevent;

        button_init();
        draw_startmenu();
    }


    LinearLayout.LayoutParams param= new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,1);

    //start
    private int startbuttonNum=4;
    private Button[] start_btn=new Button[startbuttonNum];
    String[] s={"공격","스킬","아이템","강제처형"};
    public void draw_startmenu(){
        layout.removeAllViews();
        for (int i=0;i<startbuttonNum;i++){
            layout.addView(start_btn[i]);
        }
    }

    //skill
    private int skillbuttonNum=4;
    private Button[] skill_btn=new Button[skillbuttonNum];
    String[] skills={"공격스킬","치유스킬","버프","디버프"};
    public void draw_skillmenu(){
        layout.removeAllViews();
        for (int i=0;i<skillbuttonNum;i++){
            layout.addView(skill_btn[i]);
        }
    }

    private int ItemNum=2;
    private Button[] itembtn=new Button[ItemNum];
    String[] item={"Hp포션","Mp포션"};
    public void draw_itemmenu(){
        layout.removeAllViews();
        for (int i=0;i<ItemNum;i++)
        {
            layout.addView(itembtn[i]);
        }
    }



    private void button_init(){
        //startmenu
        for (int i=0;i<startbuttonNum;i++){
            start_btn[i]=new Button(context);
            start_btn[i].setText(s[i]);
            start_btn[i].setLayoutParams(param);
            start_btn[i].setOnClickListener(clickevent.startbtn[i]);
        }

        //skillmenu
        for (int i=0;i<skillbuttonNum;i++){
            skill_btn[i]=new Button(context);
            skill_btn[i].setText(skills[i]);
            skill_btn[i].setLayoutParams(param);
            skill_btn[i].setOnClickListener(clickevent.skillbtn[i]);
        }

        //item
        for(int i=0;i<ItemNum;i++)
        {
            itembtn[i]=new Button(context);
            itembtn[i].setText(item[i]);
            itembtn[i].setLayoutParams(param);
            itembtn[i].setOnClickListener(clickevent.itembtn[i]);
        }


    }

    public void clear(){
        layout.removeAllViews();
    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setforeground(Drawable d){
        layout.setForeground(d);
    }




}
