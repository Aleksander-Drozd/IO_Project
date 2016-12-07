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
        //zeby dzialalo musisz podpiac driver w File|Project Structure|Modules|+ i wybierasz jara z folderu lib
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connection = DatabaseUtil.getConnection();
    }

    public Employee getEmployee(String login, String password) {
        Employee employee = null;
        ResultSet resultSut;

        //TODO make query
        String query = "select * from employee";
        int a = 0;

        //empty resultset, dunno why
        resultSut = runStatement(query);
        try {
            a = resultSut.getInt("position");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(a);
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
