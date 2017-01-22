package DAO;

import POJO.Customer;
import POJO.Employee;
import POJO.Sale;
import POJO.Trip;
import Util.DatabaseUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SaleDAO {

    private EmployeeDAO employeeDAO;
    private TripDAO tripDAO;
    private CustomerDAO customerDAO;

    public static ObservableList<Sale> getSales(){
        ObservableList<Sale> salesObservableList = FXCollections.observableArrayList();
        ResultSet resultSet;

        String query = "SELECT * FROM sales";

        resultSet = DatabaseUtil.runStatement(query);
        try {
            while (resultSet.next()) {
                salesObservableList.add(createSale(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return salesObservableList;
    }

    private static Sale createSale(ResultSet resultSet) throws SQLException{
        Sale sale = new Sale();

        int employeeId, tripId, customerId;

        employeeId = resultSet.getInt("employee_id");
        tripId = resultSet.getInt("trip_id");
        customerId = resultSet.getInt("customer_id");

        Employee employee = EmployeeDAO.getEmployee(employeeId);
        Trip trip = TripDAO.getTrip(tripId);
        Customer customer = CustomerDAO.getCustomer(customerId);

        sale.setEmployee(employee);
        sale.setTrip(trip);
        sale.setCustomer(customer);

        sale.setQuantity(resultSet.getInt("quantity"));
        sale.setLastName(customer.getLastName());
        sale.setTripTitle(trip.getTitle());
        sale.setSaleDate(resultSet.getDate("date"));

        return sale;
    }
}
