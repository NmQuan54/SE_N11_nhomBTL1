package com.Controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.App;
import com.Helper.AlertHelper;
import com.Models.AccountBacsi;
import com.Models.AccountBenhnhan;
import com.utils.ExecuteQuery;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
// import javafx.scene.control.Alert.AlertType;

public class LoginController {

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private ComboBox<String> cbRole;

    @FXML
    Button btnLogin;
    @FXML
    Button btnSignup;
    
    private String selectedRole = "User";
    private ArrayList<AccountBenhnhan> userAccounts = new ArrayList<>();
    private ArrayList<AccountBacsi> pyschologistsAccounts = new ArrayList<>();

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

    

    private void initAccount() {
        ExecuteQuery queryPyschologists = new ExecuteQuery("SELECT * FROM account_bacsi"); // lay du lieu account admin tu
                                                                                  // database

        ResultSet resultSet = queryPyschologists.executeQuery();
        try {
            while (resultSet.next()) {
                pyschologistsAccounts.add(new AccountBacsi(resultSet.getString("username"),
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
                userAccounts.add(new AccountBenhnhan(resultSet.getString("username"),
                        resultSet.getString("password")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int checkAccount() {
        String inputUsername = txtUsername.getText();
        String inputPassword = txtPassword.getText();
        
        for (AccountBacsi account : pyschologistsAccounts) {
            if (account.getUsername().equals(inputUsername) && account.getPassword().equals(inputPassword)) {
                return 1;
            }
        }
    
        for (AccountBenhnhan account : userAccounts) {
            if (account.getUsername().equals(inputUsername) && account.getPassword().equals(inputPassword)) {
                return 2;
            }
        }
    
        return 0;
    }

    public void onClickLogin(ActionEvent actionEvent) throws IOException {
        if (txtUsername.getText().equals("") || txtPassword.getText().equals("")) {
            AlertHelper.showLoginError("Vui lòng nhập đầy đủ thông tin");
            return;
        }

        if (checkAccount() == 1 && selectedRole.equals("Psychologists")) {
            App.setRoot("PsychologistsFrm");
        } else if (checkAccount() == 2 && selectedRole.equals("User")) {
            App.setRoot("userFrm");
        } else {
            AlertHelper.showLoginError("Sai tên đăng nhập hoặc mật khẩu");
        }

        // System.out.println("Pass: " + getPassword());
    }

    public void onClickSignup(ActionEvent actionEvent) throws IOException {
        App.setRoot("registerFrm");
    }

}
