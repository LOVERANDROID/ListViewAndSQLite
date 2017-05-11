package com.example.frodo.listviewandsqlite.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import com.example.frodo.listviewandsqlite.R;
import com.example.frodo.listviewandsqlite.adapter.MyAdapter;
import com.example.frodo.listviewandsqlite.com.itcast.dao.AccountDao;
import com.example.frodo.listviewandsqlite.com.itcast.product.bean.Account;

import java.text.SimpleDateFormat;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //输入商品名称和金额的EditText
    private EditText nameET, balanceET;
    //需要适配的数据集合
    private List<Account> accountList;
    //数据库的操作类
    private AccountDao dao;
    //适配器
    private MyAdapter adapter;
    private ListView list;
    private ImageView add_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //初始化控件
        initView();
        add_image.setOnClickListener(this);
        dao = new AccountDao(this);
        //从数据库中获取所有的数据
        accountList = dao.queryAll();
        adapter = new MyAdapter(this, accountList);
        list.setAdapter(adapter);
    }

    private void initView() {

        list = (ListView) findViewById(R.id.listView);
        list.setOnItemClickListener(new MyOnItemClickListener());
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                //删除前先弹出对话框
                DialogInterface.OnClickListener listener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //先删除数据库，在删除List<Account>中的数据，顺序不能变，否则会出现数组越界报错
                                dao.delete(accountList.get(position).getId());
                                accountList.remove(accountList.get(position));
                                adapter.notifyDataSetChanged();
                            }
                        };
                //创建对话框
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("确定删除吗?");
                builder.setPositiveButton("确定", listener);
                builder.setNegativeButton("取消", null);
                builder.show();
                return true;
            }
        });
        add_image = (ImageView) findViewById(R.id.iv_add);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, EditNoteActivity.class);
        startActivityForResult(intent, 1);
    }

    //list View的Item点击事件
    private class MyOnItemClickListener implements AdapterView.OnItemClickListener {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(MainActivity.this, EditNoteActivity.class);
            intent.putExtra("note", accountList.get(position).getName());
            startActivityForResult(intent, 1);
            dao.delete(accountList.get(position).getId());
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                dao = new AccountDao(this);
                //从数据库中获取所有的数据
                accountList = dao.queryAll();
                adapter = new MyAdapter(this, accountList);
                list.setAdapter(adapter);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}








