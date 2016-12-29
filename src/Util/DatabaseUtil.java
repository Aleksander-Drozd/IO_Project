package Util;

import java.sql.*;

public class DatabaseUtil {

    private static String dbURL = "jdbc:mysql://localhost:3306/tour_operator";
    private static String user = "root";
    private static String password = "";

    private static Connection connection = null;

    public static Connection getConnection(){
        if ( connection != null ) {
           return connection;
        } else {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(dbURL, user, password);
                return connection;
            } catch (SQLException | ClassNotFoundException e) {
                return null;
            }
        }
    }

    public static ResultSet runStatement(String query) {
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
