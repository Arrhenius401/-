package com.work.entity;

import java.io.Serializable;

//接诊医生
public class TreatDoctor extends Doctor {

    private String deptName;        //科室名
    private String treatLevel;      //看诊等级
    private double treatFee;        //挂号费
    private boolean isAvailable;    //是否有空

    public TreatDoctor(String UID,String account,String password,String name,int age,String sex,int catalog,
                       String deptName,String treatLevel,double treatFee,boolean isAvailable){
        this(UID,account,password,name,age,sex,catalog);
        this.deptName = deptName;
        this.treatLevel = treatLevel;
        this.treatFee = treatFee;
        this.isAvailable = isAvailable;
    }

    public TreatDoctor(String UID,String account,String password,String name,int age,String sex,int catalog){
        super(UID,account,password,name,age,sex,catalog);
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getTreatLevel() {
        return treatLevel;
    }

    public void setTreatLevel(String treatLevel) {
        this.treatLevel = treatLevel;
    }

    public double getTreatFee() {return treatFee;}

    public void setTreatFee(double treatFee) {this.treatFee = treatFee;}

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "TreatDoctor{" +
                super.toString() +
                "deptName='" + deptName + '\'' +
                ", treatLevel='" + treatLevel + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
