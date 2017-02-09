package Util;

import java.sql.*;

public class DatabaseUtil {

    private static final String dbURL = "jdbc:mysql://localhost:3306/tour_operator?characterEncoding=utf8";
    private static final String user = "root";
    private static final String password = "";

    private static Connection connection = null;

    public static final int ERROR = -1;

    public static void setConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dbURL, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet runSelectQuery(String query) {
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

    public static void startTransaction() throws SQLException {
        connection.setAutoCommit(false);
    }

    public static void endTransaction() throws SQLException {
        connection.setAutoCommit(true);
    }

    public static void rollback() throws SQLException {
        connection.rollback();
    }

    public static int update(String query){
        Statement statement = null;

        if (connection == null) {
            setConnection();
        }

        try {
            ResultSet resultSet;
            statement = connection.createStatement();
            
            if (statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS) == 0){
                return ERROR;
            }

            resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return ERROR;
        }

        return 1;
    }
}
