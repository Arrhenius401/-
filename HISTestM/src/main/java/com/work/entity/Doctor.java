package com.work.entity;


import com.work.io.DataSaverLoader;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

public class Doctor extends HisItem{

    private String account;     //登录账号(唯一的)
    private String password;    //登录密码
    private int age;            //医生年龄
    private String sex;     //医生性别
    private int catalog;    //医生种类（1:看诊医生 2:药房医生）

    //新增
    private ArrayList<String> workTime;
    private ArrayList<String> quitTime;

    public Doctor(String UID,String account, String password, String name, int age, String sex, int catalog) {
        super(UID,name);
        this.account = account;
        this.password = password;
        this.age = age;
        this.sex = sex;
        this.catalog = catalog;

        workTime = new ArrayList<String>();
        quitTime = new ArrayList<String>();
    }

    public void setAccount(String a){this.account=a;}

    public String getAccount(){return account;}

    public void setPassword(String a){this.password=a;}

    public String getPassword(){return password;}

    public void setAge(int a){this.age=a;}

    public int getAge(){return age;}

    public void setSex(String a){this.sex=a;}

    public String getSex(){return sex;}

    public void setCatalog(int a){this.catalog=a;}

    public int getCatalog(){return catalog;}

    public ArrayList<String> getWorkTime() {
        return workTime;
    }

    public void setWorkTime(ArrayList<String> workTime) {
        this.workTime = workTime;
    }

    public ArrayList<String> getQuitTime() {
        return quitTime;
    }

    public void setQuitTime(ArrayList<String> quitTime) {
        this.quitTime = quitTime;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "UID='" + this.getUID() +'\'' +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                "name='" + this.getName() +'\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", catalog=" + catalog +
                '}';
    }
}
