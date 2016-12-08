package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
            } catch (SQLException e) {
                return null;
            } catch (ClassNotFoundException e) {
                return null;
            }
        }
    }
}
