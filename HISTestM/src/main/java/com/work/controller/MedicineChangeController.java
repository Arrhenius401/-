package com.work.controller;

import com.work.entity.Medicine;
import com.work.entity.PharmacyDoctor;
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
import java.rmi.server.UID;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import static com.work.controller.MedicineManageController.yao;

public class MedicineChangeController  implements Initializable{

    @FXML
    private Button save;

    @FXML
    private TextField mNumber;

    @FXML
    private TextField mName;

    @FXML
    private TextField mPrice;

    @FXML
    private TextField mUID;

    @FXML
    private RadioButton isPrescription;

    @FXML
    private RadioButton noPrescription;

    @FXML
    private TextArea mDescription;

    public static Stage primaryStage;

    //使数据可以被修改
    public void changeData(){
        setTextEditable(true);
    }
    //保存修改后的信息
    public void saveData() throws Exception {
        Medicine m = yao;
        yao.setOperater(null);
        m.setName(mName.getText());
        m.setPrice(Double.parseDouble(mPrice.getText().substring(0, mPrice.getText().length() - 1)));
        m.setNumber(Integer.parseInt(mNumber.getText()));
        m.setDescription(mDescription.getText());
        if (isPrescription.isSelected()) {
            m.setPrescription(true);
        } else {
            m.setPrescription(false);
        }
        if (m.getName() != null||m.getNumber()>=0) {
            DataSaverLoader. change(m, DataSaverLoader.M_PATH);
            AlertController.showAlert(primaryStage, "保存成功", "数据保存成功！", "已保存至本地文件中");
        } else {
            AlertController.showAlert(primaryStage, "保存失败", "数据错误！", "请检查写入的数据");
        }
    }

    //添加药品信息
    public void fillDataMD(Medicine m){
        mUID.setText(m.getUID());
        mName.setText(m.getName());
        mPrice.setText(m.getPrice()+"元");
        mNumber.setText(String.valueOf(m.getNumber()));
        mDescription.setText(m.getDescription());
        if(m.isPrescription()){
            isPrescription.setSelected(true);
        }
    }

    //用于编辑模式的切换
    public void setTextEditable(boolean a){
        //使textField不可更改
        mName.setEditable(a);
        mNumber.setEditable(a);
        mPrice.setEditable(a);
        mDescription.setEditable(a);
        isPrescription.setDisable(!a);
        noPrescription.setDisable(!a);

        //将保存按钮隐藏
        save.setVisible(a);
        save.setManaged(a);

    }

    public void back() throws IOException {
        StageServe.paint(primaryStage,"药品总览","/com/work/MedicineManageView.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //使mUID不可更改
        mUID.setEditable(false);
        //将保存按钮隐藏
        setTextEditable(false);
        //使判断处方药的两RadioButton绑定
        ToggleGroup doctorCatalog = new ToggleGroup();
        this.isPrescription.setToggleGroup(doctorCatalog);
        this.noPrescription.setToggleGroup(doctorCatalog);
        //初始化药品信息
        this.fillDataMD(yao);


    }
}
