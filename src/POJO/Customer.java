package POJO;

public class Customer {

    private String firstName;
    private String lastName;
    private String sex;
    private String city;
    private String street;
    private String postCode;
    private String phoneNumber;

    public Customer(){

    }

    public Customer(String forTests) {
        firstName = "Jan";
        lastName = "Panda";
        city = "Wroclove <3";
        street = "Mikolaja";
        sex = "male";
        postCode = "99-333";
        phoneNumber = "332908123";
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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
}
