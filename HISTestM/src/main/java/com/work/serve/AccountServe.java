package com.work.serve;

import com.work.entity.PharmacyDoctor;
import com.work.entity.TreatDoctor;
import com.work.io.DataSaverLoader;

import java.util.ArrayList;

public class AccountServe {
    public boolean isSameAccount(String account){
        Boolean result = false;
        try {

            ArrayList<TreatDoctor> treatDoctors = DataSaverLoader.load(DataSaverLoader.TD_EXAMPLE,DataSaverLoader.TD_PATH);
            ArrayList<PharmacyDoctor> pharmacyDoctors = DataSaverLoader.load(DataSaverLoader.PD_EXAMPLE,DataSaverLoader.PD_PATH);

            for(TreatDoctor td:treatDoctors){
                if(account.equals(td.getAccount())){
                    result = true;
                    return result;
                }
            }

            for(PharmacyDoctor pd:pharmacyDoctors){
                if(account.equals(pd.getAccount())){
                    result = true;
                    return result;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }
}
