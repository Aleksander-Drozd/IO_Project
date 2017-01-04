package Controller;

import Model.SaleModel;
import POJO.Sale;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
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
    @FXML
    protected Button addSaleButton;

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

        salesTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showSaleDetails(newValue)
        );
    }

    private void showSaleDetails(Sale sale) {

        if (sale != null) {
            firstNameLabel.setText(sale.getCustomer().getFirstName());
            lastNameLabel.setText(sale.getCustomer().getLastName());
            cityLabel.setText(sale.getCustomer().getCity());
            streetLabel.setText(sale.getCustomer().getStreet());
            postCodeLabel.setText(sale.getCustomer().getPostCode());
            phoneNumberLabel.setText(sale.getCustomer().getPhoneNumber());
            tripTitleLabel.setText(sale.getTrip().getTitle());
            tripDescriptionTextArea.setText(sale.getTrip().getDescription());
            tripDateLabel.setText(sale.getTrip().getDate().toString());
            tripPrizeLabel.setText(sale.getTrip().getPrice() + " zl");
            tripDaysLabel.setText(sale.getTrip().getDays() + "");
            saleDateLabel.setText(sale.getSaleDate());
            saleCostLabel.setText((sale.getQuantity() * sale.getTrip().getPrice())+ " zl");
        } else {
            System.out.print("Sale is null");
        }

    }

    @FXML
    private void handleButtonEditSale() {
        System.out.println("Edytuj sprzedaz");
    }

    @FXML
    private void handleButtonAddSale() {
        Stage addSaleStage = new Stage();
        Parent root;

        try {
            root = FXMLLoader.load(getClass().getResource("../View/SaleView.fxml"));

            addSaleStage.setScene(new Scene(root));
            addSaleStage.initModality(Modality.APPLICATION_MODAL);
            addSaleStage.initOwner(addSaleButton.getScene().getWindow());
            addSaleStage.setTitle("Dodaj nowa sprzedaz");
            addSaleStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
