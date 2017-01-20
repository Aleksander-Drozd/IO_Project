import DAO.TripDAO;
import Util.DatabaseUtil;
import org.junit.Test;

import static org.junit.Assert.*;

public class TripDAOTest {
    @Test
    public void getTrips() throws Exception {
        DatabaseUtil.setConnection();

        assertNotEquals(null, TripDAO.getTrips());
    }

}