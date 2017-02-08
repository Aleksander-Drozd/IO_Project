package Controller;

import DAO.EmployeeDAO;
import DAO.TripDAO;
import Model.LoginModel;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.sql.Date;
import java.util.ResourceBundle;

public class SaleController implements Initializable{

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
    private TripModel tripModel;

    public SaleController(){
        genderToggleGroup = new ToggleGroup();
        textFieldArrayList = new ArrayList<>();
        tripModel = new TripModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        femaleRadioButton.setToggleGroup(genderToggleGroup);
        maleRadioButton.setToggleGroup(genderToggleGroup);

        tripComboBox.getItems().addAll(tripModel.getTrips());

        textFieldArrayList.add(firstNameTextField);
        textFieldArrayList.add(lastNameTextField);
        textFieldArrayList.add(postCodeTextField);
        textFieldArrayList.add(cityTextField);
        textFieldArrayList.add(phoneNumberTextField);
        textFieldArrayList.add(streetTextField);
        textFieldArrayList.add(quantityTextField);

        //y tho?
        //maleRadioButton.setSelected(false);
        //femaleRadioButton.setSelected(false);

        saleDatePicker.setValue(LocalDate.now());
    }

    // TODO refactor - make function for creating Sale object + function for checking input data
    @FXML
    private void handleAddButton(){
        //TODO make sth better to create or not new instance of Sale class - need to remember sale id
        int saleId = sale != null ? sale.getSaleId() : -1;
        sale = new Sale();
        sale.setSaleId(saleId);

        if (!validateData())
            return;

        Customer customer = new Customer();

        customer.setFirstName(firstNameTextField.getText());
        customer.setLastName(lastNameTextField.getText());
        customer.setPostCode(postCodeTextField.getText());
        customer.setCity(cityTextField.getText());
        customer.setStreet(streetTextField.getText());
        customer.setPhoneNumber(phoneNumberTextField.getText());

        if(maleRadioButton.isSelected()) {
            customer.setGender("male");
        } else {
            customer.setGender("female");
        }

        sale.setQuantity(Integer.parseInt(quantityTextField.getText()));
        sale.setTrip(tripComboBox.getValue());
        sale.setEmployee(EmployeeDAO.getLoggedEmployee());
        sale.setLastName(customer.getLastName());
        sale.setTripTitle(tripComboBox.getValue().getTitle());
        sale.setSaleDate(Date.valueOf(saleDatePicker.getValue()));
        sale.setCustomer(customer);

        ((Stage)firstNameTextField.getScene().getWindow()).close();
    }

    //ToDO Rename this method. Except validating, it also sets error styles
    private boolean validateData(){
        boolean correctData = true;

        try{
            Integer.parseInt(quantityTextField.getText());
            quantityTextField.getStyleClass().remove("textfield-error");
        }catch(NumberFormatException e){
            quantityTextField.getStyleClass().add("textfield-error");
            correctData = false;
        }

        try{
            Integer.parseInt(phoneNumberTextField.getText());
            phoneNumberTextField.getStyleClass().remove("textfield-error");
        }catch(NumberFormatException e){
            phoneNumberTextField.getStyleClass().add("textfield-error");
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
            Date.valueOf(saleDatePicker.getValue());
            saleDatePicker.getStyleClass().remove("date-picker-error");
        }catch (RuntimeException e){
            saleDatePicker.getStyleClass().add("date-picker-error");
        }

        if (tripComboBox.getValue() != null){
            tripComboBox.getStyleClass().remove("textfield-error");
        } else{
            correctData = false;
            tripComboBox.getStyleClass().add("textfield-error");
        }

        if (postCodeTextField.getText().length() > 6)
            correctData = false;

        return correctData;
    }

    @FXML
    private void handleCancelButton(){
        sale = null;
        ((Stage)firstNameTextField.getScene().getWindow()).close();
    }

    private void setSaleView(Sale sale) {
        firstNameTextField.setText(sale.getCustomer().getFirstName());
        lastNameTextField.setText(sale.getCustomer().getLastName());
        postCodeTextField.setText(sale.getCustomer().getPostCode());
        cityTextField.setText(sale.getCustomer().getCity());
        streetTextField.setText(sale.getCustomer().getStreet());
        postCodeTextField.setText(sale.getCustomer().getPostCode());
        phoneNumberTextField.setText(sale.getCustomer().getPhoneNumber());

        // This code set trip even when sale.getTrip() object is not inside tripComboBox (TripModel.getTrips())
        tripComboBox.getSelectionModel().select(sale.getTrip());

        switch (sale.getCustomer().getGender()) {
            case "f":
                femaleRadioButton.setSelected(true);
                break;
            case "m":
                maleRadioButton.setSelected(true);
                break;
            default:
                maleRadioButton.setSelected(false);
                femaleRadioButton.setSelected(false);
                break;
        }

        // TODO make Util Date to set own LocalDate format
        saleDatePicker.setValue(LocalDate.now());

        quantityTextField.setText(sale.getQuantity() + "");
    }

    public void setSale(Sale sale) {
        this.sale = sale;

        if (sale != null) {
            setSaleView(sale);
        }
    }

    public Sale getSale() {
        return sale;
    }

}
