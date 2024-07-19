package com.work.controller;

import com.work.entity.Doctor;
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
public class AccountViewController implements Initializable {

    @FXML
    private RadioButton treatDoctor;

    @FXML
    private RadioButton phramacyDoctor;

    @FXML
    private RadioButton selectUID;

    @FXML
    private RadioButton selectAccount;

    @FXML
    private ComboBox deptName;

    @FXML
    private ComboBox treatLevel;

    @FXML
    private ComboBox doctorName;

    @FXML
    private Button searchAcountOrUID;

    @FXML
    private Button save;

    @FXML
    private Button change;

    @FXML
    private Button back;

    @FXML
    private TextField name;

    @FXML
    private TextField UID;

    @FXML
    private TextField catalog;

    @FXML
    private TextField sex;

    @FXML
    private TextField age;

    @FXML
    private TextField isAvailable;

    @FXML
    private TextField doctorDept;

    @FXML
    private TextField doctorLevel;

    @FXML
    private TextField account;

    @FXML
    private TextField password;

    @FXML
    private TextField depend;

    @FXML
    private TextField window;

    @FXML
    private TextField doctorFee;

    @FXML
    private RadioButton changeAccount;

    public static Stage primaryStage;
    private static ArrayList<TreatDoctor> treatDoctors;
    private static ArrayList<PharmacyDoctor> pharmacyDoctors;

    public void searchAcountOrUID() throws Exception {
        deptName.setItems(FXCollections.observableArrayList());
        treatLevel.setItems(FXCollections.observableArrayList());
        doctorName.setItems(FXCollections.observableArrayList());
        boolean isUID;
        if(this.selectUID.isSelected()){
            isUID = true;
            String doctorUID = this.depend.getText();
            if(doctorUID.substring(0,2).equals("TD")){
                //"=="用于判读首地址,作用于数值的比较;"equals"则是比较变量中具体的数值,如字符串等
                TreatDoctor pointTD = DataSaverLoader.findDoctor(DataSaverLoader.TD_EXAMPLE,DataSaverLoader.TD_PATH,doctorUID,isUID);
                fillDataTD(pointTD);
            }else if(doctorUID.substring(0,2).equals("PD")){
                PharmacyDoctor pointPD = DataSaverLoader.findDoctor(DataSaverLoader.PD_EXAMPLE,DataSaverLoader.PD_PATH,doctorUID,isUID);
                fillDataPD(pointPD);
            }else{
                AlertController.showAlert(primaryStage,"查询失败","查无此人！","请检查输入的UID，如”PD001“");
            }
        }else {
            isUID = false;
            String doctorAccount = this.depend.getText();
            TreatDoctor pointTD = DataSaverLoader.findDoctor(DataSaverLoader.TD_EXAMPLE,DataSaverLoader.TD_PATH,doctorAccount,false);
            if(pointTD!=null){
                fillDataTD(pointTD);
            }else {
                PharmacyDoctor pointPD = DataSaverLoader.findDoctor(DataSaverLoader.PD_EXAMPLE,DataSaverLoader.PD_PATH,doctorAccount,false);
                if(pointPD!=null){
                    fillDataPD(pointPD);
                }else{
                    AlertController.showAlert(primaryStage,"查询失败","查无此人！","请检查输入的账号信息");
                }
            }
        }

    }

