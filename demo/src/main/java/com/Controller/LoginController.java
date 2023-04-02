package com.Controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.App;
import com.Models.Account;
import com.Models.ExecuteQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

public class LoginController {

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private ComboBox<String> cbRole;

    @FXML
    Button btnSubmit;

    private String selectedRole = "User";
    private ArrayList<Account> userAccounts = new ArrayList<>();
    private ArrayList<Account> pyschologistsAccounts = new ArrayList<>();

    @FXML
    public void initialize() { // xu ly combobox
        setCbRole();
        initAccount();
    }

    private void setCbRole() {
        cbRole.getItems().addAll("User", "Psychologists");
        cbRole.getSelectionModel().selectFirst(); // set gia tri mac dinh cho combobox
        cbRole.setOnAction(e -> {
            selectedRole = cbRole.getSelectionModel().getSelectedItem().toString();
            System.out.println(selectedRole);
        });
    }

    private void showLoginError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Lỗi đăng nhập");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void initAccount() {
        ExecuteQuery queryPyschologists = new ExecuteQuery("SELECT * FROM account_bacsi"); // lay du lieu account admin tu
                                                                                  // database

        ResultSet resultSet = queryPyschologists.executeQuery();
        try {
            while (resultSet.next()) {
                pyschologistsAccounts.add(new Account(resultSet.getString("username"),
                        resultSet.getString("password")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ExecuteQuery queryUser = new ExecuteQuery("SELECT * FROM account_benhnhan"); // lay du lieu account student tu
                                                                                      // database
        resultSet = queryUser.executeQuery();
        try {
            while (resultSet.next()) {
                userAccounts.add(new Account(resultSet.getString("username"),
                        resultSet.getString("password")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int checkAccount() {
        String inputUsername = txtUsername.getText();
        String inputPassword = txtPassword.getText();
        
        for (Account account : pyschologistsAccounts) {
            if (account.getUsername().equals(inputUsername) && account.getPassword().equals(inputPassword)) {
                return 1;
            }
        }
    
        for (Account account : userAccounts) {
            if (account.getUsername().equals(inputUsername) && account.getPassword().equals(inputPassword)) {
                return 1;
            }
        }
    
        return 0;
    }

    public void btnSubmit(ActionEvent actionEvent) throws IOException {
        if (txtUsername.getText().equals("") || txtPassword.getText().equals("")) {
            showLoginError("Vui lòng nhập đầy đủ thông tin");
            return;
        }

        if (checkAccount() == 1 && selectedRole.equals("Psychologists")) {
            App.setRoot("PsychologistsFrm");
        } else if (checkAccount() == 1 && selectedRole.equals("User")) {
            App.setRoot("userFrm");
        } else {
            showLoginError("Sai tên đăng nhập hoặc mật khẩu");
        }

        // System.out.println("Pass: " + getPassword());
    }

}
