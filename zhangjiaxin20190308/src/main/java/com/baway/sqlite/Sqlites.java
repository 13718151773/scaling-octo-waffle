package com.baway.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @Author：${张嘉鑫}
 * @Date：2019/3/8 11:03
 */
public class Sqlites extends SQLiteOpenHelper {
    public Sqlites(Context context) {
        super(context,"User.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL("create table news(title text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
