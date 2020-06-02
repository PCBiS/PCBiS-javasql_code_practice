package deptManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbConn.OracleDriver;

public class DeptManager implements OracleDriver{
	private static DeptManager instance;
	private DeptManager() {
		//초기화 생성
	}
	public static DeptManager getInstance() {
		if (instance == null) {
			instance = new DeptManager();
		}
		return instance;
	}
	
	public void deptMemberList() {
		String sql = "SELECT * FROM dept";
		
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, ID, PW);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs != null) {
				System.out.println("부서 정보");
				System.out.println("----------------------");
				System.out.println("DEPTNO | DNAME | LOC");
				System.out.println("----------------------");
				while (rs.next()) {
					System.out.println(
							rs.getInt("DEPTNO") + " | " + 
							rs.getString("DNAME") + " | " + 
							rs.getString("LOC"));
				}
				System.out.println("----------------------");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void deptMemberList(String deptno) {
		String sql = "SELECT * FROM dept WHERE DEPTNO = '" + deptno + "'";
		
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, ID, PW);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs != null) {
				System.out.println("부서 정보");
				System.out.println("----------------------");
				System.out.println("DEPTNO | DNAME | LOC");
				System.out.println("----------------------");
				while (rs.next()) {
					System.out.println(
							rs.getInt("DEPTNO") + " | " + 
							rs.getString("DNAME") + " | " + 
							rs.getString("LOC"));
				}
				
				System.out.println("----------------------");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	

}
