import com.work.entity.Medicine;
import com.work.entity.Patient;
import com.work.entity.PharmacyDoctor;
import com.work.io.DataSaverLoader;
import java.util.ArrayList;

public class TestIO {
    //2024.6.22  通过测试
    //2024.6.24  新的DataSaverLoader通过测试

    public static final String path = "src/main/resources/data/Medicine.txt";
    public static Medicine a = new Medicine("ME001","a",100,100,"wow",true);
    public static Medicine b = new Medicine("ME002","b",222,200,"111",true);
    public static Medicine c = new Medicine("ME003","c",111,400,"190",true);
    public static Medicine d = new Medicine("ME004","d",678,467,"124",true);

    //测试load方法
    public static void testLaod()throws Exception{
        ArrayList<Medicine> arrayList01 = DataSaverLoader.load(DataSaverLoader.M_EXAMPLE,path);
        for (int i=0;i<arrayList01.size();i++){
            Medicine item = arrayList01.get(i);
            System.out.println(item.toString());
        }
    }
    //测试第一种Save方法
    public static void testSave(Medicine medicine)throws Exception{
        System.out.println("************************************");
        System.out.println("测试save");
        DataSaverLoader.save(medicine,path);     //使用的相对路径要和你当前的working directory路径能组成一个绝对路径
        TestIO.testLaod();
    }

    //测试find方法
    public static void testFind(String o)throws Exception{
        System.out.println("************************************");
        System.out.println("测试find方法");
        a = null;
        a = DataSaverLoader.find(DataSaverLoader.M_EXAMPLE,path,o);
        if (a!=null){
            System.out.println(a.toString());
        }else {
            System.out.println("查无此药");
        }
    }

    //测试delete方法
    public static void testDelete(String o)throws Exception{
        System.out.println("************************************");
        System.out.println("测试delete方法");
        System.out.println("删除前");
        TestIO.testLaod();
        Boolean D_result01=DataSaverLoader.delete(DataSaverLoader.M_EXAMPLE,path,o);
        if(D_result01){
            System.out.println("删除成功");
        }else{
            System.out.println("删除失败！！！");
        }
        System.out.println("删除后");
        TestIO.testLaod();
    }

    //测试change方法
    public static void testChange(Medicine medicine)throws Exception{
        System.out.println("************************************");
        System.out.println("测试change方法");
        System.out.println("更改前");
        TestIO.testLaod();
        Boolean D_result01=DataSaverLoader.change(medicine,path);
        if(D_result01){
            System.out.println("修改成功");
        }else{
            System.out.println("修改失败！！！");
        }
        System.out.println("修改后");
        TestIO.testLaod();
    }


    public static void main(String[] args) {
        try {
            ArrayList<Medicine> arrayList01 = DataSaverLoader.load(DataSaverLoader.M_EXAMPLE,DataSaverLoader.M_PATH);
            for (int i=0;i<arrayList01.size();i++){
                Medicine item = arrayList01.get(i);
                System.out.println(item.toString());
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
