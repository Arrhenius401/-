package com.work.entity;

import com.work.io.DataSaverLoader;

import java.io.Serializable;
import java.util.ArrayList;

public class HisItem implements Serializable {

    private static final long serialVersionUID = 1L;        //防InvalidClassException
    private String UID;     //每个医生，每一个患者，每种药物都有独特的编号，用于方便有关数据的操作
    private String name;    //名字

    public HisItem(){
    }

    public HisItem(String UID, String name) {
        this.UID = UID;
        this.name = name;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    //去除对象前的字母标识，得到其UID的数字部分
    public int getNumUID(){
        int number = Integer.parseInt(this.getUID().substring(2));
        return number;
    }

    public static void main(String[] args) {
        String UID = HisItem.getNewUID(DataSaverLoader.M_EXAMPLE,DataSaverLoader.M_PATH);
        System.out.println(UID);
    }

    //根据文件存储的最后一个对象，生成新的UID(需引用DataSaverLoader中的示范对象)
    public static <E extends HisItem> String getNewUID(E e,String adress){
        //第一个参数用于确定泛型对象，第二个参数是文件的地址
        String newUID = null;
        try {
            ArrayList<E> arrayList = DataSaverLoader.load(e,adress);
            int lastNumber = 0;

            if (arrayList.size()!=0){
                lastNumber = arrayList.get(arrayList.size()-1).getNumUID();
            }
            if(lastNumber==999){
                System.out.println("超出最大数字！");
            }else{
                lastNumber++;
                newUID = String.format("%03d",lastNumber);
                if (DataSaverLoader.TD_EXAMPLE.getClass()==e.getClass()) {
                    newUID = "TD" + newUID;
                } else if (DataSaverLoader.P_EXAMPLE.getClass()==e.getClass()) {
                    newUID = "PA" + newUID;
                } else if (DataSaverLoader.PD_EXAMPLE.getClass()==e.getClass()) {
                    newUID = "PD" + newUID;
                } else if (DataSaverLoader.M_EXAMPLE.getClass()==e.getClass()) {
                    newUID = "ME" + newUID;
                } else {
                    System.out.println("输入不合法");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return newUID;
    }

}
