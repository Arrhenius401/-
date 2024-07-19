package com.work.serve;

import com.work.controller.LoginController;
import com.work.controller.MainController;
import com.work.controller.WorkQuitViewController;
import com.work.entity.PharmacyDoctor;
import com.work.entity.TreatDoctor;
import com.work.io.DataSaverLoader;

import java.util.ArrayList;

public class LoginServe {

    public int login(String account,String pwd){
        //0表示登录成功,1表示账号存在但密码错误，2表示账号不存在
        int result = 2;
        //开个后门
        if(account.equals("admin")&&pwd.equals("123")){

            LoginController.loginUID="admin";
            MainController.loginUID="admin";

            result = 0;
            return result;
        }
        try {
            ArrayList<TreatDoctor> treatDoctors = DataSaverLoader.load(DataSaverLoader.TD_EXAMPLE,DataSaverLoader.TD_PATH);
            ArrayList<PharmacyDoctor> pharmacyDoctors = DataSaverLoader.load(DataSaverLoader.PD_EXAMPLE,DataSaverLoader.PD_PATH);
            for (TreatDoctor td:treatDoctors){
                if(td.getAccount().equals(account)){
                    if(td.getPassword().equals(pwd)){

                        LoginController.loginUID = td.getUID();
                        MainController.loginUID=td.getUID();

                        LoginController.loginDoctor = td;
                        result = 0;
                        return result;
                    }else {
                        result = 1;
                        return result;
                    }
                }else {
                    for(PharmacyDoctor pd:pharmacyDoctors){
                        if(pd.getAccount().equals(account)){
                            if(pd.getPassword().equals(pwd)){

                                LoginController.loginUID = pd.getUID();
                                MainController.loginUID=pd.getUID();

                                LoginController.loginDoctor = pd;
                                result = 0;
                                return result;
                            }else {
                                result = 1;
                                return result;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
