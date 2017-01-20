import DAO.EmployeeDAO;
import POJO.Employee;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmployeeDAOTest {
    EmployeeDAO employeeDAO;
    @Before
    public  void setUp(){
        employeeDAO = new EmployeeDAO();
    }
    @Test
    public void getEmployee() throws Exception {
        Employee employee = employeeDAO.getEmployee("test", "test");

        assertNotEquals(null, employee);
        assertEquals("test", employee.getFirstName());
        assertEquals("test", employee.getLastName());
    }

}