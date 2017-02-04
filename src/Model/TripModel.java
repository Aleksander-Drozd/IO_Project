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
        //tripsObservableList = tripDAO.getTrips();

        return tripsObservableList;
    }

    public boolean addTrip(Trip trip){
        tripsObservableList.add(trip);

        return true;
    }

}
