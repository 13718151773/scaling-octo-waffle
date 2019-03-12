package com.baway.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.baway.sqlite.Sqlites;

/**
 * @Author：${张嘉鑫}
 * @Date：2019/3/8 11:31
 */
public class SQliteDao {
    private final SQLiteDatabase database;
    private Context context;

    public SQliteDao(Context context) {
        this.context = context;
        Sqlites sqlites = new Sqlites(context);
        database = sqlites.getWritableDatabase();
    }
    //添加数据库
    public long insert(String table, String nullColumnHack, ContentValues values) {
        return database.insert(table, nullColumnHack, values);
    }
    //查询数据库
    public Cursor query(String table, String[] columns, String selection,
                        String[] selectionArgs, String groupBy, String having,
                        String orderBy) {
        return database.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
    }
}
