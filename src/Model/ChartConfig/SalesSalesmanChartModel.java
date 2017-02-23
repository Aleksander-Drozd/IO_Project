package Model.ChartConfig;


import DAO.EmployeeDAO;
import DAO.SaleDAO;
import POJO.Employee;
import Util.ChartDataEntity;
import Util.DateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Calendar;
import java.util.Date;
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
        int count = 0, scopeInDays, accuracyInDays;

        scopeInDays = Integer.parseInt(config.get("scope")) * Integer.parseInt(config.get("scopeMultiplier"));
        accuracyInDays = Integer.parseInt(config.get("accuracy")) * Integer.parseInt(config.get("accuracyMultiplier"));

        Date beginningDate = addDaysToDate(new Date(), -scopeInDays);

        System.out.println( "Employee id: " + employee.getId() );
        System.out.println(DateUtil.toStandardString(beginningDate));

        return count;
    }

    private Date addDaysToDate(Date date, int days){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

}
