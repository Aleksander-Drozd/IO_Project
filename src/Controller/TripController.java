package Controller;


import POJO.Trip;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
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
    private boolean correctData = true;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

    public void setTrip(Trip trip) {
        this.trip = trip;

        showTripData();
    }

    @FXML
    private void saveTrip() {
        validateData();

        if (correctData) {
            updateGotTrip();
        }
    }

    @FXML
    private void closeView() {
        System.out.println("Close view!");
    }

    private void updateGotTrip() {
        trip.setTitle( title.getText() );
        trip.setDescription( description.getText() );
        trip.setDays( Integer.parseInt(days.getText()) );
        trip.setPrice( Float.parseFloat(price.getText()) );
        trip.setDate( Date.from( Instant.from( date.getValue().atStartOfDay(ZoneId.systemDefault()) ) ));
    }

    private void validateData() {
        correctData = true;

        validateTitle();
        validateDescription();
        validatePrice();
        validateDays();
        validateDate();
    }


    private void validateTitle() {
        if ( isEmpty(title) ) {
            addErrorCSSClass(title);
            correctData = false;
        } else {
            removeErrorCSSClass(title);
        }
    }

    private void validateDescription() {
        if ( isEmpty(description) ) {
            addErrorCSSClass(description);
            correctData = false;
        } else {
            removeErrorCSSClass(description);
        }
    }

    private void validateDays() {
        if ( isEmpty(days) ) {
            addErrorCSSClass(days);
            correctData = false;
        } else {
            if ( isCorrectIntegerNumber(days) ) {
                removeErrorCSSClass(days);
            } else {
                addErrorCSSClass(days);
                correctData = false;
            }
        }
    }

    private void validatePrice() {
        if ( isEmpty(price) ) {
            addErrorCSSClass(price);
            correctData = false;
        } else {
            if ( isCorrectFloatingPointNumber(price) ) {
                removeErrorCSSClass(price);
            } else {
                addErrorCSSClass(price);
                correctData = false;
            }
        }
    }

    private void validateDate() {
        if ( isCorrectDate(date) ) {
            removeErrorCSSClass(date);
        } else {
            addErrorCSSClass(date);
        }
    }


    private void removeErrorCSSClass(Control field) {
        field.getStyleClass().remove("textfield-error");
        field.getStyle();
    }

    private void addErrorCSSClass(Control field) {
        field.getStyleClass().add("textfield-error");
    }

    private boolean isCorrectDate(DatePicker date) {
        try {
            Date.from( Instant.from( date.getValue().atStartOfDay( ZoneId.systemDefault() ) ) );

            return true;
        } catch (DateTimeException | IllegalArgumentException | NullPointerException e) {
            return false;
        }
    }

    private boolean isCorrectIntegerNumber(TextField field) {
        try {
            Integer.parseInt(field.getText());

            return true;
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
    }

    private boolean isCorrectFloatingPointNumber(TextField field) {
        try {
            Float.parseFloat(field.getText());

            return true;
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
    }

    private boolean isEmpty(TextInputControl field) {
        return field.getText().isEmpty();
    }

}