package com.Controller;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.App;
import com.Models.AccountBenhnhan;
import com.Models.ExecuteQuery;
import javafx.event.ActionEvent;
import com.Helper.AlertHelper;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

public class SignupController {
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private PasswordField txtRePassword;
    @FXML
    private ComboBox<String> cbRole; 
    @FXML
    Button btBack;
    @FXML
    Button btnSignup;
    private String selectedRole = "User";
    @FXML
    public void initialize() { // xu ly combobox
        setCbRole();
    }

    private void setCbRole() {
        cbRole.getItems().addAll("User", "Psychologists");
        cbRole.getSelectionModel().selectFirst(); // set gia tri mac dinh cho combobox
        cbRole.setOnAction(e -> {
            selectedRole = cbRole.getSelectionModel().getSelectedItem().toString();
            System.out.println(selectedRole);
        });
    }

    @FXML
    public void onPressSignup(ActionEvent actionEventction) throws SQLException, IOException {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String rePassword = txtRePassword.getText();
        String role = selectedRole;
        if(!rePassword.equals(password)) {
            System.out.println("ngu");
            return ;
        }
        if(role.equals("User")){
            ExecuteQuery query = new ExecuteQuery(
                "INSERT INTO account_benhnhan VALUES ('" + username + "', '" + password + "')" );  
            query.executeUpdate();
            AlertHelper.showAlert(AlertType.INFORMATION, "Login successful", null, "You have successfully logged with Sser role.");
            App.setRoot("loginFrm");
        }else if(role.equals("Psychologists")){
            ExecuteQuery query = new ExecuteQuery(
                "INSERT INTO account_bacsi VALUES ('" + username + "', '" + password + "')");  
            query.executeUpdate();   
            AlertHelper.showAlert(AlertType.INFORMATION, "Login successful", null, "You have successfully logged with Psychologist role.");
            App.setRoot("loginFrm");
        }
    }
    @FXML
    public void onClickBack(ActionEvent actionEventction) throws SQLException, IOException {
        App.setRoot("loginFrm");
    }
}

