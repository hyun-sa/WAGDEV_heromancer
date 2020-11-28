package com.yupodong.heromancer;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;

public class BattleWindow {
    private ImageButton[] mob=new ImageButton[4];
    private ImageButton[] player=new ImageButton[4];

    private ImageView[] now_turn=new ImageView[4];

    private ProgressBar[] mobhp=new ProgressBar[4];
    private ProgressBar[] playerhp=new ProgressBar[4];
    private ProgressBar[] playermp=new ProgressBar[4];

    private int mobNum;
    private int playerNum;
    private ConstraintLayout layout;
    private Context context;
    private com.yupodong.heromancer.clickevent c;
    public BattleWindow(ConstraintLayout l, Context con, int playerNum, int mobNum, com.yupodong.heromancer.clickevent c){
        this.layout=l;
        this.context=con;
        this.playerNum=playerNum;
        this.mobNum=mobNum;
        this.c=c;

        //자주 사용되기에 미리 찾아둠
        mob[0]=(ImageButton)layout.findViewById(R.id.mob1);
        mob[1]=(ImageButton)layout.findViewById(R.id.mob2);
        mob[2]=(ImageButton)layout.findViewById(R.id.mob3);
        mob[3]=(ImageButton)layout.findViewById(R.id.mob4);
        player[0]=(ImageButton)layout.findViewById(R.id.player1);
        player[1]=(ImageButton)layout.findViewById(R.id.player2);
        player[2]=(ImageButton)layout.findViewById(R.id.player3);
        player[3]=(ImageButton)layout.findViewById(R.id.player4);

        now_turn[0]=(ImageView)layout.findViewById(R.id.p1);
        now_turn[1]=(ImageView)layout.findViewById(R.id.p2);
        now_turn[2]=(ImageView)layout.findViewById(R.id.p3);
        now_turn[3]=(ImageView)layout.findViewById(R.id.p4);
        for(int i=1;i<4;i++){
            now_turn[i].setVisibility(View.INVISIBLE);
        }

        mobhp[0]=(ProgressBar)layout.findViewById(R.id.mob1hp);
        mobhp[1]=(ProgressBar)layout.findViewById(R.id.mob2hp);
        mobhp[2]=(ProgressBar)layout.findViewById(R.id.mob3hp);
        mobhp[3]=(ProgressBar)layout.findViewById(R.id.mob4hp);
        playerhp[0]=(ProgressBar)layout.findViewById(R.id.playerhp1);
        playerhp[1]=(ProgressBar)layout.findViewById(R.id.playerhp2);
        playerhp[2]=(ProgressBar)layout.findViewById(R.id.playerhp3);
        playerhp[3]=(ProgressBar)layout.findViewById(R.id.playerhp4);
        playermp[0]=(ProgressBar)layout.findViewById(R.id.playermp1);
        playermp[1]=(ProgressBar)layout.findViewById(R.id.playermp2);
        playermp[2]=(ProgressBar)layout.findViewById(R.id.playermp3);
        playermp[3]=(ProgressBar)layout.findViewById(R.id.playermp4);


        //리스너 등록
        for (int i=0;i<4;i++) {
            mob[i].setOnClickListener(c.mob);
            player[i].setOnClickListener(c.player);
        }




    }

    public void setnowturn(int target,boolean on){
        if(on)
            now_turn[target].setVisibility(View.VISIBLE);
        else
            now_turn[target].setVisibility(View.INVISIBLE);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void select_mob(Drawable d, int i){
        /*mob1이 0*/
        c.setMobtarget(true);
        mob[i].setForeground(d);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void finish_select_mob(int i){
        c.setMobtarget(false);
        mob[i].setForeground(null);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void select_player(Drawable d, int i){
        /*player1이 0*/
        c.setPlayertarget(true);
        player[i].setForeground(d);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void finish_select_player(int i){
        c.setPlayertarget(false);
        player[i].setForeground(null);
    }

    public void mobdie(int i,int image){
        mob[i].setImageResource(image);
    }

    public void playerdie(int i,int image){
        player[i].setImageResource(image);
    }

    public void setmobHP(int target,int per){
        mobhp[target].setProgress(per);
    }

    public void setplayerHP(int target,int per){
        playerhp[target].setProgress(per);
    }

    public void setplayerMP(int target,int per){
        playermp[target].setProgress(per);
    }

    public void setmobimage(int i,int image){
        mob[i].setImageResource(image);
    }

    public void setPlayerimage(int i,int image){
        player[i].setImageResource(image);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setforeground(Drawable d){
        layout.setForeground(d);
    }

    //private int testcount=0;
    public void test(String a){
        //testcount++;
        ((TextView)layout.findViewById(R.id.test)).setText(a);
    }
}
