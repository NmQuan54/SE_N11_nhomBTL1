package com.Controller;
import java.io.IOException;

import com.App;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

public class UserInfoController {
    @FXML
    TextField txtTen;
    @FXML
    TextField txtTuoi;
    @FXML
    TextField txtSdt;
    @FXML
    TextField txtDiaChi;
    @FXML
    Button btLuu;
    @FXML
    RadioButton rbtNam;
    @FXML
    RadioButton rbtNu;
    RadioButton radioButton1 = new RadioButton("Nam");
    RadioButton radioButton2 = new RadioButton("Nu");
    
    // String ten = txtTen.getText();
    // String tuoi = txtTuoi.getText();
    // String diachi = txtDiaChi.getText();
    // String sdt = txtSdt.getText();
    // public void onClickSave(ActionEvent actionEvent){
    //     return;
    // }
}
