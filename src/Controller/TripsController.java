package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class TripsController implements Initializable {

    private TripController tripController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void handleButtonAddTrip() {
        System.out.println("Add!");
    }

    @FXML
    private void handleButtonEditTrip() {
        System.out.println("Edit!");
    }

}
