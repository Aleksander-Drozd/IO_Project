package Controller;

import Model.ChartModel;
import Util.ChartType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.Chart;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ChartController implements Initializable{

    @FXML
    private BorderPane mainContent;

    private ChartModel chartModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void generateChart(List<Object> dataForChart, ChartType chartType) {
        switch (chartType) {
            case PIE_CHART:
                setChart(chartModel.generatePieChart(dataForChart));
                break;
            case LINE_CHART:
                setChart(chartModel.generateLineChart(dataForChart));
                break;
            case BAR_CHART:
                setChart(chartModel.generateBarChart(dataForChart));
                break;
        }
    }

    private void setChart(Chart chart) {
        mainContent.setCenter(chart);
    }
}
