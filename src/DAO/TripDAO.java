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

    public boolean addTrip(Trip trip){
        int tripId;

        String insertTripQuery = "INSERT INTO trips (title, description, days, price, date VALUES('" +
                trip.getTitle() + "', '" +
                trip.getDescription() + "', '" +
                trip.getDays() + "', '" +
                trip.getPrice() + "', '" +
                trip.getDate().toString() + "');";

        tripId = DatabaseUtil.update(insertTripQuery);

        if (tripId == DatabaseUtil.ERROR)
            return false;

        trip.setId(tripId);

        return true;
    }

    public boolean updateTrip(Trip trip){
        String updateTripQuery = "UPDATE trips SET " +
                "title = '" + trip.getTitle() + "', " +
                "description = '" + trip.getDescription() + "', " +
                "days = '" + trip.getDays() + "', " +
                "price = '" + trip.getPrice() + "', " +
                "date = '" + trip.getDate() + "' " +
                "WHERE id = '" + trip.getId() + "';";

        if (DatabaseUtil.update(updateTripQuery) == DatabaseUtil.ERROR)
            return false;

        return true;
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
