package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;

public class BarChartController implements Initializable{

    @FXML
    private BarChart<String, Integer> salesPerSalesmanChart;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        XYChart.Series<String, Integer> dataSeries = new XYChart.Series<>();

        dataSeries.getData().add(new XYChart.Data("Janek", 30));
        dataSeries.getData().add(new XYChart.Data("Romek", 45));
        dataSeries.getData().add(new XYChart.Data("Atomek", 70));

        salesPerSalesmanChart.getData().addAll(dataSeries);
    }
}
