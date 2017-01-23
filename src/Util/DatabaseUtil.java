package Util;

import java.sql.*;

public class DatabaseUtil {

    private static final String dbURL = "jdbc:mysql://localhost:3306/tour_operator";
    private static final String user = "root";
    private static final String password = "";

    private static Connection connection = null;

    public static final int START_TRANSACTION = 1;
    public static final int END_TRANSACTION = 2;

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

    public static int update(String query){
        Statement statement = null;
        int id = 0;

        if (connection == null) {
            setConnection();
        }

        try {
			connection.setAutoCommit(true);
            ResultSet resultSet;
            statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            resultSet = statement.getGeneratedKeys();

            if (resultSet.next())
                return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

        return 1;
    }

    public static int update(String query, int transaction){
        Statement statement = null;
        int id = 0;

        if (connection == null)
            setConnection();

        try {
            if (transaction == START_TRANSACTION)
                connection.setAutoCommit(false);

            ResultSet resultSet;
            statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            resultSet = statement.getGeneratedKeys();

            if (transaction == END_TRANSACTION)
                connection.commit();

            if (resultSet.next())
                return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

        return 1;
    }
}
