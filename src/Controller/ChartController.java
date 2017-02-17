package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.Chart;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ChartController implements Initializable{

    @FXML
    private BorderPane mainContent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setChart(Chart chart) {
        mainContent.setCenter(chart);
    }
}
