package Controller;

import Model.LoginModel;

import POJO.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField loginTextField;
    @FXML
    private PasswordField passwordField;

    private LoginModel loginModel;
    private Employee employee;

    private Stage stage;

    public LoginController() {
        loginModel = new LoginModel();
    }

    public Employee getEmployee() {
        return employee;
    }

    @FXML
    private void handleButtonLogin() {
        employee = loginModel.getEmployee(loginTextField.getText(), passwordField.getText());
        stage.close();
        //TODO information about login
    }

    @FXML
    private void handleButtonCancel() {
        stage.close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
