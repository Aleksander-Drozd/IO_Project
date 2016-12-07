package DAO;

import POJO.Employee;
import Util.DatabaseUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeDAO {


    public EmployeeDAO() {
        Connection connection = DatabaseUtil.getConnection();
    }

    public Employee getEmployee(String login, String password) {
        Employee employee = null;
        ResultSet resultSut;

        //TODO make query
        String query = "";

        resultSut = runStatement(query);
        //TODO decoding resultSet

        return employee;
    }

    private ResultSet runStatement(String query) {
        Statement statement;
        ResultSet resultSet;

        try {
            //TODO fix connection
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return resultSet;
    }


}
