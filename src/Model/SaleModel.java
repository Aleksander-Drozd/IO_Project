package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import POJO.Sale;
import DAO.SaleDAO;

public class SaleModel {

    private ObservableList<Sale> salesObservableList;

    private SaleDAO saleDAO;

    public SaleModel() {
        saleDAO = new SaleDAO();
        salesObservableList = FXCollections.observableArrayList();
    }

    public ObservableList<Sale> getSalesObservableList() {
        salesObservableList.add(new Sale(""));
        salesObservableList.add(new Sale(""));
        salesObservableList.add(new Sale(""));

        return salesObservableList;
    }

    public void addSale(Sale sale){
        salesObservableList.add(sale);
    }

}