    public boolean saveData() throws Exception {

        this.account.setDisable(true);

        String doctorUID = UID.getText();
        if(doctorUID.substring(0,2).equals("TD")){
            //"=="用于判读首地址,作用于数值的比较;"equals"则是比较变量中具体的数值,如字符串等
            TreatDoctor pointTD = DataSaverLoader.find(DataSaverLoader.TD_EXAMPLE,DataSaverLoader.TD_PATH,doctorUID);
            pointTD.setName(name.getText());
            pointTD.setDeptName(doctorDept.getText());
            pointTD.setTreatLevel(doctorLevel.getText());
            pointTD.setPassword(password.getText());

            //检查需控制输入类型的数据
            try {
                pointTD.setTreatFee(Double.parseDouble(doctorFee.getText().substring(0,doctorFee.getText().length()-1)));
                pointTD.setAge(Integer.parseInt(age.getText().substring(0,age.getText().length()-1)));
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

            if(changeAccount.isSelected()){
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
            }

            //保存对象
            DataSaverLoader.change(pointTD,DataSaverLoader.TD_PATH);
            AlertController.showAlert(primaryStage,"保存成功","数据保存成功！","已保存至本地文件中");
            setTextEditable(true);
            return true;

        }else if(doctorUID.substring(0,2).equals("PD")){
            PharmacyDoctor pointPD = DataSaverLoader.find(DataSaverLoader.PD_EXAMPLE,DataSaverLoader.PD_PATH,doctorUID);
            pointPD.setName(name.getText());
            pointPD.setPassword(password.getText());

            //检查需控制输入类型的数据
            try {
                pointPD.setAge(Integer.parseInt(age.getText().substring(0,age.getText().length()-1)));
                pointPD.setWindow(Integer.parseInt(window.getText().substring(0,window.getText().length()-1)));
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

            if(changeAccount.isSelected()){
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
            }

            //保存对象
            DataSaverLoader.change(pointPD,DataSaverLoader.PD_PATH);
            AlertController.showAlert(primaryStage,"保存成功","数据保存成功！","已保存至本地文件中");
            setTextEditable(true);
            return true;
        }

        AlertController.showAlert(primaryStage,"保存失败","数据错误！","请检查写入的数据");
        setTextEditable(false);
        return false;
    }

    public void changeData(){
        setTextEditable(false);
    }

    public void back() throws IOException {
        StageServe.paint(primaryStage,"主界面","/com/work/MainView.fxml");
    }

    //将医生信息填入文本中
    public void fillDataTD(TreatDoctor pointTD){
        UID.setText(pointTD.getUID());
        name.setText(pointTD.getName());
        catalog.setText("看诊医生");
        sex.setText(pointTD.getSex());
        age.setText(pointTD.getAge()+"岁");
        doctorDept.setText(pointTD.getDeptName());
        doctorLevel.setText(pointTD.getTreatLevel());
        account.setText(pointTD.getAccount());
        password.setText(pointTD.getPassword());
        doctorFee.setText(pointTD.getTreatFee()+"元");
        window.setText("无");
        if(pointTD.isAvailable()){
            isAvailable.setText("工作中");
        }else{
            isAvailable.setText("离开");
        }
    }

    //同上
    public void fillDataPD(PharmacyDoctor pointPD){
        UID.setText(pointPD.getUID());
        name.setText(pointPD.getName());
        catalog.setText("药房医生");
        sex.setText(pointPD.getSex());
        age.setText(pointPD.getAge()+"岁");
        window.setText(pointPD.getWindow()+"号");
        doctorDept.setText("无");
        doctorLevel.setText("无");
        account.setText(pointPD.getAccount());
        password.setText(pointPD.getPassword());
        doctorFee.setText("无");
        if(pointPD.isAvailable()){
            isAvailable.setText("工作中");
        }else{
            isAvailable.setText("离开");
        }
    }

    //用于编辑模式的切换
    public void setTextEditable(boolean a){
        //使textField不可更改
        name.setDisable(a);
        catalog.setDisable(a);
        sex.setDisable(a);
        age.setDisable(a);
        doctorDept.setDisable(a);
        doctorLevel.setDisable(a);
        window.setDisable(a);
        doctorFee.setDisable(a);
        password.setDisable(a);

        //将保存按钮隐藏
        save.setVisible(!a);
        save.setManaged(!a);

        //将修改账号和密码的按钮隐藏
        changeAccount.setVisible(!a);
        changeAccount.setManaged(!a);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //使UID和工作状态不可更改
        //将保存按钮隐藏
        isAvailable.setDisable(true);
        UID.setDisable(true);
        account.setDisable(true);
        setTextEditable(true);

        //使医生列别的两RadioButton绑定
        ToggleGroup doctorCatalog = new ToggleGroup();
        this.phramacyDoctor.setToggleGroup(doctorCatalog);
        this.treatDoctor.setToggleGroup(doctorCatalog);

        //使查询依据的两RadioButton绑定
        ToggleGroup dependWhat = new ToggleGroup();
        this.selectAccount.setToggleGroup(dependWhat);
        this.selectUID.setToggleGroup(dependWhat);

        //使用医生的类别，科室，等级，姓名进行查询
        //为单选按钮treatDoctor添加监听器
        this.treatDoctor.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                treatLevel.setItems(FXCollections.observableArrayList());
                doctorName.setItems(FXCollections.observableArrayList());
                try {
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
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //为单选按钮phramacyDoctor添加监听器
        this.phramacyDoctor.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                deptName.setItems(FXCollections.observableArrayList());
                treatLevel.setItems(FXCollections.observableArrayList());
                doctorName.setItems(FXCollections.observableArrayList());
                try {
                    pharmacyDoctors = DataSaverLoader.load(DataSaverLoader.PD_EXAMPLE,DataSaverLoader.PD_PATH);
                    //将药房医生名字添加到下拉列表中
                    Set<String> doctorNames = new HashSet<String>();
                    for(PharmacyDoctor pd:pharmacyDoctors){
                        doctorNames.add(pd.getName());
                    }
                    //更新到科室下拉列表中
                    for (String s:doctorNames){
                        doctorName.getItems().add(s);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //为医生姓名下拉列表选值添加监听器
        this.doctorName.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                try{
                    if(treatDoctor.isSelected()){
                        treatDoctors = DataSaverLoader.load(DataSaverLoader.TD_EXAMPLE,DataSaverLoader.TD_PATH);
                        for(TreatDoctor pointTD:treatDoctors){
                            if(pointTD.getName().equals(newValue)){
                                fillDataTD(pointTD);
                                break;
                            }
                        }
                    }else {
                        pharmacyDoctors = DataSaverLoader.load(DataSaverLoader.PD_EXAMPLE,DataSaverLoader.PD_PATH);
                        for(PharmacyDoctor pointPD:pharmacyDoctors){
                            if(pointPD.getName().equals(newValue)){
                                fillDataPD(pointPD);
                                break;
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        //为changeAccount按钮添加监听器
        this.changeAccount.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue){
                    account.setDisable(false);
                }else {
                    account.setDisable(true);
                }
            }
        });

    }
}
