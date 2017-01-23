package Model;

import DAO.EmployeeDAO;
import POJO.Employee;

public class LoginModel {

    public LoginModel() {
    }

    public Employee getEmployee(String login, String password) {
        return EmployeeDAO.getEmployee(login, password);
    }

}
