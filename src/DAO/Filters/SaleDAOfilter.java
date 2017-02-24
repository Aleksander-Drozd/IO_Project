package DAO.Filters;


import com.sun.istack.internal.Nullable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SaleDAOfilter {

    private List<Integer> employeesIds;
    private List<Integer> tripsIds;
    private LocalDate beginDate;
    private LocalDate endDate;

    public SaleDAOfilter() {
        employeesIds = new ArrayList<>();
        tripsIds = new ArrayList<>();
        beginDate = null;
        endDate = LocalDate.now();
    }

    public SaleDAOfilter employeeId(int id) {
        employeesIds.add(id);
        return this;
    }

    public SaleDAOfilter employeeId(List<Integer> listId) {
        employeesIds.addAll(listId);
        return this;
    }

    public SaleDAOfilter tripId(int id) {
        tripsIds.add(id);
        return this;
    }

    public SaleDAOfilter tripId(List<Integer> tripId) {
        tripsIds.addAll(tripId);
        return this;
    }

    public SaleDAOfilter scopeBetweenDates(LocalDate beginDate, LocalDate endDate) {
        this.beginDate = beginDate;
        this.endDate = endDate;
        return this;
    }

    public List<Integer> getEmployeesIds() {
        return employeesIds;
    }

    public List<Integer> getTripsIds() {
        return tripsIds;
    }

    @Nullable
    public LocalDate getBeginDate() {
        return beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

}
