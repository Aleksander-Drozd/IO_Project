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
        position = "Manager";
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

    public Employee copy() {
        Employee employee = new Employee();

        employee.setId(this.id);
        employee.setFirstName(this.firstName);
        employee.setLastName(this.lastName);
        employee.setPosition(this.position);

        return employee;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
