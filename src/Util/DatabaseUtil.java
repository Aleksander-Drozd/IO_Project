package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {

    private static String dbURL = "jdbc:mysql://localhost:8080/tour_operator";
    private static String user = "root";
    private static String password = "";

    public static Connection getConnection(){
        Connection connection;

        try {
            connection = DriverManager.getConnection(dbURL, user, password);
            return connection;
        } catch (SQLException e) {
        }

        return null;
    }
}
