package POJO;


import java.util.Date;

public class Trip {

    private String title;
    private String description;
    private int days;
    private float price;
    private Date date;

    public Trip(){

    }

    public Trip(String forTests) {
        title = "Super wycieczka";
        description = "To jest naprawde super wycieczka";
        days = 3;
        price = 197.22f;
        date = new Date();
    }

    @Override
    public String toString() {
        return  title;
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

}
