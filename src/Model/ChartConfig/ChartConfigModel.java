package Model.ChartConfig;


import Util.ChartDataEntity;
import javafx.collections.ObservableList;

import java.util.Map;

public interface ChartConfigModel {

    public ObservableList<ChartDataEntity> getDataForChart(Map<String, String> config);

}
