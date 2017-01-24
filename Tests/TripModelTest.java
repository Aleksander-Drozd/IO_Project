import DAO.TripDAO;
import Model.TripModel;
import POJO.Trip;
import mockit.Mocked;
import mockit.Verifications;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TripModelTest {

    int beforeLength;

    @Mocked
    TripDAO tripDAO;

    @Before
    public void setUp(){
        beforeLength = TripModel.getTrips().size();
    }

    @Test
    public void addTripLengthTest() throws Exception {
        TripModel.addTrip(new Trip());
        assertEquals(beforeLength + 1, TripModel.getTrips().size());
    }

    @Test
    public void addTripDAOTest() throws Exception {
        TripModel.getTrips();

        new Verifications() {
            {
                TripDAO.getTrips();
            }
        };
    }

}