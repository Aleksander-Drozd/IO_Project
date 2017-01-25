import DAO.EmployeeDAO;
import DAO.SaleDAO;
import POJO.Customer;
import POJO.Employee;
import POJO.Sale;
import POJO.Trip;
import Util.DatabaseUtil;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.Verifications;
import mockit.internal.state.MockClasses;
import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;

import static org.junit.Assert.*;

public class SaleDAOTest {

    EmployeeDAO employeeDAO;

    SaleDAO saleDAO;

    Sale sale;
    Customer customer;
    Trip trip;
    Employee employee;

    @Before
    public void setUp() {
        sale = new Sale();
        customer = new Customer();
        trip = new  Trip();
        employee = new Employee();

        sale.setCustomer(customer);
        sale.setTrip(trip);
        sale.setEmployee(employee);

        saleDAO = new SaleDAO();
    }

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

        assertNotNull("Return ObservableList<Sale>", saleDAO.getSales());
        assertEquals("Size list should be 0", saleDAO.getSales().size(), 0);

    }

    @Test
    public void addSale() throws Exception {
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

        int salesCount = saleDAO.getSales().size();
        assertTrue("Should return true", saleDAO.addSale(sale));
        assertEquals("Should add one sale",salesCount, saleDAO.getSales().size());
    }

}