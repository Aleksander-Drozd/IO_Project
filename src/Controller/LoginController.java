package Controller;

import Model.LoginModel;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField loginTextField;
    @FXML
    private PasswordField passwordField;

    private LoginModel loginModel;

    @FXML
    private void handleButtonLogin() {
        System.out.println("Zalogowano!");
    }

    @FXML
    private void handleButtonCancel() {
        System.out.println("Zakonczono!");
    }


}
