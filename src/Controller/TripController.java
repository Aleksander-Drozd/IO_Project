package Controller;


import POJO.Trip;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class TripController implements Initializable {

    @FXML
    private TextField title;
    @FXML
    private TextArea description;
    @FXML
    private TextField days;
    @FXML
    private TextField price;
    @FXML
    private DatePicker date;

    private Trip trip;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setTrip(Trip trip) {
        this.trip = trip;

        showTripData();
    }

    private void showTripData() {
        int tripDays = trip.getDays();
        float tripPrice = trip.getPrice();

        title.setText( trip.getTitle() );
        description.setText( trip.getDescription() );
        days.setText( tripDays != 0 ? Integer.toString(tripDays) : "" );
        price.setText( tripPrice != 0.0f ? Float.toString(tripPrice) : "" );
        date.setValue( trip.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() );
    }

}