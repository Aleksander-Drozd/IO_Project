package Model;

import Util.Dialog;
import Util.ErrorDescriptions;
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
        if (saleDAO.addSale(sale)) {
            salesObservableList.add(sale);
        } else {
            Dialog.displayErrorDialog(ErrorDescriptions.DATABASE_ERROR, null);
        }
    }

    public void updateSale(Sale originalSale, Sale editedSale) {
        if(!saleDAO.updateSale(editedSale)) {
            salesObservableList.remove(editedSale);
            salesObservableList.add(originalSale);
            Dialog.displayErrorDialog(ErrorDescriptions.DATABASE_ERROR, null);
        }
    }
}
