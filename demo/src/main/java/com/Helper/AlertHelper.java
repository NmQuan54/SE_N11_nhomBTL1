package com.Helper;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
public abstract class AlertHelper {
    public static void showAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
    public static void showLoginError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Lỗi đăng nhập");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    // public static boolean showConfirmation(String message) {
    //     Alert alert = new Alert(AlertType.CONFIRMATION, message);
    //     alert.setTitle("Xác nhận");
    //     alert.setHeaderText(null);
    //     Optional<ButtonType> result = alert.showAndWait();
    //     return result.isPresent() && result.get() == ButtonType.OK;
    // }
}
