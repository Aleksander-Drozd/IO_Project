package DAO;

import DAO.Filters.SaleDAOfilter;
import POJO.Customer;
import POJO.Employee;
import POJO.Sale;
import POJO.Trip;
import Util.DatabaseUtil;
import Util.Dialog;
import Util.ErrorDescriptions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

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
        ResultSet resultSet = null;

        String query = "SELECT * FROM sales WHERE employee_id = ? ORDER BY date DESC;";

        try {
            PreparedStatement preparedStatement = DatabaseUtil.prepareStatement(query);

            preparedStatement.setInt(1, EmployeeDAO.getLoggedEmployee().getId());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                salesObservableList.add(createSale(resultSet));
            }
        } catch (SQLException | NullPointerException e) {
            Dialog.displayErrorDialog(ErrorDescriptions.DATABASE_ERROR);
        } finally {
            DatabaseUtil.closeResultSetAndConnectedStatement(resultSet);
        }

        return salesObservableList;
    }

    private Sale createSale(ResultSet resultSet) throws SQLException{
        Sale sale = new Sale();

        int employeeId, tripId, customerId;

        employeeId = resultSet.getInt("employee_id");
        tripId = resultSet.getInt("trip_id");
        customerId = resultSet.getInt("customer_id");

        Employee employee = EmployeeDAO.getLoggedEmployee();
        Trip trip = tripDAO.getTrip(tripId);
        Customer customer = customerDAO.getCustomer(customerId);

        sale.setEmployee(employee);
        sale.setTrip(trip);
        sale.setCustomer(customer);

        sale.setSaleId(resultSet.getInt("id"));
        sale.setQuantity(resultSet.getInt("quantity"));
        sale.setLastName(customer.getLastName());
        sale.setTripTitle(trip.getTitle());
        sale.setSaleDate(resultSet.getDate("date").toLocalDate());

        return sale;
    }

    //Todo Refactor name or method body. It's doing too much
    public boolean addSale(Sale sale){
        int gender, customerId, saleId;

        Customer customer = sale.getCustomer();

        if (customer.getGender().equals("Male")) {
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
            customer.setId(customerId);

            if (customerId == DatabaseUtil.ERROR) {
                throw new SQLException();
            }

            String insertSaleQuery = "INSERT INTO sales (employee_id, trip_id, customer_id, quantity, date) VALUES ('" +
                    EmployeeDAO.getLoggedEmployee().getId() + "', '" +
                    sale.getTrip().getId() + "', '" +
                    customer.getId() + "', '" +
                    sale.getQuantity() + "', '" +
                    sale.getSaleDate() + "');";

            saleId = DatabaseUtil.update(insertSaleQuery);
            sale.setSaleId(saleId);

            if (saleId == DatabaseUtil.ERROR) {
                throw new SQLException();
            }

            DatabaseUtil.endTransaction();
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();

            try {
                DatabaseUtil.rollback();
                DatabaseUtil.endTransaction();
            } catch (SQLException exception){

            }

            return false;
        }

        return true;
    }

    //ToDo refactor
    public boolean updateSale(Sale sale) {
        int gender;
        Customer customer = sale.getCustomer();

        if (customer.getGender().equals("Male")) {
            gender = 1;
        } else {
            gender = 0;
        }

        String updateSaleQuery = "UPDATE sales SET trip_id = ?, quantity = ?, date = ? WHERE id = ?;";

        PreparedStatement updateSalePreparedStatement = null;
        try {
            updateSalePreparedStatement = DatabaseUtil.prepareStatement(updateSaleQuery);

            updateSalePreparedStatement.setInt(1, sale.getTrip().getId());
            updateSalePreparedStatement.setInt(2, sale.getQuantity());
            updateSalePreparedStatement.setObject(3, sale.getSaleDateAsDate());
            updateSalePreparedStatement.setInt(4, sale.getSaleId());
        } catch (SQLException e) {

        }

        String updateCustomerQuery = "UPDATE customers SET first_name = ?, last_name = ?, gender = ?, city = ?, "
                + "street = ?, post_code = ?, phone_number = ? WHERE id = ?;";

        PreparedStatement updateCustomerPreparedStatement = null;
        try {
            updateCustomerPreparedStatement = DatabaseUtil.prepareStatement(updateCustomerQuery);

            updateCustomerPreparedStatement.setString(1, customer.getFirstName());
            updateCustomerPreparedStatement.setString(2, customer.getLastName());
            updateCustomerPreparedStatement.setInt(3, gender);
            updateCustomerPreparedStatement.setString(4, customer.getCity());
            updateCustomerPreparedStatement.setString(5, customer.getStreet());
            updateCustomerPreparedStatement.setString(6, customer.getPostCode());
            updateCustomerPreparedStatement.setString(7, customer.getPhoneNumber());
            updateCustomerPreparedStatement.setInt(8, customer.getId());
        } catch (SQLException e) {

        }

        try {
            DatabaseUtil.startTransaction();

            updateSalePreparedStatement.executeUpdate();
            updateCustomerPreparedStatement.executeUpdate();

            DatabaseUtil.endTransaction();
        } catch (SQLException e) {
            e.printStackTrace();

            try {
                DatabaseUtil.rollback();
                DatabaseUtil.endTransaction();
            } catch (SQLException exception){
                e.printStackTrace();
            }
            return false;
        } finally {
            DatabaseUtil.closeStatemnt(updateSalePreparedStatement);
            DatabaseUtil.closeStatemnt(updateCustomerPreparedStatement);
        }

        return true;
    }

    public int getEmployeesNumberOfSales(int employeeId, LocalDate beginDate, LocalDate endDate){
        String query = "SELECT count(*) FROM sales WHERE employee_id = ? AND date BETWEEN ? AND ?";
        int numberOfSales = 0;

        try {
            PreparedStatement preparedStatement = DatabaseUtil.prepareStatement(query);

            preparedStatement.setInt(1, employeeId);
            preparedStatement.setObject(2, beginDate);
            preparedStatement.setObject(3, endDate);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                numberOfSales = resultSet.getInt(1);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return numberOfSales;
    }

    public Map<Integer, Integer> getEmployeesNumberOfSales(SaleDAOfilter filter) {
        Map<Integer, Integer> resultMap = new HashMap<>();

        StringJoiner filtersJoiner = new StringJoiner("AND");

        //TODO check checked Employee
        if (!filter.getEmployeesIds().isEmpty()) {
            filtersJoiner.add(generateEmployeeQueryFilter(filter.getEmployeesIds()));
        }
        if (!filter.getTripsIds().isEmpty()) {
            filtersJoiner.add(generateTripQueryFilter(filter.getTripsIds()));
        }
        if (filter.getBeginDate() != null) {
            filtersJoiner.add(generateDateQueryFilter(filter.getBeginDate(), filter.getEndDate()));
        }

        String filterQuery = filtersJoiner.toString();

        if (!filterQuery.isEmpty()) {
            filterQuery = " WHERE" + filterQuery;
        }

        String query = "SELECT employee.Id AS Id, COUNT(sales.employee_id) AS Count " +
                " FROM employee " +
                " LEFT JOIN sales ON sales.employee_id = employee.id " +
                filterQuery +
                " GROUP BY employee.Id;";

        ResultSet resultSet = DatabaseUtil.runSelectQuery(query);

        try {
            while(resultSet.next()) {
                resultMap.put(
                        resultSet.getInt("Id"),
                        resultSet.getInt("Count"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseUtil.closeResultSetAndConnectedStatement(resultSet);
        }

        return resultMap;
    }

    private String generateEmployeeQueryFilter(List<Integer> employeesIds) {
        StringJoiner filter = new StringJoiner(" OR ", " ( ", " ) ");
        for (Integer id: employeesIds) {
            filter.add("sales.employee_id = " + id);
        }
        return filter.toString();
    }

    private String generateTripQueryFilter(List<Integer> tripsIds) {
        StringJoiner filter = new StringJoiner(" OR ", " ( ", " ) ");
        for (Integer id: tripsIds) {
            filter.add("sales.trip_id = " + id);
        }
        return filter.toString();
    }

    private String generateDateQueryFilter(LocalDate beginDate, LocalDate endDate) {
       return " date BETWEEN '" + beginDate.toString() + "' AND '" + endDate.toString() +"' " ;
    }
}

