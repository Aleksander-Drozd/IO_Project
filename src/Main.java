import POJO.Employee;
import Util.DatabaseUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import Controller.LoginController;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Employee loggedEmployee = initLoginView();

        if (loggedEmployee != null) {
            System.out.println("Zalogowano pomyslnie!");
        } else {
            System.out.println("Blad logowania!");
        }

    }

    private Employee initLoginView() {
        try {
            Stage loginStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("View/LoginView.fxml"));

            BorderPane layout = (BorderPane) loader.load();

            Scene scene = new Scene(layout);
            LoginController loginController = loader.getController();
            loginController.setStage(loginStage);

            loginStage.setTitle("Logowanie");
            loginStage.setScene(scene);
            loginStage.showAndWait();

            return loginController.getEmployee();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
