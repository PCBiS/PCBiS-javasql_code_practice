package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.ConnProvider;

public class DeleteDAO {
	ConnProvider prov = null; 
	Connection conn = null;
	PreparedStatement pstmt = null; 
	ResultSet rs = null;

	private static DeleteDAO instance;
	
	private DeleteDAO() {
		// Constructor
		prov = ConnProvider.getInstance();
	}
	
	public static DeleteDAO getInstance() {
		if (instance == null) {
			instance = new DeleteDAO();
		}
		return instance;
	}
	
	public boolean deleteName(String name) throws SQLException {
		boolean isDelete = false;
		String sql = "DELETE FROM contact WHERE name = ?";
		
		conn = prov.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, name);
		int result = pstmt.executeUpdate();
		if (result > 0) {
			isDelete = true;
		}
		return isDelete;
	}
	
	public boolean deletePhoneNum(String phoneNum) throws SQLException {
		boolean isDelete = false;
		String sql = "DELETE FROM contact WHERE phonenum = ?";
		
		conn = prov.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, phoneNum);
		int result = pstmt.executeUpdate();
		if (result > 0) {
			isDelete = true;
		}
		return isDelete;
	}
	
	public boolean deletePIDX(String pidx) throws SQLException {
		boolean isDelete = false;
		String sql = "DELETE FROM contact WHERE pidx = ?";
		
		conn = prov.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, pidx);
		int result = pstmt.executeUpdate();
		if (result > 0) {
			isDelete = true;
		}
		return isDelete;
	}
}
