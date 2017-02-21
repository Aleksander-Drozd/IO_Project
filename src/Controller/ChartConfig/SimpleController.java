package Controller.ChartConfig;


import Util.ChartTemplate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class SimpleController implements Initializable, ChartConfigController {

    private final static int DAY = 1;
    private final static int WEEK = 7;
    private final static int MONTH = 30;
    private final static int YEAR = 365;

    @FXML
    private TextField numberOfScopes;
    @FXML
    private ComboBox scopeType;
    @FXML
    private TextField numberOfAccuracy;
    @FXML
    private ComboBox accuracyType;

    private ObservableMap<String, Integer> multiplierType;

    public SimpleController() {
        Map<String, Integer> multipliers = new HashMap<>();
        multipliers.put("dzien", DAY);
        multipliers.put("tydzien", WEEK);
        multipliers.put("miesiac", MONTH);
        multipliers.put("rok", YEAR);

        multiplierType = FXCollections.observableHashMap();
        multiplierType.putAll(multipliers);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scopeType.setItems(FXCollections.observableArrayList(multiplierType.keySet()));
        accuracyType.setItems(FXCollections.observableArrayList(multiplierType.keySet()));
    }

    @Override
    public Map<String, String> getChartConfig() {
        Map<String, String> config  = new HashMap<>();

        config.put("template", ChartTemplate.SALES_SALESMAN.toString());
        config.put("scopeMultiplier", String.valueOf(multiplierType.get(scopeType.getValue().toString())));
        config.put("scope", numberOfScopes.getText());
        config.put("accuracyMultiplier", String.valueOf(multiplierType.get(accuracyType.getValue().toString())) );
        config.put("accuracy", numberOfAccuracy.getText());

        return config;
    }
}
