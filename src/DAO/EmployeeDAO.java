package DAO;

import POJO.Employee;
import Util.DatabaseUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAO {

    private static Employee loggedEmployee = null;

    public Employee logEmployee(String login, String password) {
        ResultSet resultSet;
        Employee employee = null;

        //TODO Mby method for this?
        String SQL = "SELECT id, first_name, last_name, position FROM employee WHERE login='" + login + "' AND password='" + password + "';";

        resultSet = DatabaseUtil.runStatement(SQL);
        try {
            if (resultSet.next()) {
                employee = createEmployee(resultSet);

                loggedEmployee = employee;
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }

        return employee;
    }

    public Employee getLoggedEmployee(){
        return loggedEmployee;
    }

    private Employee createEmployee(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();

        employee.setId(resultSet.getInt("id"));
        employee.setFirstName(resultSet.getString("first_name"));
        employee.setLastName(resultSet.getString("last_name"));
        employee.setPosition(resultSet.getString("position"));

        return employee;
    }
}
