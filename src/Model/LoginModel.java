package Model;

import DAO.EmployeeDAO;
import POJO.Employee;

public class LoginModel {

    private EmployeeDAO employeeDAO;

    public LoginModel() {
        employeeDAO = new EmployeeDAO();
    }

    public Employee getEmployee(String login, String password) {
        return employeeDAO.logEmployee(login, password);
    }

}
