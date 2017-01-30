import DAO.TripDAO;
import Model.TripModel;
import POJO.Trip;
import Util.DatabaseUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mockit.*;
import mockit.integration.junit4.JMockit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JMockit.class)
public class TripModelTest {

    int beforeLength;

    @Mocked
    TripDAO tripDAO;

    @Test
    public void addTrip() throws Exception {
        TripModel tripModel = new TripModel();
        assertTrue(tripModel.addTrip(new Trip()));
    }

    @Test
    public void getTripsTest() throws Exception {
        TripModel tripModel = new TripModel();
        assertNotNull("Should return ObservableList", tripModel.getTrips());

        new Verifications(){{ tripDAO.getTrips(); times = 1; }};
    }

}