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

    public SaleDAO() {
        employeeDAO = new EmployeeDAO();
        tripDAO = new TripDAO();
        customerDAO = new CustomerDAO();
    }

    public ObservableList<Sale> getSales(){
        ObservableList<Sale> salesObservableList = FXCollections.observableArrayList();
        ResultSet resultSet;

        String query = "SELECT * FROM sales WHERE employee_id=" + employeeDAO.getLoggedEmployee().getId() + ";";

        resultSet = DatabaseUtil.runStatement(query);
        try {
            while (resultSet.next()) {
                salesObservableList.add(createSale(resultSet));
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }

        return salesObservableList;
    }

    private Sale createSale(ResultSet resultSet) throws SQLException{
        Sale sale = new Sale();

        int employeeId, tripId, customerId;

        employeeId = resultSet.getInt("employee_id");
        tripId = resultSet.getInt("trip_id");
        customerId = resultSet.getInt("customer_id");

        Employee employee = employeeDAO.getLoggedEmployee();
        Trip trip = tripDAO.getTrip(tripId);
        Customer customer = customerDAO.getCustomer(customerId);

        sale.setEmployee(employee);
        sale.setTrip(trip);
        sale.setCustomer(customer);

        sale.setSaleId(resultSet.getInt("id"));
        sale.setQuantity(resultSet.getInt("quantity"));
        sale.setLastName(customer.getLastName());
        sale.setTripTitle(trip.getTitle());
        sale.setSaleDate(resultSet.getDate("date"));

        return sale;
    }

    public boolean addSale(Sale sale){
        int gender, customerId;

        Customer customer = sale.getCustomer();

        if (customer.getGender().equals("male")) {
            gender = 1;
        } else {
            gender = 0;
        }

        String insertCustomerQuery = "INSERT INTO customers (first_name, last_name, gender, city, street, post_code, phone_number)" + "VALUES ('" +
                customer.getFirstName() + "', '" +
                customer.getLastName() + "', '" +
                gender + "', '" +
                customer.getCity() + "', '" +
                customer.getStreet() + "', '" +
                customer.getPostCode() + "', '" +
                customer.getPhoneNumber() + "');";

        try {
            DatabaseUtil.startTransaction();

            customerId = DatabaseUtil.update(insertCustomerQuery);

            if (customerId == DatabaseUtil.ERROR) {
                throw new SQLException();
            }

            String insertSaleQuery = "INSERT INTO sales (employee_id, trip_id, customer_id, quantity, date) VALUES ('" +
                    employeeDAO.getLoggedEmployee().getId() + "', '" +
                    sale.getTrip().getId() + "', '" +
                    customerId + "', '" +
                    sale.getQuantity() + "', '" +
                    sale.getSaleDate() + "');";

            if (DatabaseUtil.update(insertSaleQuery) == DatabaseUtil.ERROR) {
                throw new SQLException();
            }

            DatabaseUtil.endTransaction();
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();

            return false;
        }

        return true;
    }

    public boolean updateSale(Sale sale) {
        //TODO Check query to update sale (WHERE sale.saleId) and add customerId attribute to customer object

        int gender, customerId;
        Customer customer = sale.getCustomer();

        if (customer.getGender().equals("male")) {
            gender = 1;
        } else {
            gender = 0;
        }

        String updateCustomerQuery = "UPDATE customers SET "
                + "first_name = '" + customer.getFirstName() + "', "
                + "last_name = '" + customer.getLastName() + "', "
                + "gender = '" + gender + "', "
                + "city = '" + customer.getCity() + "', "
                + "street = '" + customer.getStreet() + "', "
                + "post_code = '" + customer.getPostCode() + "', "
                + "phone_number = '" + customer.getPhoneNumber() + "' ";
                //+ "WHERE customer_id = '" + customer.getCustomerId() + "';";

        String updateSaleQuery = "UPDATE sales SET "
                + "employee_id = '" + employeeDAO.getLoggedEmployee().getId() + "', "
                + "trip_id = '" + sale.getTrip().getId() + "', "
                //+ "customer_id = '" + customer.getCustomerId() + "', "
                + "quantity = '" + sale.getQuantity() + "', "
                + "date = '" + sale.getSaleDate() + "' "
                + "WHERE id = " + sale.getSaleId() + ";";

        try {
            DatabaseUtil.startTransaction();

            if (DatabaseUtil.update(updateSaleQuery) == DatabaseUtil.ERROR || DatabaseUtil.update(updateCustomerQuery) == DatabaseUtil.ERROR) {
                throw new SQLException();
            }

            DatabaseUtil.endTransaction();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
