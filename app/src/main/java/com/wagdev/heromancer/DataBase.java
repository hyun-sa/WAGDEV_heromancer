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

public class DataBase extends AppCompatActivity {
    //    public static final String DATABASE_NAME = "GameData.db";
//    public static final int DATABASE_VERSION = 1;
//
//    public static final String TABLE_NAME = "Character";
//    public static final String CHARACTER_ID = "_id";
//    public static final String MAX_HP = "max_hp";
//    public static final String MAX_MP = "max_mp";
//    public static final String OFFENSE = "offense";
//    public static final String DEFENSE = "defense";
//    public static final String MAGIC_POWER = "magic_power";
//    public static final String MAGIC_RESISTANCE = "magic_resistance";

//    public static final int CHARACTER_NUMBER = 5;
//    public static final int[][] character = new int[CHARACTER_NUMBER][6];

    //쫄다구 최대수
    public static int CHARACTER_NUMBER = 5;
    //플레이어 스텟 수
    public static final int PLAYER_STAT = 4;
    //돈
    public static int money;
    //플레이어 스텟
    public static int[] player = new int[4];
    //쫄따구 수
    public static int subnum;
    //쫄따구 스텟
    public static int[][] character = new int[CHARACTER_NUMBER][6];
    //hp포션개수
    public static int hp_potion;
    //mp포션개수
    public static int mp_potion;
    // money subnum hp_potion mp_potion
    // player
    // character


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

//    SQLiteDatabase database;
//
//    private void createDatabase() {
//        database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
////        database.execSQL("CREATE TABLE IF NOT EXISTS " + "things" + "("
////                + "_id" + " integer PRIMARY KEY autoincrement, "
////                + "money" + " integer, "
////                + "hp_potion" + " integer, "
////                + "mp_potion" + " integer, "
////                + "morality" + " integer)"
////        );
//    }
//
//
//    private void createTable(String name) {
//
//        if (database == null) {
//            createDatabase();
//        }
//
//        database.execSQL("CREATE TABLE IF NOT EXISTS " + name + "("
//                + CHARACTER_ID + " integer PRIMARY KEY autoincrement, "
//                + MAX_HP + " integer, "
//                + MAX_MP + " integer, "
//                + OFFENSE + " integer, "
//                + DEFENSE + " integer, "
//                + MAGIC_POWER + " integer, "
//                + MAGIC_RESISTANCE + " integer)"
//        );
//    }
//
//    private void insertCharacterRecord(String TableName, int max_hp, int max_mp, int offense, int defense, int magic_power, int magic_resistance){
//        if (database == null){
//            createDatabase();
//        }
//        if (TableName == null){
//            //경고 메시지
//        }
//
//        database.execSQL("INSERT INTO " + TableName
//                + "(max_hp, max_mp, offense, defense, magic_power, magic_resistance)"
//                + " values "
//                + "(" + max_hp + ", " + max_mp + ", " + offense + ", " + defense + ", " + magic_power + ", " + magic_resistance + ")");
//    }
//
//    private void updataCharacterRecord(String To, int value){
//        if (database == null){
//            createDatabase();
//        }
//
//
//        database.execSQL("UPDATE " + "Character"
//                + " SET "
//                + value
//                + " WHERE "
//                + "_id=" + To
//        );
//
//    }
//
//    public void update(){
//        for(int i=0; i < CHARACTER_NUMBER; i++)
//            for(int j=0; j < 6; j++){
//
//                database.execSQL("UPDATE " + "Character"
//                        + " SET "
//                        + character[i][j]
//                        + " WHERE "
//                        + "_id=" + i
//                );
//            }
//
//        for(int i=0; i < 4; i++){
//            database.execSQL("UPDATE " + "player"
//                    + " SET "
//                    + player[i]
//                    + " WHERE "
//                    + "_id=" + i
//            );
//        }
//    }
//
//    public void getData() {
//        Cursor cursor = database.rawQuery("select _id, name, age, mobile from emp", null);
//        int recordCount = cursor.getCount();
//        for (int i = 0; i < recordCount; i++) {
//            cursor.moveToNext();
//            for(int j=0; j < 6; j++) {
//                character[i][j] = cursor.getInt(j); // hp
//            }
//        }
//    }
//
//
//    public void deleteTable(int _id){
//        if (database == null){
//            createDatabase();
//        }
////        if (TableName == null){
////            //경고 메시지
////        }
//        String str = "DELETE FROM Character WHERE _id =" + _id;
//        database.execSQL(str);
//    }
//
//
//    public void init(String name){
//        createDatabase();
//        //createTable(name);
//    }

}
