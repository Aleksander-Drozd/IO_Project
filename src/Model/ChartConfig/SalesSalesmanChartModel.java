package Model.ChartConfig;


import DAO.EmployeeDAO;
import DAO.SaleDAO;
import POJO.Employee;
import Util.ChartDataEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
    public ObservableList<ChartDataEntity> getDataForChart(Map config) {
        String conf = (String) config.get("scopeMultiplier");

        ObservableList<ChartDataEntity> chartDataEntities = FXCollections.observableArrayList();

        List<Employee> employees = employeeDAO.getEmployees();
        employees.forEach( employee -> {
            int count = getEmployeeSalesCount(employee, config);
            ChartDataEntity entity = new ChartDataEntity(employee.toString(), count);
            chartDataEntities.add(entity);
        });

        return chartDataEntities;
    }

    private int getEmployeeSalesCount(Employee employee, Map config) {
        int count = 0;

        System.out.println( "Employee id: " + employee.getId() );

        return count;
    }

}
