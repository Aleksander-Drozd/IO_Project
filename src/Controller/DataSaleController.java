package Controller;

import DAO.TripDAO;
import Model.TripModel;
import POJO.Customer;
import POJO.Sale;
import POJO.Trip;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.time.ZoneId;
import java.util.ArrayList;
import java.sql.Date;
import java.util.ResourceBundle;

public class DataSaleController implements Initializable{

    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private RadioButton maleRadioButton;
    @FXML
    private RadioButton femaleRadioButton;
    @FXML
    private DatePicker saleDatePicker;
    @FXML
    private TextField postCodeTextField;
    @FXML
    private TextField cityTextField;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private TextField streetTextField;
    @FXML
    private ComboBox<Trip> tripComboBox;
    @FXML
    private TextField quantityTextField;

    private ToggleGroup genderToggleGroup;
    private ArrayList<TextField> textFieldArrayList;

    private Sale sale;

    public DataSaleController(){
        genderToggleGroup = new ToggleGroup();
        textFieldArrayList = new ArrayList<>();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        femaleRadioButton.setToggleGroup(genderToggleGroup);
        maleRadioButton.setToggleGroup(genderToggleGroup);

        tripComboBox.getItems().addAll(TripModel.getTrips());

        textFieldArrayList.add(firstNameTextField);
        textFieldArrayList.add(lastNameTextField);
        textFieldArrayList.add(postCodeTextField);
        textFieldArrayList.add(cityTextField);
        textFieldArrayList.add(phoneNumberTextField);
        textFieldArrayList.add(streetTextField);
        textFieldArrayList.add(quantityTextField);
    }

    @FXML
    private void handleAddButton(){
        Sale sale = new Sale();
        Customer customer = new Customer();
        boolean correctData = true;

        customer.setFirstName(firstNameTextField.getText());
        customer.setLastName(lastNameTextField.getText());
        customer.setPostCode(postCodeTextField.getText());
        customer.setCity(cityTextField.getText());
        customer.setStreet(streetTextField.getText());
        customer.setPostCode(postCodeTextField.getText());
        customer.setPhoneNumber(phoneNumberTextField.getText());

        if(maleRadioButton.isSelected())
            customer.setGender("male");
        else
            customer.setGender("female");

        try{
            sale.setQuantity(Integer.parseInt(quantityTextField.getText()));
            quantityTextField.getStyleClass().remove("textfield-error");
        }catch(NumberFormatException e){
            quantityTextField.getStyleClass().add("textfield-error");
            correctData = false;
        }

        for(TextField textField:textFieldArrayList) {
            textField.getStyleClass().remove("textfield-error");
            if(textField.getText().equals("")){
                textField.getStyleClass().add("textfield-error");
                correctData = false;
            }
        }

        try{
            sale.setSaleDate(Date.valueOf(saleDatePicker.getValue()));
            saleDatePicker.getStyleClass().remove("date-picker-error");
        }catch (RuntimeException e){
            saleDatePicker.getStyleClass().add("date-picker-error");
        }

        if (tripComboBox.getValue() == null){
            correctData = false;
            tripComboBox.getStyleClass().add("textfield-error");
        }
        else{
            tripComboBox.getStyleClass().remove("textfield-error");
            sale.setTrip(tripComboBox.getValue());
        }

        sale.setCustomer(customer);

        //sale object ready to return or pass
        if(correctData){
            ((Stage)firstNameTextField.getScene().getWindow()).close();
        }
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Sale getSale() {
        return sale;
    }

    @FXML
    private void handleCancelButton(){
        ((Stage)firstNameTextField.getScene().getWindow()).close();
    }

}
