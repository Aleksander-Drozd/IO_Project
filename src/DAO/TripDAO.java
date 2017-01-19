package DAO;

import POJO.Trip;
import Util.DatabaseUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TripDAO {

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
        trip.setDescription(resultSet.getString("description"));
        trip.setDays(resultSet.getInt("days"));
        trip.setPrice(resultSet.getFloat("price"));

        return trip;
    }
}
