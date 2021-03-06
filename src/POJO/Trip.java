package POJO;


import java.util.Date;

public class Trip {

    private int id;
    private String title;
    private String description;
    private int days;
    private float price;
    private Date date;

    public Trip(){
        id = -1;
        title = "";
        description = "";
        days = 0;
        price = 0.0f;
        date = new Date();
    }

    public Trip(String forTests) {
        title = "Super wycieczka";
        description = "To jest naprawde super wycieczka";
        days = 3;
        price = 197.22f;
        date = new Date();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Trip createCopy() {
        Trip trip = new Trip();

        trip.setId(this.id);
        trip.setTitle(this.title);
        trip.setDescription(this.description);
        trip.setPrice(this.price);
        trip.setDate(this.date);
        trip.setDays(this.days);

        return trip;
    }

    @Override
    public String toString() {
        return  title;
    }
}
