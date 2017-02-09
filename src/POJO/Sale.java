package POJO;

import java.util.Date;

public class Sale {

    private int saleId;

    private Employee employee;
    private Trip trip;
    private Customer customer;
    private int quantity;

    // Data to TableView
    private String lastName;
    private String tripTitle;
    private Date saleDate;

    public Sale(){
        employee = new Employee("");
        trip = new Trip("");
        customer = new Customer("");
        saleDate = new Date();
    }

    public Sale(String forTests) {
        employee = new Employee("");
        trip = new Trip("");
        customer = new Customer("");
        saleDate = new Date();
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    //for tableView
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTripTitle() {
        return tripTitle;
    }

    public void setTripTitle(String tripTitle) {
        this.tripTitle = tripTitle;
    }

    public String getSaleDate() {
        return saleDate.toString();
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }
}
