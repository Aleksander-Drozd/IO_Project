package DAO;

import POJO.Trip;
import Util.DatabaseUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TripDAO {

    private Connection connection;

    public TripDAO() {
        connection  = DatabaseUtil.getConnection();
    }

    public static ObservableList<Trip> getTrips(){
        ObservableList<Trip> tripObservableList = FXCollections.observableArrayList();
        ResultSet resultSet;

        String query = "SELECT * FROM trips";

        resultSet = DatabaseUtil.runStatement(query);
        try {
            while (resultSet.next()) {
                tripObservableList.add(createTrip(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tripObservableList;
    }

    private static Trip createTrip(ResultSet resultSet) throws SQLException{
        Trip trip = new Trip();

        trip.setTitle(resultSet.getString("title"));
        trip.setDesciption(resultSet.getString("description"));
        trip.setDays(resultSet.getInt("days"));
        trip.setPrice(resultSet.getFloat("price"));

        return trip;
    }

}
