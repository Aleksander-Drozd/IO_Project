package Util;

import java.sql.*;

public class DatabaseUtil {

    private static String dbURL = "jdbc:mysql://localhost:3306/tour_operator";
    private static String user = "root";
    private static String password = "";

    private static Connection connection = null;

    public static void setConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dbURL, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet runStatement(String query) {
        Statement statement = null;
        ResultSet resultSet;

        if (connection == null) {
            setConnection();
        }

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
