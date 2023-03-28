package Connection_to_SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCconnection {
	
	public static Connection getJDBCconnnection() {
	
		final String url = "jdbc:mysql://localhost:3306/login_app";
		final String user = "root";
		final String password = "quan05042003";
		
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection(url, user, password);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public static void main(String[] args) {
		Connection connection = getJDBCconnnection();
		if(connection == null) {
			System.out.println("That bai");
			return;
		}
		
		String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, "user123");
			statement.setString(2, "pass123");
			
			
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
			    System.out.println("Them tai khoan thanh cong");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
