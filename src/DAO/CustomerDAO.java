package DAO;

import POJO.Customer;
import Util.DatabaseUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {
    public Customer getCustomer(int id) {
        Customer customer = null;
        ResultSet resultSet;

        String query = "SELECT * FROM customers WHERE id='" + id + "';";

        resultSet = DatabaseUtil.runSelectQuery(query);
        try {
            if (resultSet.next()) {
                customer = createCustomer(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer;
    }

    private Customer createCustomer(ResultSet resultSet) throws SQLException{
        Customer customer = new Customer();
        int gender;

        customer.setFirstName(resultSet.getString("first_name"));
        customer.setLastName(resultSet.getString("last_name"));
        customer.setCity(resultSet.getString("city"));
        customer.setStreet(resultSet.getString("street"));
        customer.setPostCode(resultSet.getString("post_code"));
        customer.setPhoneNumber(resultSet.getString("phone_number"));

        gender = resultSet.getInt("gender");

        if (gender == 1)
            customer.setGender("Male");
        else
            customer.setGender("Female");

        return customer;
    }
}
