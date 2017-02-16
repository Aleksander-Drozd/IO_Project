package Controller;

import Model.StatisticModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class StatisticController {

    private StatisticModel statisticModel;

    @FXML
    private void handleBarChartButton(){
        Stage dataSaleStage = new Stage();

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/BarChartView.fxml"));

            BorderPane layout = loader.load();

            Scene scene = new Scene(layout);

            dataSaleStage.setScene(scene);
            dataSaleStage.initModality(Modality.APPLICATION_MODAL);
//            dataSaleStage.initOwner(addSaleButton.getScene().getWindow());
            dataSaleStage.setTitle("Sprzedaz");

            dataSaleStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
