package com.work.controller;

import com.work.entity.Doctor;
import com.work.serve.*;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private TextField userName;
    @FXML
    private PasswordField userPassword;

    public static String loginUID;      //登录账号的UID
    public static Doctor loginDoctor;   //登录的对象
    public static Stage primaryStage;

    public void login()throws Exception{
        LoginServe loginService=new LoginServe();
        int flag=loginService.login(userName.getText(),userPassword.getText());
        //如果用户名和密码不匹配，弹警告窗口
        //如果用户名和密码匹配，进入下一个页面
        if(flag==0){
            StageServe.paint(primaryStage,"主界面","/com/work/MainView.fxml");
        }else if(flag==1){
            AlertController.showAlert(primaryStage,"登录失败","密码错误","请重试");
        }else {
            AlertController.showAlert(primaryStage,"登录失败","账号不存在","请先联系管理员注册账号");
        }
    }
}
