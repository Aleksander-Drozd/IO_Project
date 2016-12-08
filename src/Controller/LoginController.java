package Controller;

import Model.LoginModel;

import POJO.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField loginTextField;
    @FXML
    private PasswordField passwordField;

    private LoginModel loginModel;
    private Employee employee;

    public LoginController() {
        loginModel = new LoginModel();
    }

    @FXML
    private void handleButtonLogin() {
        employee = loginModel.getEmployee(loginTextField.getText(), passwordField.getText());
        //TODO information about login
    }

    @FXML
    private void handleButtonCancel() {
        //TODO close stage
    }


}
