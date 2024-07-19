package com.work;

import com.work.controller.*;
import com.work.serve.StageServe;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;

public class MainApplication extends Application {

    //启动设置方法
    @Override
    public void start(Stage primaryStage) throws Exception {
        //将stage传递给LoginController使用
        LoginController.primaryStage=primaryStage;
        MainController.primaryStage=primaryStage;
        RegistController.primaryStage=primaryStage;
        DiagnoseController.primaryStage=primaryStage;
        PatientViewController.primaryStage=primaryStage;
        AccountViewController.primaryStage=primaryStage;
        DoctorViewController.primaryStage=primaryStage;
        SignUpViewController.primaryStage=primaryStage;
        WorkQuitViewController.primaryStage=primaryStage;
        MedicineController.primaryStage=primaryStage;
        MedicineManageController.primaryStage=primaryStage;
        MedicineChangeController.primaryStage=primaryStage;
        StageServe.paint(primaryStage,"登录界面","/com/work/LogInView.fxml");//设置场景标题

    }

    public static void main(String[] args) {
        launch();
    }
}
