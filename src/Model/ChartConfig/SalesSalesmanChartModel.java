package Model.ChartConfig;


import DAO.EmployeeDAO;
import DAO.Filters.SaleDAOfilter;
import DAO.SaleDAO;
import POJO.Employee;
import Util.ChartDataEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class SalesSalesmanChartModel implements ChartConfigModel {

    private SaleDAO saleDAO;
    private EmployeeDAO employeeDAO;

    public SalesSalesmanChartModel() {
        saleDAO = new SaleDAO();
        employeeDAO = new EmployeeDAO();
    }

    @Override
    public ObservableList<ChartDataEntity> getDataForChart(Map<String, String> config) {
        String conf = config.get("scopeMultiplier");

        ObservableList<ChartDataEntity> chartDataEntities = FXCollections.observableArrayList();

        List<Employee> employees = employeeDAO.getEmployees();
        employees.forEach( employee -> {
            int count = getEmployeeSalesCount(employee, config);
            ChartDataEntity entity = new ChartDataEntity(employee.toString(), count);
            chartDataEntities.add(entity);
        });

        return chartDataEntities;
    }

    private int getEmployeeSalesCount(Employee employee, Map<String, String> config) {
        //count = saleDAO.getEmployeesNumberOfSales(employee.getId(), beginDate, endDate);
        SaleDAOfilter filter = generateFilter(config);

        saleDAO.getEmployeesNumberOfSales(filter);

        return 0;
    }

    private SaleDAOfilter generateFilter(Map<String, String> config) {
        int count = 0, scopeInDays, accuracyInDays;

        scopeInDays = Integer.parseInt(config.get("scope")) * Integer.parseInt(config.get("scopeMultiplier"));
        accuracyInDays = Integer.parseInt(config.get("accuracy")) * Integer.parseInt(config.get("accuracyMultiplier"));

        LocalDate beginDate = LocalDate.now().minusDays(scopeInDays);
        LocalDate endDate = LocalDate.now();


        return new SaleDAOfilter().employeeId(2).employeeId(1).scopeBetweenDates(LocalDate.now().minusDays(200), LocalDate.now());
    }
}
