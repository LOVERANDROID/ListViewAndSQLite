package com.example.frodo.listviewandsqlite.com.itcast.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.frodo.listviewandsqlite.com.itcast.product.bean.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Frodo on 2016/10/16.
 * 数据库操作类
 */
public class AccountDao  {
    private MyHelper helper;
    public AccountDao(Context context){
        helper = new MyHelper(context);
    }
    //插入数据
    public void insert(Account account){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", account.getName());
        values.put("balance", account.getBalance());
        long id = db.insert("account", null, values);
        account.setId(id);
        db.close();
    }
    //更新数据
    public void update(Account account){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", account.getName());
        values.put("balance", account.getBalance());
        int count = db.update("account", values, "_id = ?",
                new String[]{account.getId()+""});
    }
    //查询所有数据倒序排列
    public List<Account> queryAll(){
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.query("account", null, null, null, null, null, "_id DESC");//desc为降序排列，asc为升序排列
        List<Account> list = new ArrayList<>();
        while (cursor.moveToNext()){
            long id = cursor.getLong(cursor.getColumnIndex("_id"));
            String name = cursor.getString(1);
            String balance = cursor.getString(2);
            list.add(new Account(id, name, balance));
        }
        cursor.close();
        db.close();
        return list;
    }
    //根据id删除数据
    public int delete(long id){
        SQLiteDatabase db = helper.getWritableDatabase();
        //按条件删除指定表中的数据，返回受影响的行数
        int count = db.delete("account", "_id = ?", new String[]{id + ""});
        db.close();
        return count;
    }

}
