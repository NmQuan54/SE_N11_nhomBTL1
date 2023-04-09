package com.Controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.App;
import com.Helper.AlertHelper;

import com.Models.ExecuteQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class PsyHomeController {
    @FXML
    Button btThongTin;
    @FXML
    Button btPhongKham;
    @FXML
    Button btLichKham;
    @FXML
    Button btYeuCau;
    @FXML
    Button btLogout;
    
    public void onClickLogout(ActionEvent actionEvent) throws SQLException, IOException  {
        App.setRoot("loginFrm");
    }
}
