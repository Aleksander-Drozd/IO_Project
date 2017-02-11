package DAO;

import POJO.Trip;
import Util.DatabaseUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TripDAO {

    public ObservableList<Trip> getTrips(){
        ObservableList<Trip> tripObservableList = FXCollections.observableArrayList();
        ResultSet resultSet;

        String query = "SELECT * FROM trips";

        resultSet = DatabaseUtil.runSelectQuery(query);
        try {
            while (resultSet.next()) {
                tripObservableList.add(createTrip(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.closeResultSetAndConnectedStatement(resultSet);
        }

        return tripObservableList;
    }

    public Trip getTrip(int id){
        ResultSet resultSet;
        Trip trip = null;

        String query = "SELECT * FROM trips WHERE id='" + id + "';";

        resultSet = DatabaseUtil.runSelectQuery(query);
        try {
            if (resultSet.next()) {
                trip = createTrip(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.closeResultSetAndConnectedStatement(resultSet);
        }

        return trip;
    }

    private Trip createTrip(ResultSet resultSet) throws SQLException{
        Trip trip = new Trip();

        trip.setId(resultSet.getInt("id"));
        trip.setTitle(resultSet.getString("title"));
        trip.setDescription(resultSet.getString("description"));
        trip.setDays(resultSet.getInt("days"));
        trip.setPrice(resultSet.getFloat("price"));
        trip.setDate(resultSet.getDate("date"));

        return trip;
    }
}
