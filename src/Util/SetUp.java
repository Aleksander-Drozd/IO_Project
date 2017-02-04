package Util;

import Model.LoginModel;
import Model.TripModel;
import Util.TripCostUtil;
import fit.Fixture;

public class SetUp extends Fixture {
    static TripCostUtil tripCostUtil;
    static DataVerificationUtil dataVerificationUtil;
    static LoginModel loginModel;
    static TripModel tripModel;

    public SetUp(){
        tripCostUtil = new TripCostUtil();
        dataVerificationUtil = new DataVerificationUtil();
        loginModel = new LoginModel();
        tripModel = new TripModel();
    }
}
