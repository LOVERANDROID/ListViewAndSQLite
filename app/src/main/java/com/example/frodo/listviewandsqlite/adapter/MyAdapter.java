package com.example.frodo.listviewandsqlite.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.frodo.listviewandsqlite.R;
import com.example.frodo.listviewandsqlite.com.itcast.dao.AccountDao;
import com.example.frodo.listviewandsqlite.com.itcast.product.bean.Account;

import java.util.List;

/**
 * Created by Lemon on 2017/4/18.
 */

//自定义个是适配器。把数据装在List View工具中
public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<Account> accountList;
    private AccountDao dao;
    public MyAdapter(Context context, List<Account> accountList){
        this.context = context;
        this.accountList = accountList;
    }
    //获取条目数目
    public int getCount() {
        return accountList.size();
    }

    //根据位置获取对象
    public Object getItem(int position) {
        return accountList.get(position);
    }

    //根据位置获取对象id
    public long getItemId(int positon) {
        return positon;
    }

    //获取一个条目视图
    public View getView(int position, View converView, ViewGroup parent) {
        //重用convertView
        View item = LayoutInflater.from(context).inflate(R.layout.item, null);
        //获取该视图的Text View
        TextView nameTV = (TextView) item.findViewById(R.id.TV_name);
        TextView balanceTV = (TextView) item.findViewById(R.id.TV_balance);
        //根据当前位置获取ACount对象
        final Account a = accountList.get(position);
        //把Account对象中的数据放到TextView中

        nameTV.setText(a.getName());
        balanceTV.setText(a.getBalance().toString());//TextView.setText().toString
        // (error：No package identifier when getting value for resource number错误原因见云笔记)

        return item;
    }
}