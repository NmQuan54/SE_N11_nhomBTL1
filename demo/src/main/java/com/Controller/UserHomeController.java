package com.Controller;
import java.io.IOException;

import com.App;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

public class UserHomeController {
    @FXML 
    Button btLichKham;
    @FXML 
    Button btPhongKham;
    @FXML 
    Button btTimKiem;
    @FXML 
    Button btThongTin;
    @FXML 
    Button btLogout;

    public void onClickLogout(ActionEvent actionEvent) throws SQLException, IOException {
        App.setRoot("loginFrm");
    }
    public void onClickThongTin(ActionEvent actionEvent) throws SQLException, IOException{
        App.setRoot("infoUserFrm");
    }
}
