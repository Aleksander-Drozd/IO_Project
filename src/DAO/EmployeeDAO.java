package DAO;

import POJO.Employee;
import Util.DatabaseUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    private static Employee loggedEmployee = null;

    public Employee logEmployee(String login, String password) {
        ResultSet resultSet;
        Employee employee = null;

        //TODO Mby method for this?
        String SQL = "SELECT id, first_name, last_name, position FROM employee WHERE login='" + login + "' AND password='" + password + "';";

        resultSet = DatabaseUtil.runSelectQuery(SQL);
        try {
            if (resultSet.next()) {
                employee = createEmployee(resultSet);

                loggedEmployee = employee;
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.closeResultSetAndConnectedStatement(resultSet);
        }

        return employee;
    }

    public static Employee getLoggedEmployee(){
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

    public List<Employee> getEmployees() {
        ResultSet resultSet;
        List<Employee> employees = new ArrayList<>();

        //TODO Mby method for this?
        String SQL = "SELECT id, first_name, last_name, position FROM employee;";

        resultSet = DatabaseUtil.runSelectQuery(SQL);
        try {
            while (resultSet.next()) {
                employees.add(createEmployee(resultSet));
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.closeResultSetAndConnectedStatement(resultSet);
        }

        return employees;
    }
}
