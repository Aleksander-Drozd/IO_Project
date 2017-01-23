package DAO;

import POJO.Employee;
import Util.DatabaseUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAO {
    private static Employee loggedEmployee = null;

    public static Employee getEmployee(String login, String password) {
        ResultSet resultSet;

        //TODO Mby method for this?
        String SQL = "SELECT id, first_name, last_name, position FROM employee WHERE login='" + login + "' AND password='" + password + "';";

        resultSet = DatabaseUtil.runStatement(SQL);
        try {
            if (resultSet.next()) {
                loggedEmployee = createEmployee(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return loggedEmployee;
    }

    public static Employee getEmployee(int id) {
        Employee employee = null;
        ResultSet resultSet;

        String query = "SELECT id, first_name, last_name, position FROM employee WHERE id='" + id + "';";

        resultSet = DatabaseUtil.runStatement(query);
        try {
            if (resultSet.next()) {
                employee = createEmployee(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employee;
    }

    public static Employee getLoggedEmployee(){
        return loggedEmployee;
    }

    private static Employee createEmployee(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();

        employee.setId(resultSet.getInt("id"));
        employee.setFirstName(resultSet.getString("first_name"));
        employee.setLastName(resultSet.getString("last_name"));
        employee.setPosition(resultSet.getString("position"));

        return employee;
    }
}
