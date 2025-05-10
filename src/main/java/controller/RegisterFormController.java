package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Student;
import model.StudentData;

import java.time.LocalDate;

public class RegisterFormController {

    @FXML
    private DatePicker dobPicker;

    @FXML
    private ComboBox<String> genderComboBox;

    @FXML
    private PasswordField txt_ConfirmPassword;

    @FXML
    private TextField txt_Email;

    @FXML
    private TextField txt_Name;

    @FXML
    private PasswordField txt_Password;

    @FXML
    private TextField txt_StudentId;

    @FXML
    void btn_RegisterOnAction(ActionEvent event) throws Exception {

        String id = txt_StudentId.getText().trim();
        String name = txt_Name.getText().trim();
        String email = txt_Email.getText().trim();
        String password = txt_Password.getText();
        String confirmPassword = txt_ConfirmPassword.getText();
        LocalDate dob = dobPicker.getValue();
        String gender = genderComboBox.getValue();

        //--- Validations
        if (id.isEmpty() || name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || dob == null || gender == null) {
            showAlert(Alert.AlertType.ERROR, "All fields are required.");
            return;
        }

        if (!email.matches("^\\S+@\\S+\\.\\S+$")){
            showAlert(Alert.AlertType.ERROR, "Invalid email format.");
            return;
        }

        if (StudentData.isEmailRegistered(email)) {
            showAlert(Alert.AlertType.ERROR, "Email already registered.");
            return;
        }

        if (!password.equals(confirmPassword)){
            showAlert(Alert.AlertType.ERROR, "Passwords do not match.");
            return;
        }

        //--- Create & Register Students & Save to DB
        Student student = new Student(id, name, email, password, dob, gender);
        model.StudentDAO.saveStudent(student);

        showAlert(Alert.AlertType.INFORMATION, "Registration successful!");
        clearForm();

        // Navigate to Login
        Parent root = FXMLLoader.load(getClass().getResource("/view/loginForm.fxml"));
        Stage stage = (Stage) txt_StudentId.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Student Login");
    }

    @FXML
    public void initialize(){
        //--- Gender ComboBox Values
        genderComboBox.getItems().addAll("Male", "Female", "Other");

        //--- Move Cursor to next Text Fields when Enter is Pressed
        txt_StudentId.setOnAction(e -> txt_Name.requestFocus());
        txt_Name.setOnAction(e -> txt_Email.requestFocus());
        txt_Email.setOnAction(e -> txt_Password.requestFocus());
        txt_Password.setOnAction(e -> txt_ConfirmPassword.requestFocus());
        txt_ConfirmPassword.setOnAction(e -> dobPicker.requestFocus());
        dobPicker.setOnAction(e -> genderComboBox.requestFocus());
    }

    private void showAlert(Alert.AlertType type, String message){
        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearForm(){
        txt_StudentId.clear();
        txt_Name.clear();
        txt_Email.clear();
        txt_Password.clear();
        txt_ConfirmPassword.clear();
        dobPicker.setValue(null);
        genderComboBox.setValue(null);
    }
}
