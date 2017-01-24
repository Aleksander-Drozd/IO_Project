import DAO.EmployeeDAO;
import DAO.SaleDAO;
import POJO.Employee;
import Util.DatabaseUtil;
import mockit.Mock;
import mockit.MockUp;
import mockit.Verifications;
import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;

import static org.junit.Assert.*;

public class SaleDAOTest {


    EmployeeDAO employeeDAO;

    @Test
    public void getSales() throws Exception {
        new MockUp<DatabaseUtil>() {
            @Mock
            public int update(String query) {
                return 0;
            }

            @Mock
            public ResultSet runStatement(String query) {
                return null;
            }

            @Mock
            public void startTransaction() {
            }

            @Mock
            public void endTransaction() {
            }
        };

        new MockUp<EmployeeDAO>() {
            @Mock
            public Employee getLoggedEmployee() {
                return new Employee();
            }
        };

        SaleDAO.getSales();

        assertNotNull("Return ObservableList<Sale>", SaleDAO.getSales());
        assertEquals("Size list should be 0", SaleDAO.getSales().size(), 0);
    }

    @Test
    public void addSale() throws Exception {

    }

    @Test
    public void updateSale() throws Exception {

    }

}