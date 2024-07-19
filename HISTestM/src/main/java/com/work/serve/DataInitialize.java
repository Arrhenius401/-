package com.work.serve;

import com.work.entity.*;
import com.work.io.DataSaverLoader;

import java.util.ArrayList;
import java.util.Date;

public class DataInitialize {

    //初始化看诊医生数据文件
    public static void TDInitialize(){
        TreatDoctor one = new TreatDoctor("TD001","lixiaoming","li123","李晓明",42,"男",1,"心血管科",
                "普通",30,false);
        TreatDoctor two = new TreatDoctor("TD002","HUA","hua123","华佗",73,"男",1,"神经外科",
                "主治医师",100,false);
        TreatDoctor three = new TreatDoctor("TD003","BIAN","bian123","扁鹊",55,"男",1,"心血管科",
                "住院医师",50,false);
        TreatDoctor four = new TreatDoctor("TD004","zhen","li123","李时珍",49,"男",1,"心血管科",
                "主任医师",80,false);
        TreatDoctor five = new TreatDoctor("TD005","jing","jing123","张仲景",37,"男",1,"皮肤科",
                "主任医师",80,false);
        TreatDoctor six = new TreatDoctor("TD006","wow","123123","王雅丽",56,"女",1,"皮肤科",
                "副主任医师",70,false);
        TreatDoctor seven = new TreatDoctor("TD007","zhr","1111","张浩然",29,"男",1,"神经外科",
                "普通",30,false);
        TreatDoctor eight = new TreatDoctor("TD008","cj","11111","陈静",28,"女",1,"儿科",
                "普通",30,false);
        TreatDoctor nine = new TreatDoctor("TD009","dcc","11111","陈密",33,"女",1,"心血管科",
                "住院医师",30,false);

        ArrayList<TreatDoctor> arrayList = new ArrayList<TreatDoctor>();
        arrayList.add(one);
        arrayList.add(two);
        arrayList.add(three);
        arrayList.add(four);
        arrayList.add(five);
        arrayList.add(six);
        arrayList.add(seven);
        arrayList.add(eight);
        arrayList.add(nine);

        try {
            DataSaverLoader.initSave(arrayList,DataSaverLoader.TD_EXAMPLE,DataSaverLoader.TD_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //初始化药房医生数据文件
    public static void PDInitialize(){
        PharmacyDoctor one = new PharmacyDoctor("PD001","1234","1234","医师甲",43,"男",2,1,true);
        PharmacyDoctor two = new PharmacyDoctor("PD002","1257","1257","医师乙",33,"男",2,2,true);
        PharmacyDoctor three= new PharmacyDoctor("PD003","000","000","医师丙",35,"男",2,1,true);

        ArrayList<PharmacyDoctor> arrayList = new ArrayList<PharmacyDoctor>();
        arrayList.add(one);
        arrayList.add(two);
        arrayList.add(three);

        try {
            DataSaverLoader.initSave(arrayList,DataSaverLoader.PD_EXAMPLE,DataSaverLoader.PD_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //初始化患者数据文件
    public static void PInitialize(){
        Patient one = new Patient("PA001","张三",56,"男","001","1998.6.25","中国","神经外科","华佗","主治医师","2024.6.25",true,30,1);
        Patient two = new Patient("PA002","李四",13,"男","002","2001.6.25","中国","儿科","陈静","普通","2024.6.25",true,70,1);
        Patient three = new Patient("PA003","王五",56,"男","003","1995.6.28","中国","神经外科","华佗","主治医师","2024.6.28",true,30,1);

        ArrayList<Patient> arrayList = new ArrayList<Patient>();
        arrayList.add(one);
        arrayList.add(two);
        arrayList.add(three);

        try {
            DataSaverLoader.initSave(arrayList,DataSaverLoader.P_EXAMPLE,DataSaverLoader.P_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //初始化药物数据文件
    public static void MInitialize(){

        Medicine one = new Medicine("ME001","青霉素",299,100, "溶液内含 10,000 单位/mL 青霉素和 10,000 μg/mL 链霉素。",true);
        Medicine two = new Medicine("ME002","阿莫西林胶囊",19.1,100, "阿莫西林以三水合物存在。含0.25g*24粒。",true);
        Medicine three = new Medicine("ME003","板蓝根颗粒",12.5,500, "10g*20袋",false);
        Medicine four = new Medicine("ME004","奥美拉唑肠溶胶囊",21,50, "20mg×14粒",true);

        ArrayList<Medicine> arrayList = new ArrayList<Medicine>();
        arrayList.add(one);
        arrayList.add(two);
        arrayList.add(three);
        arrayList.add(four);

        try {
            DataSaverLoader.initSave(arrayList,DataSaverLoader.M_EXAMPLE,DataSaverLoader.M_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DataInitialize.TDInitialize();
        DataInitialize.PDInitialize();
        DataInitialize.PInitialize();
        DataInitialize.MInitialize();
        System.out.println("数据初始化成功");

    }

}
