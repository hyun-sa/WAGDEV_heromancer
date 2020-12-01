package com.wagdev.heromancer;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import com.wagdev.heromancer.object.subplayer;
import com.wagdev.heromancer.object.MagicKnight;

public class DataBase extends AppCompatActivity {

    //쫄다구 최대수
    public static int CHARACTER_NUMBER = 2;
    //플레이어 스텟 수
    public static final int PLAYER_STAT = 4;
    //돈
    public static int money=0;
    //플레이어 스텟
    public static int[] player = new int[6];
    //쫄따구 수
    public static int subnum=0;
    //쫄따구 스텟(인덱스6은 몹의 유형을 저장
    public static int[][] character = new int[CHARACTER_NUMBER][7];
    //hp포션개수
    public static int hp_potion=0;
    //mp포션개수
    public static int mp_potion=0;
    //도덕성
    private static int Morality=0;
    //전투승리여부
    private static boolean win;


    // money subnum hp_potion mp_potion
    // player
    // character

    public static int getMorality() {
        return Morality;
    }

    public static void plus_Morality(int amount){
        Morality+=amount;
        if(Morality>100) Morality=100;
    }

    public static int getMoney() {
        return money;
    }

    public static void plus_money(int amount){
        money+=amount;
    }

    public static int getHp_potion() {
        return hp_potion;
    }

    public static void plus_hppotion(int amount){
        hp_potion+=amount;
    }

    public static int getMp_potion() {
        return mp_potion;
    }

    public static void plus_mppotion(int amount){
        mp_potion+=amount;
    }

    public static int getSubnum() {
        return subnum;
    }

    public static void plus_subnum(int amount){
        subnum+=amount;
    }

    public static subplayer get_subplayer(int i){
        return new subplayer(character[i]);
    }

    public static MagicKnight get_player(int i){
        return new MagicKnight(player);
    }

    public static boolean isWin() {
        return win;
    }

    public static void setWin(boolean win) {
        DataBase.win = win;
    }

    public void init(Context context){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(context.getFilesDir() + "gamedata.txt", false));

            bw.write("0" + " " + "0" + " " + "0" + " " + "0" + " " + "\n");

            for(int i=0; i < PLAYER_STAT; i++) {
                bw.write("0" + " ");
            }
            bw.write("\n");

            for(int i=0; i < CHARACTER_NUMBER; i++) {
                for (int j = 0; j < 6; j++) {
                    bw.write("0"+" ");
                }
                bw.write("\n");
            }
            bw.close();

            Toast.makeText(this, "생성완료", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void loadFile(Context context) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(context.getFilesDir() + "gamedata.txt"));
            String str = null;
            if((str = br.readLine()) != null){
                String[] readStr = str.split(" ");
                money = Integer.parseInt(readStr[0]);
                subnum = Integer.parseInt(readStr[1]);
                hp_potion = Integer.parseInt(readStr[2]);
                mp_potion = Integer.parseInt(readStr[3]);
            }
            if((str = br.readLine()) != null){
                String[] readStr = str.split(" ");
                Log.d("iiiiiiii", str);
                for (int i=0; i<readStr.length;i++) {
                    player[i] = Integer.parseInt(readStr[i]);
                    Log.d("iiiiii456465", i+" ");
                }
            }

            int k=0;
            while (((str = br.readLine()) != null)) {
                String[] readStr = str.split(" ");
                for (int i=0; i<readStr.length;i++){
                    character[k][i] = Integer.parseInt(readStr[i]);
                }
                k += 1;
            }
            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "File not Found", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void saveFile(Context context) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(context.getFilesDir() + "gamedata.txt", false));

            bw.write(money + " " + subnum + " " + hp_potion + " " + mp_potion + " " + "\n");

            for(int i=0; i < 4; i++) {
                bw.write(player[i] + " ");
            }
            bw.write("\n");

            for(int i=0; i < CHARACTER_NUMBER; i++) {
                for (int j = 0; j < 6; j++) {
                    bw.write(character[i][j]+" ");
                }
                bw.write("\n");
            }
            bw.close();

            Toast.makeText(this, "저장완료", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
