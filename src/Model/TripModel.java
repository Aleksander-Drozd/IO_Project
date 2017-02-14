package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import DAO.TripDAO;
import POJO.Trip;

public class TripModel {

    //ToDo to mull over all this
    private ObservableList<Trip> tripsObservableList;
    private TripDAO tripDAO;

    public TripModel() {
        tripDAO = new TripDAO();
        tripsObservableList = FXCollections.observableArrayList();
    }

    public ObservableList<Trip> getTrips(){
        tripsObservableList = tripDAO.getTrips();

        return tripsObservableList;
    }

    public boolean saveTrip(Trip trip){
        if (!tripsObservableList.contains(trip)) {
            return addTrip(trip);
        } else {
            return updateTrip(trip);
        }
    }

    private boolean addTrip(Trip trip) {
        if (tripDAO.addTrip(trip)) {
            tripsObservableList.add(trip);
            return true;
        }
        return false;
    }

    private boolean updateTrip(Trip trip) {
        return tripDAO.updateTrip(trip);
    }

    public boolean contains(Trip trip) {
        return tripsObservableList.contains(trip);
    }
}
