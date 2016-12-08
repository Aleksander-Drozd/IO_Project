package Controller;

import POJO.Customer;
import POJO.Sale;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DataSaleController {

    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private RadioButton maleRadioButton;
    @FXML
    private RadioButton femaleRadioButton;
    @FXML
    private DatePicker birthDatePicker;
    @FXML
    private TextField postCodeTextField;
    @FXML
    private TextField cityTextField;
    @FXML
    private TextField streetTextField;
    @FXML
    private ComboBox tripComboBox;
    @FXML
    private TextField quantityTextField;

    //FXML.loader() uses this constructor
    public DataSaleController(){

    }

    @FXML
    private void handleAddButton(){
        Sale sale = new Sale();
        Customer customer = new Customer();

        customer.setFirstName(firstNameTextField.getText());
        customer.setLastName(lastNameTextField.getText());
        customer.setPostCode(postCodeTextField.getText());
        customer.setCity(cityTextField.getText());
        customer.setStreet(streetTextField.getText());

        if(maleRadioButton.isSelected())
            customer.setSex("male");
        else
            customer.setSex("female");

        sale.setQuantity(Integer.parseInt(quantityTextField.getText()));

        //Todo check if data is correct
        ((Stage)firstNameTextField.getScene().getWindow()).close();
    }

    @FXML
    private void handleCancelButton(){
        Stage stage = (Stage)firstNameTextField.getScene().getWindow();
        stage.close();
    }
}
