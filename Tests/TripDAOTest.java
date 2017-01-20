import DAO.TripDAO;
import Util.DatabaseUtil;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TripDAOTest {
    @Before
    public void setConnection(){
        DatabaseUtil.setConnection();
    }

    @Test
    public void getTrips() throws Exception {
        assertNotEquals(null, TripDAO.getTrips());
    }

}