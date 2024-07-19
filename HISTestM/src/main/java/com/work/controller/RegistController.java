package com.work.controller;

import com.work.entity.Doctor;
import com.work.entity.HisItem;
import com.work.entity.Patient;
import com.work.entity.TreatDoctor;
import com.work.io.DataSaverLoader;
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
import java.util.*;

public class RegistController implements Initializable {

    @FXML
    private TextField UID;      //UID

    @FXML
    private TextField name;     //患者名字

    @FXML
    private TextField age;      //患者年龄

    @FXML
    private TextField sUID;     //患者身份证号

    @FXML
    private TextField adress;       //患者家庭住址

    @FXML
    private ComboBox deptName;      //挂号科室

    @FXML
    private ComboBox treatLevel;    //挂号级别

    @FXML
    private ComboBox doctorName;    //接诊医生

    @FXML
    private CheckBox isBook;    //病历本

    @FXML
    private Label treatFee;     //应收

    @FXML
    private TextField receipt;      //实收

    @FXML
    private Label refund;       //找零

    @FXML
    private Label checker;      //收费员

    @FXML
    private RadioButton male;   //男按钮

    @FXML
    private RadioButton female; //女按钮

    @FXML
    private DatePicker birth;   //出生日期

    @FXML
    private DatePicker treatDate;   //接诊日期

    public static Stage primaryStage;
    private static ArrayList<TreatDoctor> treatDoctors;



    //添加新的患者信息
    public void addPatient()throws Exception{
        String UID = HisItem.getNewUID(DataSaverLoader.P_EXAMPLE,DataSaverLoader.P_PATH);
        String name = this.name.getText();
        String sex;

        if(this.male.isSelected()){
            sex = "男";
        }else {
            sex = "女";
        }
        String sUID = this.sUID.getText();
        String birth = this.birth.getValue().toString();
        String adress = this.adress.getText();
        String deptName = this.deptName.getValue().toString();
        String doctorName = this.doctorName.getValue().toString();
        String treatLevel = this.treatLevel.getValue().toString();
        String treatDate = this.treatDate.getValue().toString();
        boolean isbook = isBook.isSelected();

        //检查数据信息是否填写异常
        try{
            double treatFee = Double.parseDouble(this.treatFee.getText().substring(0,this.treatFee.getText().length()-1));
            int age = Integer.parseInt(this.age.getText());
            Patient p = new Patient(UID,name,age,sex,sUID,birth,adress,deptName,doctorName,
                    treatLevel,treatDate,isbook,treatFee,1);

            DataSaverLoader.save(p,DataSaverLoader.P_PATH);
            AlertController.showAlert(primaryStage,"注册成功","患者信息已录入！","请准时看诊。");
            StageServe.paint(primaryStage,"主界面","/com/work/MainView.fxml");
        }catch (Exception e){
            e.printStackTrace();
            AlertController.showAlert(primaryStage,"保存失败","数据类型存在错误！","请检查性别，年龄和接诊费用三项信息");
        }


    }

    public void back() throws IOException {
        StageServe.paint(primaryStage,"主界面","/com/work/MainView.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UID.setText(HisItem.getNewUID(DataSaverLoader.P_EXAMPLE,DataSaverLoader.P_PATH));
        //根据接诊医生信息中科室名，将挂号页面的科室数据初始化
        try {
            //设置单选按钮
            ToggleGroup gander = new ToggleGroup();
            male.setToggleGroup(gander);
            female.setToggleGroup(gander);

            treatDoctors = DataSaverLoader.load(DataSaverLoader.TD_EXAMPLE,DataSaverLoader.TD_PATH);
            Set<String> deptNames = new HashSet<String>();
            for(TreatDoctor td:treatDoctors){
                deptNames.add(td.getDeptName());
            }
            //更新到科室下拉列表中
            for (String s:deptNames){
                deptName.getItems().add(s);
            }
            //定义一个科室信息传递变量
            final Object[] dn = new Object[1];
            //监听deptName下拉列表的改变
            deptName.valueProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    //记录科室信息
                    dn[0] = newValue;
                    //清空原有的数据
                    treatLevel.setItems(FXCollections.observableArrayList());
                    doctorName.setItems(FXCollections.observableArrayList());
                    //第二个参数是指旧数据，第三个参数是指新数据
                    //根据科室内容来更新号别
                    Set<String> treatLevels = new HashSet<>();
                    for(TreatDoctor td:treatDoctors){
                        //选择号别时应根据科室信息选择
                        if(td.getDeptName().equals(newValue)){
                            treatLevels.add(td.getTreatLevel());
                        }
                    }
                    //更新到号别下拉列表中
                    for(String s:treatLevels){
                        treatLevel.getItems().add(s);
                    }
                }
            });
            //监听treatLevel下拉列表的改变
            treatLevel.valueProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    //清空原有的数据
                    doctorName.setItems(FXCollections.observableArrayList());
                    Set<String> set = new HashSet<String>();
                    for(TreatDoctor td:treatDoctors){
                        if(td.getDeptName().equals(dn[0])&&td.getTreatLevel().equals(newValue)){
                            set.add(td.getName());
                        }
                    }
                    //更新到医生姓名下拉列表中
                    for (String s:set){
                        doctorName.getItems().add(s);
                    }
                }
            });
            //监听treatLevel下拉列表的改变
            doctorName.valueProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    for (TreatDoctor td:treatDoctors){
                        if(td.getName().equals(newValue)){
                            treatFee.setText(td.getTreatFee()+"元");
                        }
                    }
                }
            });
            //监听isBook按钮
            isBook.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    String amount=treatFee.getText();
                    double amountNum = Double.parseDouble(amount.substring(0,amount.length()-1));
                    if(newValue){
                        //每勾选一次，加1元
                        treatFee.setText(amountNum+1+"元");
                    }else{
                        //每取消勾选一次，减1元
                        treatFee.setText(amountNum-1+"元");
                    }

                }
            });
            //监听receipt文本框
            receipt.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    //实收金额
                    double reciepts = Double.parseDouble(newValue);
                    //应收金额
                    String treatFee01 = treatFee.getText();
                    double treatFeeNum = Double.parseDouble(treatFee01.substring(0,treatFee01.length()-1));
                    refund.setText(reciepts-treatFeeNum+"元");

                }
            });
            //添加收费员
            if(LoginController.loginUID!="admin"){
                checker.setText(DataSaverLoader.find(DataSaverLoader.TD_EXAMPLE,DataSaverLoader.TD_PATH,LoginController.loginUID).getName());
            }else{
                checker.setText("admin");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
