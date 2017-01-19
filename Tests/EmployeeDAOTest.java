import DAO.EmployeeDAO;
import POJO.Employee;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmployeeDAOTest {
    @Test
    public void getEmployee() throws Exception {
        EmployeeDAO employeeDAO = new EmployeeDAO();

        Employee employee = employeeDAO.getEmployee("test", "test");

        assertEquals("test", employee.getFirstName());
        assertEquals("test", employee.getLastName());
    }

}