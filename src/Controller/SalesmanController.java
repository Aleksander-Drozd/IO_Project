package Controller;

import Model.SaleModel;
import POJO.Sale;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class SalesmanController implements Initializable {

    @FXML
    protected TableView<Sale> salesTableView;
    @FXML
    protected TableColumn<Sale, String> lastNameTableColumn;
    @FXML
    protected TableColumn<Sale, String> tripTitleTableColumn;
    @FXML
    protected TableColumn<Sale, String> dateTableColumn;
    @FXML
    protected Label salesmanLabel;
    @FXML
    protected Label firstNameLabel;
    @FXML
    protected Label lastNameLabel;
    @FXML
    protected Label cityLabel;
    @FXML
    protected Label streetLabel;
    @FXML
    protected Label postCodeLabel;
    @FXML
    protected Label phoneNumberLabel;
    @FXML
    protected Label tripTitleLabel;
    @FXML
    protected TextArea tripDescriptionTextArea;
    @FXML
    protected Label tripDateLabel;
    @FXML
    protected Label tripPrizeLabel;
    @FXML
    protected Label tripDaysLabel;
    @FXML
    protected Label saleDateLabel;
    @FXML
    protected Label saleCostLabel;

    protected SaleModel saleModel;

    public SalesmanController() {
        saleModel = new SaleModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Sale> salesObservableList = saleModel.getSalesObservableList();

        salesTableView.setItems(salesObservableList);

        lastNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tripTitleTableColumn.setCellValueFactory(new PropertyValueFactory<>("tripTitle"));
        dateTableColumn.setCellValueFactory(new PropertyValueFactory<>("saleDate"));

    }

    @FXML
    private void handleButtonEditSale() {
        System.out.println("Edytuj sprzedaz");
    }

    @FXML
    private void handleButtonAddSale() {
        System.out.println("Dodaj nowa sprzedaz");
    }
}
