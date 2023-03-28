package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import Connection_to_SQL.JDBCconnection; 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class LoginApp extends Application {
    private static Connection conn;
    private static PreparedStatement registerStatement;
    private static PreparedStatement loginStatement;
    private static TextField usernameField;
    private static PasswordField passwordField;

    public static void main(String[] args) {
        
    	try {
            

            // Chạy ứng dụng
            launch(args);

            // Đóng kết nối khi ứng dụng kết thúc
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    	conn = JDBCconnection.getJDBCconnnection();
    	registerStatement = conn.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
        loginStatement = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
        // Tạo các thành phần giao diện
        Label titleLabel = new Label("Đăng nhập");
        Label usernameLabel = new Label("Tên đăng nhập");
        Label passwordLabel = new Label("Mật khẩu");
        usernameField = new TextField();
        passwordField = new PasswordField();
        Button loginButton = new Button("Đăng nhập");
        Button registerButton = new Button("Đăng ký");
        Label registerLabel = new Label("Bạn chưa có tài khoản?");

        // Sự kiện khi nhấn nút đăng nhập
        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            try {
                loginStatement.setString(1, username);
                loginStatement.setString(2, password);
                ResultSet resultSet = loginStatement.executeQuery();
                if (resultSet.next()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Đăng nhập thành công");
                    alert.setHeaderText(null);
                    alert.setContentText("Xin chào " + resultSet.getString("username"));
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Đăng nhập thất bại");
                    alert.setHeaderText(null);
                    alert.setContentText("Tên đăng nhập hoặc mật khẩu không chính xác");
                    alert.showAndWait();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        	
        registerButton.setOnAction(event -> {
            Stage registerStage = new Stage();
            Label registerTitleLabel = new Label("Đăng ký");
            Label registerUsernameLabel = new Label("Tên đăng nhập");
            Label registerPasswordLabel = new Label("Mật khẩu");
            Label registerConfirmPasswordLabel = new Label("Xác nhận mật khẩu");
            TextField registerUsernameField = new TextField();
            PasswordField registerPasswordField = new PasswordField();
            PasswordField registerConfirmPasswordField = new PasswordField();
            Button registerSubmitButton = new Button("Đăng ký");
            registerSubmitButton.setOnAction(event2 -> {
                String username = registerUsernameField.getText();
                String password = registerPasswordField.getText();
                String confirmPassword = registerConfirmPasswordField.getText();
                try {
                    if (!password.equals(confirmPassword)) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Lỗi đăng ký");
                        alert.setHeaderText(null);
                        alert.setContentText("Mật khẩu xác nhận không đúng");
                        alert.showAndWait();
                        return;
                    }
                    // Thực hiện truy vấn để kiểm tra xem tên đăng nhập đã được sử dụng hay chưa
                    PreparedStatement checkUsernameStatement = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
                    checkUsernameStatement.setString(1, username);
                    ResultSet resultSet = checkUsernameStatement.executeQuery();
                    if (resultSet.next()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Lỗi đăng ký");
                        alert.setHeaderText(null);
                        alert.setContentText("Tên đăng nhập đã được sử dụng");
                        alert.showAndWait();
                        return;
                    }
                    // Thực hiện truy vấn để thêm người dùng mới vào cơ sở dữ liệu
                    PreparedStatement registerStatement = conn.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
                    registerStatement.setString(1, username);
                    registerStatement.setString(2, password);
                    registerStatement.executeUpdate();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Đăng ký thành công");
                    alert.setHeaderText(null);
                    alert.setContentText("Bạn đã đăng ký thành công");
                    alert.showAndWait();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            VBox registerLayout = new VBox(10);
            registerLayout.getChildren().addAll(registerTitleLabel, registerUsernameLabel, registerUsernameField, registerPasswordLabel, registerPasswordField, registerConfirmPasswordLabel, registerConfirmPasswordField, registerSubmitButton);
            Scene registerScene = new Scene(registerLayout, 300, 250);
            registerStage.setScene(registerScene);
            registerStage.show();
        });

     // Tạo một giao diện mới sau khi đăng nhập thành công
        Stage mainStage = new Stage();
        Stage loginStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/application/sample.fxml"));
        Parent root1 = fxmlLoader.load();
        
        Scene scene = new Scene(root1);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();



        Button logoutButton = new Button("Đăng xuất");
        logoutButton.setOnAction(event -> {
            // Phương thức xử lý đăng xuất
            // Đóng cửa sổ và trở về cửa sổ đăng nhập
            mainStage.close();
            loginStage.show();
        });

        


        // Tạo giao diện
        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(titleLabel, usernameLabel, usernameField, passwordLabel, passwordField, loginButton, registerLabel, registerButton);

        Scene scene1 = new Scene(root, 300, 250);

        primaryStage.setTitle("Đăng nhập");
        primaryStage.setScene(scene1);
        primaryStage.show();

    }
}
