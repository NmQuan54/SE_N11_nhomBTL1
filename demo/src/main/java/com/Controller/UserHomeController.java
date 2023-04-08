package com.Controller;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
public class UserHomeController {
    @FXML
    private ListView<String> list ;
    @FXML
    AnchorPane pane;
    String[] tab  = {"Thong tin ca nhan","phong kham"};
    @FXML
    public void initialize(){
        list.getItems().addAll(tab); 
    }

}
