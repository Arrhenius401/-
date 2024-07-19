package com.work.controller;

import com.work.entity.HisItem;
import com.work.entity.PharmacyDoctor;
import com.work.entity.TreatDoctor;
import com.work.io.DataSaverLoader;
import com.work.serve.AccountServe;
import com.work.serve.StageServe;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

//账号信息-账号总览
public class SignUpViewController implements Initializable {

    @FXML
    private RadioButton treatDoctor;

    @FXML
    private RadioButton phramacyDoctor;

    @FXML
    private Button save;

    @FXML
    private Button back;

    @FXML
    private TextField name;

    @FXML
    private TextField UID;

    @FXML
    private TextField sex;

    @FXML
    private TextField age;

    @FXML
    private TextField doctorDept;

    @FXML
    private TextField doctorLevel;

    @FXML
    private TextField account;

    @FXML
    private TextField password;

    @FXML
    private TextField window;

    @FXML
    private TextField doctorFee;

    public static Stage primaryStage;
    private static ArrayList<TreatDoctor> treatDoctors;
    private static ArrayList<PharmacyDoctor> pharmacyDoctors;

    public boolean saveData() throws Exception {
        String doctorUID = UID.getText();
        if(doctorUID.substring(0,2).equals("TD")){
            //"=="用于判读首地址,作用于数值的比较;"equals"则是比较变量中具体的数值,如字符串等
            TreatDoctor pointTD = new TreatDoctor("无","无","无","无",0,"无",1,"无","无",10,false);
            pointTD.setUID(UID.getText());
            pointTD.setName(name.getText());
            pointTD.setDeptName(doctorDept.getText());
            pointTD.setTreatLevel(doctorLevel.getText());
            pointTD.setPassword(password.getText());

            //检查需控制输入类型的数据
            try {
                pointTD.setTreatFee(Double.parseDouble(doctorFee.getText()));
                pointTD.setAge(Integer.parseInt(age.getText()));
                if(sex.getText().equals("男")||sex.getText().equals("女")){
                    pointTD.setSex(sex.getText());
                }else{
                    throw new Exception();
                }
            }catch (Exception e){
                e.printStackTrace();
                AlertController.showAlert(primaryStage,"保存失败","数据类型存在错误！","请检查性别，年龄和接诊费用三项信息");
                return false;
            }


            //检查是否存在相同账户名称

            AccountServe accountServe = new AccountServe();
            if(accountServe.isSameAccount(account.getText())){
                AlertController.showAlert(primaryStage,"保存失败","账户名称已存在！","请修改账户名称");
                return false;
            }else if(account.getText().equals("")){
                AlertController.showAlert(primaryStage,"保存失败","账户名称不可为空！","请填写账户名称");
                return false;
            }else {
                pointTD.setAccount(account.getText());
            }


            //保存对象
            DataSaverLoader.save(pointTD,DataSaverLoader.TD_PATH);
            AlertController.showAlert(primaryStage,"保存成功","数据保存成功！","已保存至本地文件中");
            emptyData();
            return true;

        }else if(doctorUID.substring(0,2).equals("PD")){
            PharmacyDoctor pointPD = new PharmacyDoctor("无","无","无","无",0,"无",2,1,false);
            pointPD.setUID(UID.getText());
            pointPD.setName(name.getText());
            pointPD.setPassword(password.getText());

            //检查需控制输入类型的数据
            try {
                pointPD.setAge(Integer.parseInt(age.getText()));
                pointPD.setWindow(Integer.parseInt(window.getText()));
                if(sex.getText().equals("男")||sex.getText().equals("女")){
                    pointPD.setSex(sex.getText());
                }else{
                    throw new Exception();
                }
            }catch (Exception e){
                e.printStackTrace();
                AlertController.showAlert(primaryStage,"保存失败","数据类型存在错误！","请检查性别，窗口和年龄三项信息");
                return false;
            }


            //检查是否存在相同账户名称

            AccountServe accountServe = new AccountServe();
            if(accountServe.isSameAccount(account.getText())){
                AlertController.showAlert(primaryStage,"保存失败","账户名称已存在！","请修改账户名称");
                return false;
            }else if(account.getText().equals("")){
                AlertController.showAlert(primaryStage,"保存失败","账户名称不可为空！","请填写账户名称");
                return false;
            }else{
                pointPD.setAccount(account.getText());
            }

            //保存对象
            DataSaverLoader.save(pointPD,DataSaverLoader.PD_PATH);
            AlertController.showAlert(primaryStage,"保存成功","数据保存成功！","已保存至本地文件中");
            emptyData();
            return true;
        }

        AlertController.showAlert(primaryStage,"保存失败","数据错误！","请检查写入的数据");
        return false;
    }

    //将非必要信息默认处理
    public void basicData(){
        doctorDept.setText("无");
        doctorLevel.setText("无");
        doctorFee.setText("0");
        window.setText("0");
    }

    //注册后清空信息
    public void emptyData(){
        name.setText("");
        age.setText("");
        sex.setText("");
        account.setText("");
        password.setText("");
        UID.setText("");
    }

    public void back() throws IOException {
        StageServe.paint(primaryStage,"主界面","/com/work/MainView.fxml");
    }

    public boolean isEmpty(){
        boolean result = false;
        if(name.getText().equals("")){
            result = true;
        }
        if (account.getText().equals("")){
            result = true;
        }
        if (password.getText().equals("")){
            result = true;
        }
        if (sex.getText().equals("")){
            result = true;
        }
        if (age.getText().equals("")){
            result = true;
        }
        return result;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //使医生列别的两RadioButton绑定
        ToggleGroup doctorCatalog = new ToggleGroup();
        this.phramacyDoctor.setToggleGroup(doctorCatalog);
        this.treatDoctor.setToggleGroup(doctorCatalog);

        treatDoctor.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue){
                    basicData();
                    UID.setText(HisItem.getNewUID(DataSaverLoader.TD_EXAMPLE,DataSaverLoader.TD_PATH));
                }
            }
        });
        phramacyDoctor.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue){
                    basicData();
                    UID.setText(HisItem.getNewUID(DataSaverLoader.PD_EXAMPLE,DataSaverLoader.PD_PATH));
                }
            }
        });
    }
}
