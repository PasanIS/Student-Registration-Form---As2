package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Student;

public class DashboardFormController {

    @FXML
    private Label lbl_DOB;

    @FXML
    private Label lbl_Email;

    @FXML
    private Label lbl_FullName;

    @FXML
    private Label lbl_Gender;

    @FXML
    private Label lbl_StudentId;

    @FXML
    void btn_LogoutOnAction(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/loginForm.fxml"));
        AnchorPane loginPane = loader.load();

        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(loginPane));
    }


    @FXML
    public void setStudent(Student student){

        if (student != null){
            lbl_StudentId.setText(student.getStudentId());
            lbl_FullName.setText(student.getFullName());
            lbl_Email.setText(student.getEmail());
            lbl_Gender.setText(student.getGender());
            lbl_DOB.setText(student.getDob().toString());
        }
    }
}
