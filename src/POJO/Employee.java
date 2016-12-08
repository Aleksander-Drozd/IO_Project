package POJO;

public class Employee {

    private int id;
    private String firstName;
    private String lastName;
    private String position;

    public Employee() {
    }

    public Employee(String forTests) {
        id = 5;
        firstName = "Marek";
        lastName = "Milkowski";
        position = "kierownik";
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
