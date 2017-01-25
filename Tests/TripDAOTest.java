import DAO.TripDAO;
import Util.DatabaseUtil;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TripDAOTest {

    TripDAO tripDAO;

    @Before
    public void setUp(){
        DatabaseUtil.setConnection();
        tripDAO = new TripDAO();
    }

    @Test
    public void getTrips() throws Exception {
        assertNotEquals(null, tripDAO.getTrips());
    }

}