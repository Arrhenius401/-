package com.work.controller;

import com.work.entity.Medicine;
import com.work.entity.Patient;
import com.work.io.DataSaverLoader;
import com.work.serve.StageServe;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MedicineController {
    @FXML
    private TextField pUID;

    @FXML
    private Label pName;

    @FXML
    private Label pAge;

    @FXML
    private Label pGender;

    @FXML
    private Label pSUID;

    @FXML
    private TableView<Medicine> tableView;

    @FXML
    private TableColumn<?, ?> mUID;

    @FXML
    private TableColumn<?, ?> mName;

    @FXML
    private TableColumn<?, ?> mPrice;

    @FXML
    private TableColumn<?, ?> mNumber;

    @FXML
    private TableColumn<?, ?> mDescription;

    @FXML
    private Label totalPrice;

    public static Patient patient = null;

    public static Stage primaryStage;


    public void inquire() throws Exception {
        //查询病患信息
        String pUID = this.pUID.getText();
        patient = DataSaverLoader.find(DataSaverLoader.P_EXAMPLE, DataSaverLoader.P_PATH, pUID);
        if (patient != null) {
            this.pName.setText(patient.getName());
            this.pAge.setText(patient.getAge() + "岁");
            this.pGender.setText(patient.getSex());
            this.pSUID.setText(patient.getsUID());

        } else {
            AlertController.showAlert(primaryStage, "查询失败", "查无此人！", "请检查输入的pUID，如”PA001“");
        }
        this.pUID.setText("");

        //药品加载
        String sentence = patient.getPrescription(); // 开药清单
        String[] parts=new String[8];
        if(!sentence.equals("")) {
           parts = sentence.split(":"); // 使用冒号作为分隔符
        }else {
            AlertController.showAlert(primaryStage, "查询失败", "查无此人！", "请检查输入的pUID，如”PA001“");
        }
        ArrayList<String> medicines1 = new ArrayList<>();
        for (String part : parts) {
            medicines1.add(part);
        }

        List<Medicine> medicines2 = new ArrayList<>();
        for (String element : medicines1) {
            String mUID1 = element.substring(0, 5);
            Medicine medicine=DataSaverLoader.find(DataSaverLoader.M_EXAMPLE, DataSaverLoader.M_PATH, mUID1);
            medicines2.add(medicine);
        }
        tableView.setItems(FXCollections.observableList(medicines2));

        mUID.setCellValueFactory(new PropertyValueFactory<>("UID"));
        mName.setCellValueFactory(new PropertyValueFactory<>("name"));
        mPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        mNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        mDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

        //药品总价
        double value=0;
        for (Medicine m : medicines2) {
            value += m.getPrice();
        }
        totalPrice.setText(value+"元");
    }

    //发药
    public void dispense(){
        patient.setVisitState(3);
        AlertController.showAlert(primaryStage,"发药成功","请按时服药！","祝您健康长寿！");
    }

    public void back() throws IOException {
        StageServe.paint(primaryStage,"主界面","/com/work/MainView.fxml");
    }
}
