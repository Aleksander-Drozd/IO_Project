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

    public LoginController() {
        loginModel = new LoginModel();
    }

    @FXML
    private void handleButtonLogin() {
        Employee employee = loginModel.getEmployee(loginTextField.getText(), passwordField.getText());
        System.out.println("Zalogowano!");
    }

    @FXML
    private void handleButtonCancel() {
        System.out.println("Zakonczono!");
    }


}
