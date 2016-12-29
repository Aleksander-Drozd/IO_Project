package Model;

import javafx.collections.ObservableList;
import DAO.TripDAO;
import POJO.Trip;

public class TripModel {

    //ToDo to mull over all this
    private static ObservableList<Trip> tripsObservableList;
    private static TripDAO tripDAO;

    public static ObservableList<Trip> getTrips(){
        if(tripsObservableList == null)
            tripsObservableList = TripDAO.getTrips();

        return tripsObservableList;
    }

}
