package Model;

import Model.ChartConfig.ChartConfigModel;
import Model.ChartConfig.SalesSalesmanChartModel;
import Util.ChartDataEntity;
import Util.ChartTemplate;
import Util.ChartType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import DAO.EmployeeDAO;
import DAO.SaleDAO;
import DAO.TripDAO;
import POJO.Employee;
import POJO.Sale;
import POJO.Trip;

import java.util.Map;

public class StatisticModel {

    private ObservableList<Trip> tripsObservableList;
    private ObservableList<Employee> employeesObservableList;
    private ObservableList<Sale> salesObservableList;

    private TripDAO tripDAO;
    private SaleDAO saleDAO;
    private EmployeeDAO employeeDAO;

    private ChartConfigModel chartConfigModel;

    public StatisticModel() {
        tripDAO = new TripDAO();
        saleDAO = new SaleDAO();
        employeeDAO = new EmployeeDAO();
    }


    public ObservableList<ChartDataEntity> getDataForChart(Map config) {
        ObservableList<ChartDataEntity> chartData = FXCollections.observableArrayList();

        ChartTemplate chartTemplate = ChartTemplate.valueOf( (String) config.get("template") );

        switch (chartTemplate) {
            case SALES_SALESMAN:
                chartConfigModel = new SalesSalesmanChartModel();
                break;
        }

        chartConfigModel.getDataForChart(config);

        return chartData;
    }
}
