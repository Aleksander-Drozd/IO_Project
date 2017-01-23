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
        if (salesObservableList.size() == 0) {
            salesObservableList = SaleDAO.getSales();
        }

        return salesObservableList;
    }

    public void addSale(Sale sale){
        if (SaleDAO.addSale(sale)) {
            salesObservableList.add(sale);
        } else {
            //TODO Show error status
        }
    }

    public void updateSale(Sale sale) {
        if(SaleDAO.updateSale(sale)) {
            salesObservableList.removeIf((gettedSale) -> gettedSale.getSaleId() == sale.getSaleId());
            salesObservableList.add(sale);
        } else {
            //TODO Show error status
        }
    }
}
