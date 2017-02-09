package Model;

import Util.DatabaseUtil;
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
            salesObservableList = saleDAO.getSales();
        }

        return salesObservableList;
    }

    public void addSale(Sale sale){
        int saleId;

        saleId = saleDAO.addSale(sale);

        if (saleId != DatabaseUtil.ERROR) {
            sale.setSaleId(saleId);
            salesObservableList.add(sale);
        } else {
            //TODO Show error status
        }
    }

    public void updateSale(Sale sale) {
        if(saleDAO.updateSale(sale)) {
            //TODO Possible auto update SaleList
            salesObservableList.removeIf((gotSale) -> gotSale.getSaleId() == sale.getSaleId());
            salesObservableList.add(sale);
        } else {
            //TODO Show error status
        }
    }
}
