package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Student;
import model.StudentData;

public class LoginFormController {

    @FXML
    private Button btn_Login;

    @FXML
    private PasswordField txt_Password;

    @FXML
    private TextField txt_Username;

    @FXML
    void btn_LoginOnAction() throws Exception {
        String username = txt_Username.getText().trim();
        String password = txt_Password.getText();

        if (username.isEmpty() || password.isEmpty()){
            showAlert(Alert.AlertType.ERROR, "Please Enter Username & Password.");
            return;
        }

        //--- Authenticate
        Student loggedInStudent = StudentData.login(username, password);

        if (loggedInStudent != null) {
            showAlert(Alert.AlertType.INFORMATION, "Login successful!");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dashboardForm.fxml"));
            Parent root = loader.load();

            DashboardFormController dashboardFormController = loader.getController();
            dashboardFormController.setStudent(loggedInStudent);

            Stage stage = (Stage) btn_Login.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Dashboard");
        }else {
            showAlert(Alert.AlertType.ERROR, "Invalid Credentials.");
        }
    }

    @FXML
    void link_RegisterOnAction() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/registerForm.fxml"));
        Stage stage = (Stage) txt_Username.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Student Registration");
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
