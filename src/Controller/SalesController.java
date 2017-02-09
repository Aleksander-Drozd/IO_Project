package Controller;

import DAO.EmployeeDAO;
import Model.SaleModel;
import POJO.Employee;
import POJO.Sale;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SalesController implements Initializable {

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

    protected Sale chosenSale = null;

    protected EmployeeDAO employeeDAO;

    public SalesController() {
        saleModel = new SaleModel();
        employeeDAO = new EmployeeDAO();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Sale> salesObservableList = saleModel.getSalesObservableList();

        Employee loggedEmployee = employeeDAO.getLoggedEmployee();
        salesmanLabel.setText(loggedEmployee.getLastName() + " " + loggedEmployee.getFirstName());

        salesTableView.setItems(salesObservableList);

        lastNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tripTitleTableColumn.setCellValueFactory(new PropertyValueFactory<>("tripTitle"));
        dateTableColumn.setCellValueFactory(new PropertyValueFactory<>("saleDate"));

        salesTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    chosenSale = newValue;
                    showSaleInfo(newValue);
                }
        );

        if (salesObservableList.size() > 0) {
            showSaleInfo(salesObservableList.get(0));
        }
    }

    private void showSaleInfo(Sale sale) {
        // TODO refactor and fix else
        if (sale != null) {
            firstNameLabel.setText(sale.getCustomer().getFirstName());
            lastNameLabel.setText(sale.getCustomer().getLastName());
            cityLabel.setText(sale.getCustomer().getCity());
            streetLabel.setText(sale.getCustomer().getStreet());
            postCodeLabel.setText(sale.getCustomer().getPostCode());
            phoneNumberLabel.setText(sale.getCustomer().getPhoneNumber());
            tripTitleLabel.setText(sale.getTrip().getTitle());
            tripDescriptionTextArea.setText(sale.getTrip().getDescription());
            tripDateLabel.setText(sale.getTrip().getDate().toString()); // <--- NullPointerException on newly added sale
            tripPrizeLabel.setText(sale.getTrip().getPrice() + " zl");
            tripDaysLabel.setText(sale.getTrip().getDays() + "");
            saleDateLabel.setText(sale.getSaleDate());
            saleCostLabel.setText((sale.getQuantity() * sale.getTrip().getPrice())+ " zl");
        } else {
            System.out.print("Sale is null");
        }
    }

    private Sale showSaleView(Sale sale){
        Stage dataSaleStage = new Stage();

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../View/SaleView.fxml"));

            BorderPane layout = (BorderPane) loader.load();

            Scene scene = new Scene(layout);
            SaleController saleController = loader.getController();

            dataSaleStage.setScene(scene);
            dataSaleStage.initModality(Modality.APPLICATION_MODAL);
            dataSaleStage.initOwner(addSaleButton.getScene().getWindow());
            dataSaleStage.setTitle("Sprzedaz");

            saleController.setSale(sale);

            dataSaleStage.showAndWait();

            return saleController.getSale();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @FXML
    private void handleButtonEditSale() {
        Sale editedSale = showSaleView(chosenSale);

        if (editedSale != null) {
            saleModel.updateSale(editedSale);
        }
    }

    @FXML
    private void handleButtonAddSale() {
        Sale newSale = showSaleView(null);

        if (newSale != null)
            saleModel.addSale(newSale);
    }

}
