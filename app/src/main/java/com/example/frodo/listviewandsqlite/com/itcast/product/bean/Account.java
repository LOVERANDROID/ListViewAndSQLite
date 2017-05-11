package com.example.frodo.listviewandsqlite.com.itcast.product.bean;

/**
 * Created by Frodo on 2016/10/16.
 */

public class Account {
    private Long id;
    private String name;
    private String balance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
    public Account(Long id, String name, String balance){
        this.id = id;
        this.name = name;
        this.balance = balance;
    }
    public Account(String name, String balance){
        super();
        this.name = name;
        this.balance = balance;
    }
    public Account(){
        super();
    }
    public String toString(){
        return "[序号：" + id + ", 商品名称：" + name + ", 余额：" + balance + "]";
    }
}
