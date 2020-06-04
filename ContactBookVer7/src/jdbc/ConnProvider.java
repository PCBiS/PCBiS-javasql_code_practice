package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnProvider implements OracleDriver{
	Connection conn;
	private static ConnProvider instance;
	private ConnProvider() {
		conn = null;
	}
	
	public static ConnProvider getInstance() {
		if (instance == null) {
			instance = new ConnProvider();
		}
		return instance;
	}
	
	public Connection getConnection() {
		try {
			conn = DriverManager.getConnection(URL, ID, PW);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
