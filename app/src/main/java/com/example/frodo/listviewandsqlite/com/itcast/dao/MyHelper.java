package com.example.frodo.listviewandsqlite.com.itcast.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Frodo on 2016/10/16.
 * 创建数据库
 */

public class MyHelper extends SQLiteOpenHelper{
    public MyHelper(Context context){
        super(context, "itcast.db", null, 2);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS account(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name VARCHAR(20)," +
                "balance VARCHAR(20))");
        db.execSQL("CREATE TABLE IF NOT EXISTS account2(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name VARCHAR(20)," +
                "balance VARCHAR(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("alter table account add account varchar(20-)");
    }
}
