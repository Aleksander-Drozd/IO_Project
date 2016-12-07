package Model;

import javafx.collections.ObservableList;
import DAO.EmployeeDAO;
import DAO.SaleDAO;
import DAO.TripDAO;
import POJO.Employee;
import POJO.Sale;
import POJO.Trip;

public class StatisticModel {

    private ObservableList<Trip> tripsObservableList;
    private ObservableList<Employee> employeesObservableList;
    private ObservableList<Sale> salesObservableList;

    private TripDAO tripDAO;
    private SaleDAO saleDAO;
    private EmployeeDAO employeeDAO;

}
