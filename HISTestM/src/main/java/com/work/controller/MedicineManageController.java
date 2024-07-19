package com.work.controller;

import com.work.entity.Doctor;
import com.work.entity.HisItem;
import com.work.entity.Medicine;
import com.work.io.DataSaverLoader;
import com.work.serve.StageServe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MedicineManageController implements Initializable {
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
    private TableColumn<?, ?> operate;

    @FXML
    private Button back;

    @FXML
    private TextField medicine1;

    @FXML
    private TextArea medicine2;

    //ME005:ZG:11:11:11:是

    public static Stage primaryStage;
    private static List<Medicine> ms = new ArrayList<>();
    public static Medicine yao=new Medicine();
    private Medicine medicine = new Medicine();

    public Medicine getmedicine() throws Exception {
        Medicine pointM = new Medicine();
        if(!medicine1.getText().equals("")) {
            String sentence = this.medicine1.getText();
            String[] parts =new String[6];
            parts =sentence.split(":"); // 使用冒号作为分隔符

            pointM.setUID(parts[0]);
            pointM.setName(parts[1]);
            pointM.setPrice(Double.parseDouble(parts[2]));
            pointM.setNumber(Integer.parseInt(parts[3]));
            pointM.setDescription(parts[4]);
            boolean var5 = false;
            if (parts[5].equals("是")) {
                var5 = true;
            }
            pointM.setPrescription(var5);
            return pointM;
        }
        return  null;
    }



    public void addm() throws Exception {
        Medicine pointM = getmedicine();
        if(pointM!=null) {
            try{DataSaverLoader.save(pointM,DataSaverLoader.M_PATH);
                pointM.setOperater(new Button("编辑"));
                tableView.getItems().add(pointM);
                tableView.refresh();
                AlertController.showAlert(primaryStage, "添加成功", "药品信息已被添加！", "请注意核对信息");
            } catch (Exception e){
                e.printStackTrace();






































































































































































































































































































                AlertController.showAlert(primaryStage, "添加失败", "当前药品编号已存在！", "请注意核对信息");

            }

        }
    }

    public void deletem() throws Exception {

      String UID="";
      UID=medicine1.getText().substring(0,5);
        if(DataSaverLoader.delete(DataSaverLoader.M_EXAMPLE,DataSaverLoader.M_PATH, UID)){
            AlertController.showAlert(primaryStage, "删除成功", "药品信息已被删除！", "请重新登陆查看");

        }else{
            AlertController.showAlert(primaryStage, "删除失败", "查无此药！", "请检查输入的mUID，如”ME001“");
        }
        tableView.refresh();
    }

    public void findm() throws Exception {
        String mUID1 = medicine1.getText().substring(0, 5);
        Medicine mm = DataSaverLoader.find(DataSaverLoader.M_EXAMPLE, DataSaverLoader.M_PATH,mUID1);
        if(mm!=null){
            medicine2.setText(mm.toString());
        }else{
            AlertController.showAlert(primaryStage, "查询失败", "查无此药！", "请检查输入的mUID，如”ME001“");

        }
    }



    public void back() throws IOException {
        StageServe.paint(primaryStage,"主界面","/com/work/MainView.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {



        try {
            ms=DataSaverLoader.load(DataSaverLoader.M_EXAMPLE,DataSaverLoader.M_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(Medicine m:ms){
            m.setOperater(new Button("编辑"));
            Button edit = m.getOperater();
            edit.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    try {
                        yao = m;
                        StageServe.paint(primaryStage,"药品信息","/com/work/MedicineChangeView.fxml");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            });

            tableView.setItems(FXCollections.observableList(ms));

        mUID.setCellValueFactory(new PropertyValueFactory<>("UID"));
        mName.setCellValueFactory(new PropertyValueFactory<>("name"));
        mPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        mNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        mDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        operate.setCellValueFactory(new PropertyValueFactory<>("operater"));

    }
}
}





