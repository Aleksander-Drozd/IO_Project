package Model;

import javafx.collections.ObservableList;
import DAO.TripDAO;
import POJO.Trip;

public class TripModel {

    //ToDo to mull over all this
    private ObservableList<Trip> tripsObservableList;
    private TripDAO tripDAO;

    public TripModel() {
        tripDAO = new TripDAO();
    }

    public ObservableList<Trip> getTrips(){
        if(tripsObservableList == null) {
            tripsObservableList = TripDAO.getTrips();
        }

        return tripsObservableList;
    }

    public void addTrip(Trip trip){
        tripsObservableList.add(trip);

        //TODO Add trip to Database
    }

}
