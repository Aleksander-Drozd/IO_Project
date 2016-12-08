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

        //TODO make query
        String SQL = "SELECT * FROM employee;";

        //empty resultset, dunno why
        resultSet = runStatement(SQL);
        try {
            while (resultSet.next()) {
                System.out.println(resultSet.getString("last_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employee;
    }

    private ResultSet runStatement(String query) {
        Statement statement;
        ResultSet resultSet;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return resultSet;
    }
}
