package Controller;

import Controller.ChartConfig.ChartConfigController;
import Model.StatisticModel;
import Util.ChartDataEntity;
import Util.ChartType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
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

    private ObservableMap<String, String> chartsTemplates;

    public StatisticsController() {
        chartTypeToggleGroup = new ToggleGroup();
        statisticModel = new StatisticModel();
        chartsTemplates =  FXCollections.observableHashMap();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initChartTypeToggleGroup();
        initChartsTemplatesListView();
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

    private void initChartsTemplatesListView() {
        ObservableList<String> chartsNames = createSortedChartsNamesList();

        chartSchemesList.setItems(chartsNames);

        chartSchemesList.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showChartConfigPane(chartsTemplates.get(newValue))
        );

        chartSchemesList.getSelectionModel().select(0);
    }

    private ObservableList<String> createSortedChartsNamesList() {
        chartsTemplates.put("Ian", "Simple");
        chartsTemplates.put("Sprzedaze pracownikow", "SalesSalesman");

        ObservableList<String> chartsNames = FXCollections.observableArrayList(chartsTemplates.keySet());
        chartsNames.sort(String::compareTo);
        return chartsNames;
    }

    @FXML
    private void handleShowChartButton(){
        Map<String, String> config = chartConfigController.getChartConfig();
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
            dataSaleStage.setTitle("Sprzedaz");

            dataSaleStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showChartConfigPane(String chartTemplate) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/" + chartTemplate + "ChartConfig.fxml"));

            AnchorPane anchorPane = loader.load();

            chartConfigController = loader.getController();

            chartConfig.getChildren().clear();
            chartConfig.getChildren().add(anchorPane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
