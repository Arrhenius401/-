package com.work.entity;


import java.io.Serializable;

//药房医生
public class PharmacyDoctor extends Doctor  {
    private int window;     //药房窗口
    private boolean isAvailable;    //是否有空

    public PharmacyDoctor(String UID,String account,String password,String name,int age,String sex,int catalog,int window,boolean isAvailable){
        this(UID,account,password,name,age,sex,catalog);
        this.window = window;
        this.isAvailable = isAvailable;
    }

    public PharmacyDoctor(String UID,String account,String password,String name,int age,String sex,int catalog){
        super(UID,account,password,name,age,sex,catalog);
    }

    public int getWindow() {
        return window;
    }

    public void setWindow(int window) {
        this.window = window;
    }

    public boolean isAvailable() {return isAvailable;}

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "PharmacyDoctor{" +
                super.toString() +
                "window=" + window +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
