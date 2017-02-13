package Controller;


import POJO.Trip;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
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

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}