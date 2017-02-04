package Util;

import fit.ColumnFixture;

public class TestLoginModel extends ColumnFixture{
    String login;
    String password;

    public boolean getEmployee(){
        if (SetUp.loginModel.getEmployee(login, password) != null)
            return true;
        else
            return false;
    }
}
