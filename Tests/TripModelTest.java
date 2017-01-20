import Model.TripModel;
import POJO.Trip;
import javafx.collections.ObservableList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TripModelTest {
    int beforeLength;

    @Before
    public void setUp(){
        beforeLength = TripModel.getTrips().size();
    }

    @Test
    public void addTrip() throws Exception {
        TripModel.addTrip(new Trip());

        assertEquals(beforeLength + 1, TripModel.getTrips().size());
    }

}