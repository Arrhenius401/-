package com.work.serve;

import com.work.MainApplication;
import com.work.entity.TreatDoctor;
import com.work.io.DataSaverLoader;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class StageServe {

    public static ArrayList<TreatDoctor> treatDoctors = null;
    public static TreatDoctor td = null;

    public static void paint(Stage primaryStage,String title,String adress) throws IOException {
        //创建JAVAFX加载器
        FXMLLoader loader=new FXMLLoader();
        //获取当前类的类对象
        URL url= MainApplication.class.getResource(adress);
        //将文件的路径传递给 JAVAFX加载器
        loader.setLocation(url);
        //通过加载器的加载获取 应用画板类型对象
        AnchorPane anchorPane=loader.load();
        //创建一个场景
        Scene scene=new Scene(anchorPane);
        //设置场景标题
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        //显示场景
        primaryStage.show();
    }

}
