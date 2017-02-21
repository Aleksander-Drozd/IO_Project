package Controller;

import Controller.ChartConfig.ChartConfigController;
import Model.StatisticModel;
import Util.ChartDataEntity;
import Util.ChartType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class StatisticsController implements Initializable {


    @FXML
    private ListView<String> chartSchemesList;
    @FXML
    private Pane chartConfig;
    @FXML
    private RadioButton barChartRadioButton;
    @FXML
    private RadioButton lineChartRadioButton;
    @FXML
    private RadioButton pieChartRadioButton;

    private ToggleGroup chartTypeToggleGroup;
    private ChartType chartType;

    private ChartConfigController chartConfigController;
    private StatisticModel statisticModel;

    public StatisticsController() {
        chartTypeToggleGroup = new ToggleGroup();
        statisticModel = new StatisticModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initChartTypeToggleGroup();

        chartSchemesList.setItems(
                FXCollections.observableArrayList("Julia", "Ian", "Sue", "Matthew", "Hannah", "Stephan", "Denise")
        );

        chartSchemesList.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showChartConfigPane(newValue)
        );

        chartSchemesList.getSelectionModel().select(0);
    }

    private void initChartTypeToggleGroup() {
        barChartRadioButton.setToggleGroup(chartTypeToggleGroup);
        lineChartRadioButton.setToggleGroup(chartTypeToggleGroup);
        pieChartRadioButton.setToggleGroup(chartTypeToggleGroup);

        chartTypeToggleGroup.selectedToggleProperty().addListener((ov, toggle, new_toggle) -> {
            if (new_toggle == barChartRadioButton) {
                chartType = ChartType.BAR_CHART;
            } else if (new_toggle == lineChartRadioButton) {
                chartType = ChartType.LINE_CHART;
            } else if (new_toggle == pieChartRadioButton) {
                chartType = ChartType.PIE_CHART;
            }
        });
    }

    @FXML
    private void handleChartButton(){
        System.out.print("");
        Map config = chartConfigController.getChartConfig();
        config.put("type", chartType.toString());
        ObservableList<ChartDataEntity> list = statisticModel.getDataForChart(config);

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

    private void showChartConfigPane(String newValue) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/SimpleChartConfig.fxml"));

            AnchorPane anchorPane = loader.load();

            chartConfigController = loader.getController();

            chartConfig.getChildren().add( anchorPane );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
