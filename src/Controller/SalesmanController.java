package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class SalesmanController implements Initializable {

    @FXML
    protected TableView salesTableView;
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
    protected TextArea tripDescriptonTextArea;
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

    protected ViewSalesController viewSalesController;
    protected DataSaleController dataSaleController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
