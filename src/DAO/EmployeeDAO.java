package DAO;

import POJO.Employee;
import Util.DatabaseUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeDAO {

    private Connection connection;

    public EmployeeDAO() {
        connection = DatabaseUtil.getConnection();
    }

    public Employee getEmployee(String login, String password) {
        Employee employee = null;
        ResultSet resultSet;

        //TODO Mby fun for this?
        String SQL = "SELECT id, first_name, last_name, position FROM employee WHERE login='" + login + "' AND password='" + password + "';";

        resultSet = DatabaseUtil.runStatement(SQL);
        try {
            if (resultSet.next()) {
                employee = createEmployee(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employee;
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
