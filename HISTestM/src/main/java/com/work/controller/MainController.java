package com.work.controller;
import com.work.controller.DiagnoseController;
import com.work.serve.StageServe;
        import javafx.fxml.FXML;
        import javafx.fxml.Initializable;
        import javafx.scene.control.Button;
        import javafx.scene.control.Menu;
        import javafx.stage.Stage;
        import java.io.IOException;
        import java.net.URL;
        import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Button regist;              //现场挂号

    @FXML
    private Button diagnose;            //医生看诊

    @FXML
    private Button patientThing;        //患者总览

    @FXML
    private Button medicineThing;       //药物总览

    @FXML
    private Button deliverMedicine;     //药房开药

    @FXML
    private Button doctorThing;         //医生总览

    @FXML
    private Button workOut;             //签到签退

    @FXML
    private Button accountThing;        //账号总览

    @FXML
    private Button signUp;              //账号注册

    //与swing相比，fx的不同在于每个画板含有一个控制器;它们的共同点则是一个图形部件仍对应一个监听器
    public static Stage primaryStage;

    public static String loginUID;

    public void toRegist() throws IOException {
        StageServe.paint(primaryStage,"挂号界面","/com/work/RegistView.fxml");
    }

    public void toDiagnose() throws IOException{
        StageServe.paint(primaryStage,"诊断界面","/com/work/DiagnoseView.fxml");
    }

    public void toPatient()throws IOException{
        StageServe.paint(primaryStage,"患者总览","/com/work/PatientView.fxml");
    }

    public void toAccount() throws IOException {
        StageServe.paint(primaryStage,"账号总览","/com/work/AccountView.fxml");
    }

    public void toDoctor() throws IOException {
        StageServe.paint(primaryStage,"医生总览","/com/work/DoctorView.fxml");
    }

    public void toSignUp() throws IOException {
        StageServe.paint(primaryStage,"注册账号","/com/work/SignUpView.fxml");
    }

    public void toWorkQuit() throws IOException {
        StageServe.paint(primaryStage,"出勤签退","/com/work/WorkQuitView.fxml");

    }

    public void toMedicine() throws IOException {
        StageServe.paint(primaryStage,"药房发药","/com/work/MedicineView.fxml");
    }

    public void toMedicineManage() throws IOException {
        StageServe.paint(primaryStage,"药物总览","/com/work/MedicineManageView.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        WorkQuitViewController.loginUID=MainController.loginUID;
        DiagnoseController.loginUID=MainController.loginUID;

        if(MainController.loginUID!=null){
            if(MainController.loginUID.substring(0,2).equals("TD")){

                medicineThing.setDisable(true);
                deliverMedicine.setDisable(true);
                accountThing.setDisable(true);
                signUp.setDisable(true);

            }else if(MainController.loginUID.substring(0,2).equals("PD")){

                regist.setDisable(true);
                diagnose.setDisable(true);
                accountThing.setDisable(true);
                signUp.setDisable(true);

            }
        }
    }
}
