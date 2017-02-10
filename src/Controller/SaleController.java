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
    private Customer customer;
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

    @FXML
    private void handleAddButton(){
        if (!validateData())
            return;

        setCustomer();
        setSale();

        ((Stage)firstNameTextField.getScene().getWindow()).close();
    }

    private void setCustomer(){
        if(sale == null)
            customer = new Customer();

        customer.setFirstName(firstNameTextField.getText());
        customer.setLastName(lastNameTextField.getText());
        customer.setPostCode(postCodeTextField.getText());
        customer.setCity(cityTextField.getText());
        customer.setStreet(streetTextField.getText());
        customer.setPhoneNumber(phoneNumberTextField.getText());

        if(maleRadioButton.isSelected()) {
            customer.setGender("Male");
        } else {
            customer.setGender("Female");
        }
    }

    private void setSale(){
        if (sale == null)
            sale = new Sale();

        sale.setQuantity(Integer.parseInt(quantityTextField.getText()));
        sale.setTrip(tripComboBox.getValue());
        sale.setEmployee(EmployeeDAO.getLoggedEmployee());
        sale.setLastName(customer.getLastName());
        sale.setTripTitle(tripComboBox.getValue().getTitle());
        sale.setSaleDate(Date.valueOf(saleDatePicker.getValue()));
        sale.setCustomer(customer);
    }

    //ToDO Rename this method. Except validating, it also sets error styles
    private boolean validateData(){
        boolean correctData = true;

        for(TextField textField:textFieldArrayList) {
            textField.getStyleClass().remove("textfield-error");
            if(textField.getText().equals("")){
                textField.getStyleClass().add("textfield-error");
                correctData = false;
            }
        }

        if (!validateNumberTextField(quantityTextField))
            correctData = false;

        if (!validateNumberTextField(phoneNumberTextField))
            correctData = false;

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

    private boolean isNumber(String text){
        try{
            Integer.parseInt(text);
            return  true;
        } catch (NumberFormatException e){
            return false;
        }
    }

    @FXML
    private void handleCancelButton(){
        sale = null;
        ((Stage)firstNameTextField.getScene().getWindow()).close();
    }

    private boolean validateNumberTextField(TextField textField){
        if (isNumber(textField.getText())){
            textField.getStyleClass().remove("textfield-error");
            return true;
        } else {
            textField.getStyleClass().add("textfield-error");
            return false;
        }
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
            case "Female":
                femaleRadioButton.setSelected(true);
                break;
            case "Male":
                maleRadioButton.setSelected(true);
                break;
            default:
                maleRadioButton.setSelected(false);
                femaleRadioButton.setSelected(false);
                break;
        }

        saleDatePicker.setValue(LocalDate.parse(sale.getSaleDate()));

        quantityTextField.setText(sale.getQuantity() + "");
    }

    public void setSale(Sale sale) {
        this.sale = sale;

        if (sale != null) {
            setSaleView(sale);
            customer = sale.getCustomer();
        }
    }

    public Sale getSale() {
        return sale;
    }

}
