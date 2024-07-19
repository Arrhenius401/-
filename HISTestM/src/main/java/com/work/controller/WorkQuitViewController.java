package com.work.controller;
import com.work.entity.PharmacyDoctor;
import com.work.entity.PutTable;
import com.work.entity.TreatDoctor;
import com.work.io.DataSaverLoader;
import com.work.serve.StageServe;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class WorkQuitViewController implements Initializable {

    @FXML
    private Label UID;

    @FXML
    private Label name;

    @FXML
    private Label isAvailable;

    @FXML
    private TableColumn start;

    @FXML
    private TableColumn end;

    @FXML
    private Button back;

    @FXML
    private Button inOut;

    @FXML
    private TableView tableView;

    private TreatDoctor ponitTD;
    private PharmacyDoctor ponitPD;

    private ArrayList<String> startTime = new ArrayList<String>();
    private ArrayList<String> endTime = new ArrayList<String>();

    private ObservableList<PutTable> data = FXCollections.observableArrayList();

    public static String loginUID;
    public static Stage primaryStage;

    //返回主页面
    public void back() throws IOException {
        StageServe.paint(primaryStage,"主界面","/com/work/MainView.fxml");
    }

    public void workQuit(){
        String dataWord = new Date().toString();
    }

    //用于填充基本信息
    public void fillData(TreatDoctor d){

        this.name.setText(d.getName());
        this.UID.setText(d.getUID());
        if(d.isAvailable()){
            this.isAvailable.setText("工作中");
            this.inOut.setText("签退");
        }else{
            this.isAvailable.setText("下班");
            this.inOut.setText("签到");
        }
    }

    public void fillData(PharmacyDoctor d) throws Exception {

        this.name.setText(d.getName());
        this.UID.setText(d.getUID());
        if(d.isAvailable()){
            this.isAvailable.setText("工作中");
            this.inOut.setText("签退");
        }else{
            this.isAvailable.setText("下班");
            this.inOut.setText("签到");
        }
    }

    //将签到签退时间和日期填入图表
    public void fillTime(){

        tableView.getItems().clear();

        if(loginUID.substring(0,2).equals("PD")||loginUID.substring(0,2).equals("TD")){

            for (int i=0;i<startTime.size();i++){
                PutTable putTable = new PutTable();
                putTable.startTime.setValue(this.startTime.get(i));
                putTable.endTime.setValue(this.endTime.get(i));
                data.add(putTable);
            }
        }
    }

    //按签到签退按钮触发事件
    public void pressButton() throws Exception{

        if (inOut.getText().equals("签到")){

            inOut.setText("签退");

            String nowDate = new Date().toString();
            startTime.add(nowDate);
            endTime.add("");

        }else{

            inOut.setText("签到");

            String nowDate = new Date().toString();
            endTime.set(endTime.size()-1,nowDate);

        }

        //改变存储的对象
        if(loginUID.substring(0,2).equals("TD")){

            ponitTD = DataSaverLoader.findDoctor(DataSaverLoader.TD_EXAMPLE,DataSaverLoader.TD_PATH,loginUID,true);

            ponitTD.setAvailable(!ponitTD.isAvailable());
            ponitTD.setWorkTime(startTime);
            ponitTD.setQuitTime(endTime);

            DataSaverLoader.change(ponitTD,DataSaverLoader.TD_PATH);

            fillTime();

            fillData(ponitTD);

        }else if(loginUID.substring(0,2).equals("PD")){

            ponitPD= DataSaverLoader.findDoctor(DataSaverLoader.PD_EXAMPLE,DataSaverLoader.PD_PATH,loginUID,true);

            ponitPD.setAvailable(!ponitPD.isAvailable());
            ponitPD.setWorkTime(startTime);
            ponitPD.setQuitTime(endTime);

            DataSaverLoader.change(ponitPD,DataSaverLoader.PD_PATH);

            fillTime();

            fillData(ponitPD);

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.tableView.setItems(data);

        start.setCellValueFactory(new PropertyValueFactory<PutTable,String>("startTime"));
        end.setCellValueFactory(new PropertyValueFactory<PutTable,String>("endTime"));


        try {
            //这if-else块用于填充基本信息
            if(loginUID.substring(0,2).equals("TD")){

                ponitTD= DataSaverLoader.findDoctor(DataSaverLoader.TD_EXAMPLE,DataSaverLoader.TD_PATH,loginUID,true);
                fillData(ponitTD);

                startTime = ponitTD.getWorkTime();
                endTime = ponitTD.getQuitTime();

            }else if(loginUID.substring(0,2).equals("PD")){

                ponitPD= DataSaverLoader.findDoctor(DataSaverLoader.PD_EXAMPLE,DataSaverLoader.PD_PATH,loginUID,true);
                fillData(ponitPD);

                startTime = ponitPD.getWorkTime();
                endTime = ponitPD.getQuitTime();

            }else if(loginUID.equals("admin")){
                this.name.setText("admin");
                this.UID.setText("admin");
                this.isAvailable.setText("巡查");
            }
        }catch (Exception ex){ex.printStackTrace();}

        fillTime();


    }
}
