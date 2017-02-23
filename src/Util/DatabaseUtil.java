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

    public static PreparedStatement prepareStatement(String query) throws SQLException{
        return connection.prepareStatement(query);
    }

    public static ResultSet runSelectQuery(String query) {
        Statement statement = null;
        ResultSet resultSet = null;

        if (connection == null) {
            setConnection();
        }

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            //closeStatement(statement);
        }
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

    public static void closeResultSetAndConnectedStatement(ResultSet resultSet){
        try {
            Statement localStatement = resultSet.getStatement();

            resultSet.close();
            localStatement.close();
        } catch (SQLException e){

        }
    }

    public static void closeConnection(){
        try {
            if(connection != null)
                connection.close();
        } catch (SQLException e) {

        }
    }

    public static void closeStatemnt(Statement statement){
        try {
            statement.close();
        } catch (SQLException e) {

        }
    }

    public static int update(String query){
        Statement statement = null;
        ResultSet resultSet = null;

        if (connection == null) {
            setConnection();
        }

        try {
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
        } finally {
            closeResultSetAndConnectedStatement(resultSet);
        }

        return 1;
    }
}
