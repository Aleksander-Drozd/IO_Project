package POJO;

import java.time.LocalDate;

public class Sale {

    private int saleId;

    private Employee employee;
    private Trip trip;
    private Customer customer;
    private int quantity;

    // Data to TableView
    private String lastName;
    private String tripTitle;
    private LocalDate saleDate;

    public Sale(){
        employee = new Employee("");
        trip = new Trip("");
        customer = new Customer("");
        saleDate = LocalDate.now();
    }

    public Sale(String forTests) {
        employee = new Employee("");
        trip = new Trip("");
        customer = new Customer("");
        saleDate =  LocalDate.now();
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

    public LocalDate getSaleDateAsDate(){
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public Sale createCopy() {
        Sale sale = new Sale();

        sale.setEmployee(this.employee.createCopy());
        sale.setTrip(this.trip.createCopy());
        sale.setCustomer(this.customer.createCopy());
        sale.setQuantity(this.quantity);
        sale.setSaleId(this.saleId);

        sale.setLastName(this.lastName);
        sale.setTripTitle(this.tripTitle);
        sale.setSaleDate(this.saleDate);

        return sale;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "saleId=" + saleId +
                ", employee=" + employee +
                ", trip=" + trip +
                ", customer=" + customer +
                ", quantity=" + quantity +
                ", lastName='" + lastName + '\'' +
                ", tripTitle='" + tripTitle + '\'' +
                ", saleDate=" + saleDate +
                '}';
    }
}
