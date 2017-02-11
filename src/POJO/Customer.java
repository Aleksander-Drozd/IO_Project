package POJO;

public class Customer {

    private int id;
    private String firstName;
    private String lastName;
    private String gender;
    private String city;
    private String street;
    private String postCode;
    private String phoneNumber;

    public Customer(){
        id = 0;
        firstName = "";
        lastName = "";
        city = "";
        street = "";
        gender = "";
        postCode = "";
        phoneNumber = "";
    }

    public Customer(String forTests) {
        firstName = "Jan";
        lastName = "Panda";
        city = "Wroclove <3";
        street = "Mikolaja";
        gender = "Male";
        postCode = "99-333";
        phoneNumber = "332908123";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Customer createCopy() {
        Customer customer = new Customer();

        customer.setId(this.id);
        customer.setFirstName(this.firstName);
        customer.setLastName(this.lastName);
        customer.setCity(this.city);
        customer.setPostCode(this.postCode);
        customer.setStreet(this.street);
        customer.setPhoneNumber(this.phoneNumber);
        customer.setGender(this.gender);

        return customer;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", postCode='" + postCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
