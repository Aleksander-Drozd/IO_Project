package Util;

import javafx.scene.control.Alert;

public class Dialog {

    public static void displayErrorDialog(String header, String description){
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Blad");
        alert.setHeaderText(header);
        alert.setContentText(description);

        alert.showAndWait();
    }

    public static void displayInfoDialog(String header, String description){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacja");
        alert.setHeaderText(header);
        alert.setContentText(description);

        alert.showAndWait();
    }
}
