package com.work.io;

import com.work.entity.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class DataSaverLoader {

    //用于给load,find,delete两个方法传第一个参数的值
    public static final Medicine M_EXAMPLE = new Medicine("ME955","example",999,999,"999",true);
    public static final TreatDoctor TD_EXAMPLE = new TreatDoctor("TD999","admin","123","张三",999,
            "男",1,"心脑血管","专家",30,true);
    public static final PharmacyDoctor PD_EXAMPLE = new PharmacyDoctor("PD999","admin","123","李四",999,"男",1,999,false);
    public static final Patient P_EXAMPLE = new Patient("PA999","李四", 999, "男", "99999", "2000.6.25","中国", "心脑血管室",
            DataSaverLoader.TD_EXAMPLE.getName(), "轻微", "2024.6.25", false, "挺好", 50, "无", 0, 4);

    //默认保存的路径
    public static final String M_PATH = "src/main/resources/data/Medicine.txt";
    public static final String PD_PATH = "src/main/resources/data/PharmacyDoctor.txt";
    public static final String TD_PATH = "src/main/resources/data/TreatDoctor.txt";
    public static final String P_PATH = "src/main/resources/data/Patient.txt";

    //加载对象，但是是将目标文件下的所有对象全部加载(后几个函数的基石）
    public static <E extends HisItem> ArrayList<E> load(E e,String adress)throws  Exception{
        //第一个参数用于确定泛型对象，第二个参数是文件的地址
        ArrayList<E> arrayList = new ArrayList<E>();
        File file = new File(adress);
        if (!file.exists()){
            //如果文件不存在，则重新创建文件
            file.createNewFile();
        }else if(file.length()!=0){
            //如果文件不为空，则将文件的对象传入arraylist
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(adress));
            E item = null;
            while((item=(E)is.readObject())!=null){
                arrayList.add(item);
            }
        }
        return arrayList;
    }

    //存储单一对象,追加在原有对象后
    public static <E extends HisItem> void save(E e,String adress)throws Exception{
        //第一个参数是需要存储的对象，第二个参数是文件的地址
        //判断UID是否续上
        ArrayList<E> arrayList=DataSaverLoader.load(e,adress);
        String UID = HisItem.getNewUID(e,adress);
        if(UID.equals(e.getUID())){
            arrayList.add(e);
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(adress));
            for(int i=0;i<arrayList.size();i++){
                os.writeObject(arrayList.get(i));
            }
            os.writeObject(null);       //用于判断文件是否结束
        }else{
            System.out.println("UID不合规，非法输入！");
            throw new Exception();
        }

    }

    //用于初始化
    public static <E extends HisItem> void initSave(ArrayList<E> arrayList,E e,String adress)throws Exception{
        //第一个参数是需要存储的对象，第二个参数用于确定泛型对象，第三个参数是文件的地址
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(adress));
        for(int i=0;i<arrayList.size();i++){
            os.writeObject(arrayList.get(i));
        }
        os.writeObject(null);       //用于判断文件是否结束
    }

    //查找对象
    public static <E extends HisItem> E find(E e, String adress, String depend)throws Exception{
        //第一个参数用于所引用的load方法,即确定泛型的类型;第二个参数是文件的地址;第三个参数是用于比较的对象,为UID
        ArrayList<E> arrayList=DataSaverLoader.load(e,adress);
        for (int i=0;i<arrayList.size();i++){
            E item = arrayList.get(i);
            if(item.getUID().equals(depend)){
                return item;
            }
        }
        return null;
    }

    //专门查找医生类型的对象
    public static <E extends Doctor> E findDoctor(E e,String adress ,String depend,boolean isUID)throws Exception{
        //第一个参数用于所引用的load方法,即确定泛型的类型;第二个参数是文件的地址;第三个参数是用于比较的对象,为UID或者账号名称;第四个参数用于判断第三个参数是否的UID
        ArrayList<E> arrayList= DataSaverLoader.load(e,adress);
        if(isUID){
            for (int i=0;i<arrayList.size();i++){
                E item = arrayList.get(i);
                if(item.getUID().equals(depend)){
                    return item;
                }
            }
        }else{
            for (int i=0;i<arrayList.size();i++){
                E item = arrayList.get(i);
                if(item.getAccount().equals(depend)){
                    return item;
                }
            }
        }

        return null;
    }

    //删除对象,删除后使用UID的find方法找到的对象,极有可能不是目标对象
    //医生,患者的UID不可删,仅能使用与药物的UID上
    public static <E extends HisItem> boolean delete (E e, String adress, String UID)throws Exception{
        //第一个参数用于所引用的load方法，即确定泛型的类型;第二个参数是文件的地址;第三个参数是用于比较的对象，由于重载了equals方法，一般是String类型的对象(UID)
        ArrayList<E> arrayList=DataSaverLoader.load(e,adress);
        for (int i=0;i<arrayList.size();i++){
            E item = arrayList.get(i);
            if(item.getUID().equals(UID)){
                //从后往前替换UID
                for(int m=(arrayList.size()-1);m>i;m--){
                    arrayList.get(m).setUID(arrayList.get(m-1).getUID());
                }
                arrayList.remove(item);
                ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(adress));
                for(int n=0;n<arrayList.size();n++){
                    os.writeObject(arrayList.get(n));
                }
                os.writeObject(null);
                return true;
            }
        }
        return false;
    }

    //修改对象
    public static <E extends  HisItem> boolean change(E e, String adress)throws Exception{
        //第一个参数是修改后的成品;第二个参数是文件的地址
        ArrayList<E> arrayList=DataSaverLoader.load(e,adress);

        for(int i=0;i<arrayList.size();i++){
            E item = arrayList.get(i);
            if(item.getUID().equals(e.getUID())){
                arrayList.set(i,e);
                DataSaverLoader.initSave(arrayList,e,adress);
            }


        }
        return false;
    }

}
