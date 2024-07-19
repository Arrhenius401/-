package com.work.controller;

import com.work.entity.Patient;
import com.work.entity.TreatDoctor;
import com.work.io.DataSaverLoader;
import com.work.serve.StageServe;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class DiagnoseController implements Initializable {
    @FXML
    private Label pName;

    @FXML
    private Label pAge;

    @FXML
    private Label pGender;

    @FXML
    private Label pSUID;

    @FXML
    private Label deptName;

    @FXML
    private Label doctorName;

    @FXML
    private Label treatLevel;

    @FXML
    private Label treatDate;

    @FXML
    private TextArea diagnosis;

    @FXML
    private TextArea prescription;

    @FXML
    private ComboBox pUID;


    private static TreatDoctor pointTD;

    public static Patient patient = null;

    public static String loginUID;

    public static Stage primaryStage;

    //实质的改变目标患者的信息
    public void saveData() throws Exception {

        if(patient!=null){
            if(this.diagnosis.getText()!=null&&this.prescription.getText()!=null){
                patient.setDiagiosis(this.diagnosis.getText());
                patient.setPrescription(this.prescription.getText());
                patient.setVisitState(2);
                DataSaverLoader.change(patient,DataSaverLoader.P_PATH);
            }
        }else {
            AlertController.showAlert(primaryStage,"保存失败","患者信息未录入！","请先获取相关信息");
        }
        AlertController.showAlert(primaryStage,"保存成功","患者信息已录入！","诊断结束");
        StageServe.paint(primaryStage,"主界面","/com/work/MainView.fxml");
    }

    public void searchPUID(String pUID) throws Exception {
        //使用mUID查询患者信息
        patient = DataSaverLoader.find(DataSaverLoader.P_EXAMPLE,DataSaverLoader.P_PATH,pUID);
        if(patient!=null){
            this.pName.setText(patient.getName());
            this.pAge.setText(patient.getAge()+"岁");
            this.pGender.setText(patient.getSex());
            this.pSUID.setText(patient.getsUID());
            this.deptName.setText(patient.getDeptName());
            this.doctorName.setText(patient.getDoctorName());
            this.treatDate.setText(patient.getTreatDate());
            this.treatLevel.setText(patient.getTreatLevel());
        }else {
            AlertController.showAlert(primaryStage,"查询失败","查无此人！","请检查输入的pUID，如”PA001“");
        }
    }

    public void back() throws IOException {
        StageServe.paint(primaryStage,"主界面","/com/work/MainView.fxml");
    }

    public void printPUID() throws Exception {
        //根据患者的就诊科室，等级，医生，状态四种信息，给登录的医生显示需要诊断的患者信息
        Set<String> pNames = new HashSet<String>();

        if(loginUID.substring(0,2).equals("TD")){
            pointTD = DataSaverLoader.find(DataSaverLoader.TD_EXAMPLE,DataSaverLoader.TD_PATH,loginUID);
            ArrayList<Patient> patients = DataSaverLoader.load(DataSaverLoader.P_EXAMPLE,DataSaverLoader.P_PATH);

            for(Patient p:patients){
                if(p.getDeptName().equals(pointTD.getDeptName())&&p.getTreatLevel().equals(pointTD.getTreatLevel())&&
                        p.getDoctorName().equals(pointTD.getName())&&p.getVisitState()==1){

                    pNames.add(p.getUID());

                }
            }

            if(pNames.size()!=0){
                for (String s:pNames){
                    pUID.getItems().add(s);
                }
            }
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {

            printPUID();

        } catch (Exception e) {
            e.printStackTrace();
            AlertController.showAlert(primaryStage,"读取失败","患者信息无法读入！","请联系管理员检查相关信息！");
        }

        pUID.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    searchPUID(newValue);
                } catch (Exception e) {
                    e.printStackTrace();
                    AlertController.showAlert(primaryStage,"读取失败","患者信息无法读入！","请联系管理员检查相关信息！");
                }
            }
        });

    }
}
