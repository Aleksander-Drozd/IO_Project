package Controller;


import POJO.Trip;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class TripController implements Initializable {

    private Trip trip;

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
