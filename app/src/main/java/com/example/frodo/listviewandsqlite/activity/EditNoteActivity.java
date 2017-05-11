package com.example.frodo.listviewandsqlite.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.frodo.listviewandsqlite.R;
import com.example.frodo.listviewandsqlite.adapter.MyAdapter;
import com.example.frodo.listviewandsqlite.com.itcast.dao.AccountDao;
import com.example.frodo.listviewandsqlite.com.itcast.product.bean.Account;

import java.text.SimpleDateFormat;
import java.util.List;

public class EditNoteActivity extends AppCompatActivity {
    private EditText editText;
    private AccountDao dao;
    private List<Account> accountList;
    private MyAdapter adapter;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        Init();
        dao = new AccountDao(this);
        Intent intent = getIntent();
        //Bundle bundle = this.getIntent().getExtras();
        // String str = intent.getStringExtra("note");
        String str = intent.getStringExtra("note");
        editText.setText(str);
    }

    private void Init() {
        editText = (EditText) findViewById(R.id.editText);
        save = (Button) findViewById(R.id.button);
//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                add();
//            }
//        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                add();
                Intent intent = new Intent();
                setResult(1, intent);
                //finish();
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    private String GetDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日  HH:mm");
        String str = format.format(System.currentTimeMillis());
        return str;
    }

    public void add() {
        String name = editText.getText().toString();
        adapter = new MyAdapter(this, accountList);
        if (!name.isEmpty()) {
            Account a = new Account(name, GetDate());
            dao.insert(a);//插入数据
//            accountList.add(a);//插入集合
            Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show();
        }
    }
}
