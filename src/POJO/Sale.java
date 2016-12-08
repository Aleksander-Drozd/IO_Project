package POJO;


import java.util.Date;

public class Sale {

    private Employee employee;
    private Trip trip;
    private Customer customer;
    private Date saleDate;
    private int quantity;

    public Sale(String forTests) {
        employee = new Employee();
        trip = new Trip();
        customer = new Customer();
        saleDate = new Date();
        quantity = 7;
    }

    public String getLastName() {
        return customer.getLastName();
    }

    public String getTripTitle() {
        return trip.getTitle();
    }

    public String getSaleDate() {
        return saleDate.toString();
    }

    public void setLastName(String lastName) {
        customer.setLastName(lastName);
    }

    public void setTripTitle(String tripTitle) {
        trip.setTitle(tripTitle);
    }

    public void setSaleDate() {
        //TODO Convert String -> Date
    }

}
