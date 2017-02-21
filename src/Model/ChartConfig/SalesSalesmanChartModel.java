package Model.ChartConfig;


import DAO.SaleDAO;
import Util.ChartDataEntity;
import javafx.collections.ObservableList;

import java.util.Map;

public class SalesSalesmanChartModel implements ChartConfigModel {

    private SaleDAO saleDAO;

    public SalesSalesmanChartModel() {
        saleDAO = new SaleDAO();
        saleDAO.getSales();
    }

    @Override
    public ObservableList<ChartDataEntity> getDataForChart(Map config) {
        String conf = (String) config.get("scopeMultiplier");


        return null;
    }

}
