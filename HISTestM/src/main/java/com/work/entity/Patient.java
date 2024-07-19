package com.work.entity;

import java.io.Serializable;
import java.util.Date;

public class Patient extends HisItem {

    private int age;            //患者年龄
    private String sex;         //患者性别
    private String sUID;            //身份证号
    private String birth;           //出生日期
    private String adress;          //家庭住址
    private String deptName;        //挂号科室
    private String doctorName; //挂号医生
    private String treatLevel;      //挂号级别
    private String treatDate;         //挂号日期
    private boolean isbook;         //是否需要病历本
    private String diagiosis;       //诊断结果
    private double treatFee;        //挂号费
    private String prescription;    //处方药品（分号分割）
    private double drugPrice;       //药品总价
    private int visitState;         //看诊状态（1:已挂号 2:看诊结束 3:已取药 4:已退号）

    public Patient(String UID,String name, int age, String sex, String sUID, String birth, String adress,
                   String deptName, String doctorName, String treatLevel, String treatDate, boolean isbook,
                   double treatFee,  int visitState) {
        super(UID,name);
        this.age = age;
        this.sex = sex;
        this.sUID = sUID;
        this.birth = birth;
        this.adress = adress;
        this.deptName = deptName;
        this.doctorName = doctorName;
        this.treatLevel = treatLevel;
        this.treatDate = treatDate;
        this.isbook = isbook;
        this.treatFee = treatFee;
        this.visitState = visitState;
    }

    public Patient(String UID, String name, int age, String sex, String sUID, String birth, String adress,
                   String deptName, String doctorName, String treatLevel, String treatDate, boolean isbook,
                   String diagiosis, double treatFee, String prescription, double drugPrice, int visitState) {
        super(UID,name);
        this.age = age;
        this.sex = sex;
        this.sUID = sUID;
        this.birth = birth;
        this.adress = adress;
        this.deptName = deptName;
        this.doctorName = doctorName;
        this.treatLevel = treatLevel;
        this.treatDate = treatDate;
        this.isbook = isbook;
        this.diagiosis = diagiosis;
        this.treatFee = treatFee;
        this.prescription = prescription;
        this.drugPrice = drugPrice;
        this.visitState = visitState;
    }

    public void setAge(int a){this.age=a;}

    public int getAge(){return age;}

    public void setSex(String a){this.sex=a;}

    public String getSex(){return sex;}

    public void setsUID(String a){this.sUID=a;}

    public String getsUID(){return sUID;}

    public String getBirth() {return birth;}

    public void setBirth(String birth) {this.birth = birth;}

    public void setAdress(String a){this.adress=a;}

    public String getAdress(){return adress;}

    public void setDeptName(String a){this.deptName=a;}

    public String getDeptName(){return deptName;}

    public void setDoctorName(String a){this.doctorName=a;}

    public String getDoctorName(){return doctorName;}

    public void setTreatLevel(String a){this.treatLevel=a;}

    public String getTreatLevel(){return treatLevel;}

    public void setTreatDate(String a){this.treatDate=a;}

    public String getTreatDate(){return treatDate;}

    public void setIsbook(boolean a){this.isbook=a;}

    public boolean getIsbook(){return isbook;}

    public boolean isIsbook() {return isbook;}

    public String getDiagiosis() {return diagiosis;}

    public void setDiagiosis(String diagiosis) {this.diagiosis = diagiosis;}

    public double getTreatFee() {return treatFee;}

    public void setTreatFee(double treatFee) {this.treatFee = treatFee;}

    public String getPrescription() {return prescription;}

    public void setPrescription(String prescription) {this.prescription = prescription;}

    public double getDrugPrice() {return drugPrice;}

    public void setDrugPrice(double drugPrice) {this.drugPrice = drugPrice;}

    public int getVisitState() {return visitState;}

    public void setVisitState(int visitState) {this.visitState = visitState;}

    @Override
    public String toString() {
        return "Patient{" +
                "UID='" + this.getUID() +'\'' +
                "name='" + this.getName() + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", sUID='" + sUID + '\'' +
                ", birth='" + birth + '\'' +
                ", adress='" + adress + '\'' +
                ", deptName='" + deptName + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", treatLevel='" + treatLevel + '\'' +
                ", treatDate='" + treatDate + '\'' +
                ", isbook=" + isbook +
                ", diagiosis='" + diagiosis + '\'' +
                ", treatFee=" + treatFee +
                ", prescription='" + prescription + '\'' +
                ", drugPrice=" + drugPrice +
                ", visitState=" + visitState +
                '}';
    }
}
