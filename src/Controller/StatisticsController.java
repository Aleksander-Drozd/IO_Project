package Controller;

import Model.StatisticModel;
import Util.ChartDataEntity;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
public class StatisticsController {


    @FXML
    private ListView<?> chartSchemesList;
    @FXML
    private TextField numberOfScopes;
    @FXML
    private ChoiceBox<?> scopeType;
    @FXML
    private TextField numberOfAccuracy;
    @FXML
    private ChoiceBox<?> accuracyType;
    @FXML
    private RadioButton barChart;
    @FXML
    private RadioButton lineChart;
    @FXML
    private RadioButton pieChart;

    private StatisticModel statisticModel;

    @FXML
    private void handleChartButton(){


        //List<ChartDataEntity> list = statisticModel.getDataForChart(  );


        Stage dataSaleStage = new Stage();

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/ChartView.fxml"));

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
