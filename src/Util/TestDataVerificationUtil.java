package Util;

import fit.ColumnFixture;

public class TestDataVerificationUtil extends ColumnFixture{
    String data[];

    public boolean verifyCustomer(){
        return SetUp.dataVerificationUtil.verifyCustomer(data);
    }
}
