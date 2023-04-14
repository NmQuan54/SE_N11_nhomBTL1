package com.Helper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.Models.Benhnhan;
import com.Models.Bacsi;
import com.utils.ExecuteQuery;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataManager {
    private static ObservableList<Benhnhan> BenhnhanList = FXCollections.observableArrayList();
    private static ObservableList<Bacsi> BacsiList = FXCollections.observableArrayList();
    
}
