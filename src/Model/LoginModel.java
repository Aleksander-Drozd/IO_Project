package Model;

import DAO.EmployeeDAO;
import POJO.Employee;

public class LoginModel {

    private Employee employee;
    private EmployeeDAO employeeDAO;

    public LoginModel() {
        employeeDAO = new EmployeeDAO();
    }

    public Employee getEmployee(String login, String password) {
        return employeeDAO.getEmployee(login, password);
    }

}
