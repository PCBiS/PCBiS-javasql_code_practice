package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.AllContactInfo;
import dto.Contact;
import dto.ContactCLUB;
import dto.ContactCOM;
import dto.ContactUNIV;
import jdbc.ConnProvider;
import oracle.net.aso.a;
import oracle.net.aso.u;

public class ViewDAO {
	ConnProvider prov = null;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String sql = null;
	
	private static ViewDAO instance;
	
	private ViewDAO() {
		// Constructor
		prov = ConnProvider.getInstance();
	}
	public static ViewDAO getInstance() {
		if (instance == null) {
			instance = new ViewDAO();
		}
		return instance;
	}
	
	public List<AllContactInfo> contactView() throws SQLException {
		List<AllContactInfo> allList = new ArrayList<AllContactInfo>();
		sql = "SELECT * FROM contactAllView";
		
		conn = prov.getConnection();
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		while (rs.next()) {
			allList.add(new AllContactInfo(
					rs.getInt("pidx"),
					rs.getString("name"),
					rs.getString("phonenum"),
					rs.getString("address"),
					rs.getString("email"),
					rs.getString("friendtype"),
					rs.getString("clubname"),
					rs.getString("nickname"),					
					rs.getString("company"),
					rs.getString("job"),
					rs.getString("major"),
					rs.getInt("grade")
					));
		}
		return allList;
	}
	
		
	
	public List<AllContactInfo> contactView(String name) throws SQLException {
		List<AllContactInfo> allList = new ArrayList<AllContactInfo>();
		
		sql = "SELECT * FROM contactAllView WHERE name LIKE '%'||?||'%'";
		conn = prov.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, name);
		rs = pstmt.executeQuery();
		
		while (rs.next()) {
			allList.add(new AllContactInfo(
					rs.getInt("pidx"),
					rs.getString("name"),
					rs.getString("phonenum"),
					rs.getString("address"),
					rs.getString("email"),
					rs.getString("friendtype"),
					rs.getString("clubname"),
					rs.getString("nickname"),					
					rs.getString("company"),
					rs.getString("job"),
					rs.getString("major"),
					rs.getInt("grade")
					));
		}
		return allList;
	}
	
	
	public List<AllContactInfo> contactViewByPhoneNumber(String phoneNum) throws SQLException {
		List<AllContactInfo> allList = new ArrayList<AllContactInfo>();
		
		sql = "SELECT * FROM contactAllView WHERE phonenum LIKE '%'||?||'%'";
		conn = prov.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, phoneNum);
		rs = pstmt.executeQuery();
		
		while (rs.next()) {
			allList.add(new AllContactInfo(
					rs.getInt("pidx"),
					rs.getString("name"),
					rs.getString("phonenum"),
					rs.getString("address"),
					rs.getString("email"),
					rs.getString("friendtype"),
					rs.getString("clubname"),
					rs.getString("nickname"),					
					rs.getString("company"),
					rs.getString("job"),
					rs.getString("major"),
					rs.getInt("grade")
					));
		}
		return allList;
	}
	
	
	public List<AllContactInfo> contactViewByPIDX(String pidx) throws SQLException {
		List<AllContactInfo> allList = new ArrayList<AllContactInfo>();
		
		sql = "SELECT * FROM contactAllView WHERE pidx LIKE '%'||?||'%'";
		conn = prov.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, pidx);
		rs = pstmt.executeQuery();
		
		while (rs.next()) {
			allList.add(new AllContactInfo(
					rs.getInt("pidx"),
					rs.getString("name"),
					rs.getString("phonenum"),
					rs.getString("address"),
					rs.getString("email"),
					rs.getString("friendtype"),
					rs.getString("clubname"),
					rs.getString("nickname"),					
					rs.getString("company"),
					rs.getString("job"),
					rs.getString("major"),
					rs.getInt("grade")
					));
		}
		return allList;
	}

}
