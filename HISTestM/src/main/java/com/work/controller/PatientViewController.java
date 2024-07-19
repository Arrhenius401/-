package com.work.controller;

import com.work.entity.Patient;
import com.work.io.DataSaverLoader;
import com.work.serve.StageServe;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

//患者信息下的患者总览
public class PatientViewController {
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
    private TextField mUID;

    @FXML
    private Label drugPrice;

    @FXML
    private Label visitState;

    @FXML
    private Label adress;

    public static Patient patient = null;

    public static Stage primaryStage;

    public void searchMUID() throws Exception {
        //使用pUID查询患者信息
        String mUID = this.mUID.getText();
        patient = DataSaverLoader.find(DataSaverLoader.P_EXAMPLE, DataSaverLoader.P_PATH, mUID);
        if (patient != null) {
            this.pName.setText(patient.getName());
            this.pAge.setText(patient.getAge() + "岁");
            this.pGender.setText(patient.getSex());
            this.pSUID.setText(patient.getsUID());
            this.deptName.setText(patient.getDeptName());
            this.doctorName.setText(patient.getDoctorName());
            this.treatDate.setText(patient.getTreatDate());
            this.treatLevel.setText(patient.getTreatLevel());
            this.adress.setText(patient.getAdress());
            this.drugPrice.setText(patient.getDrugPrice()+"元");
            this.diagnosis.setText(patient.getDiagiosis());
            this.prescription.setText(patient.getPrescription());
            switch (patient.getVisitState()){
                case 1:this.visitState.setText("已挂号");break;
                case 2:this.visitState.setText("看诊结束");break;
                case 3:this.visitState.setText("已取药");break;
                case 4:this.visitState.setText("已退号");break;
                default:this.visitState.setText("数据错误");
            }
        } else {
            AlertController.showAlert(primaryStage, "查询失败", "查无此人！", "请检查输入的mUID，如”ME001“");
        }
        this.mUID.setText("");
    }

    public void back() throws IOException {
        StageServe.paint(primaryStage,"主界面","/com/work/MainView.fxml");
    }

}
