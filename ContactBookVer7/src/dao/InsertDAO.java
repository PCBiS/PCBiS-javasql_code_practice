package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Contact;
import dto.ContactCLUB;
import dto.ContactCOM;
import dto.ContactUNIV;
import jdbc.ConnProvider;

public class InsertDAO {
	ConnProvider prov = null;
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	String sql = null;
	List<Contact> base = new ArrayList<Contact>();
	List<ContactCLUB> club = new ArrayList<ContactCLUB>();
	List<ContactUNIV> univ = new ArrayList<ContactUNIV>();
	List<ContactCOM> com = new ArrayList<ContactCOM>();
	
	private static InsertDAO instance;
	
	private InsertDAO() {
		prov = ConnProvider.getInstance();
	}
	
	public static InsertDAO getInstance() {
		if (instance == null) {
			instance = new InsertDAO();
		}
		return instance;
	}
	
	public int ContactBaseInfo(Contact base) throws SQLException {
		int result = 0;
		String seqSQL = "contact_pidx_seq.NEXTVAL";
		
		if (base.getAddress().isEmpty() && base.getEmail().isEmpty()) {
			// 값을 비워도 되는 것 중에서 둘 다 비었을 경우에 생성할 쿼리문.
			sql = "INSERT INTO contact(pidx, name, phonenum, friendtype) VALUES (?, ?, ?, ?)";
		}else if (base.getEmail().isEmpty()) {
			// 값을 비워도 되는 것 중에서 메일주소가 비었을 경우에 생성할 쿼리문.
			sql = "INSERT INTO contact(pidx, name, phonenum, address, friendtype) VALUES (?, ?, ?, ?, ?)";
		}else if (base.getAddress().isEmpty()) {
			// 값을 비워도 되는 것 중에서 주소가 비었을 경우에 생성할 쿼리문.
			sql = "INSERT INTO contact(pidx, name, phonenum, email, friendtype) VALUES (?, ?, ?, ?, ?)";
		}else {
			System.err.println("입력 받은 값에 오류가 있습니다.");
			System.err.println(base.toString());
		}
		
		conn = prov.getConnection();
		pstmt = conn.prepareStatement(sql);
		
		if (base.getAddress().isEmpty() && base.getEmail().isEmpty()) {
			pstmt.setString(1, seqSQL);
			pstmt.setString(2, base.getName());
			pstmt.setString(3, base.getPhonenum());
			pstmt.setString(4, base.getFriendtype());
			
		}else if (base.getEmail().isEmpty()) {
			pstmt.setString(1, seqSQL);
			pstmt.setString(2, base.getName());
			pstmt.setString(3, base.getPhonenum());
			pstmt.setString(4, base.getAddress());
			pstmt.setString(5, base.getFriendtype());
		}else if (base.getAddress().isEmpty()) {
			pstmt.setString(1, seqSQL);
			pstmt.setString(2, base.getName());
			pstmt.setString(3, base.getPhonenum());
			pstmt.setString(4, base.getEmail());
			pstmt.setString(5, base.getFriendtype());
		}
		return result = pstmt.executeUpdate();
	}
	
	
	
	public int contactUnivInfo(ContactUNIV base) throws SQLException {
		int result = 0;
		String univSeqSQL = "contact_univ_pidx_seq.NEXTVAL";
		String baseSQL = "contact_pidx_seq.CURRVAL";
		sql = "INSERT INTO contact_univ(pidx, fr_idx, major, grade) VALUES (?, ?, ?, ?)";
		
		conn = prov.getConnection();
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, univSeqSQL);
		pstmt.setString(2, baseSQL);
		pstmt.setString(3, base.getMajor());
		pstmt.setInt(4, base.getGrade());
		
		return result = pstmt.executeUpdate();
	}
	
	
	public int contactComInfo(ContactCOM base) throws SQLException {
		int result = 0;
		String comSeqSQL = "contact_com_pidx_seq.NEXTVAL";
		String baseSQL = "contact_pidx_seq.CURRVAL";
		sql = "INSERT INTO contact_com(pidx, fr_idx, company, job) VALUES (?, ?, ?, ?)";
		
		conn = prov.getConnection();
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, comSeqSQL);
		pstmt.setString(2, baseSQL);
		pstmt.setString(3, base.getCompany());
		pstmt.setString(4, base.getJob());
		
		return result = pstmt.executeUpdate(); 
	}
	
	
	public int contactClubInfo(ContactCLUB base) throws SQLException {
		int result = 0;
		String clubSeqSQL = "contact_club_pidx_seq.NEXTVAL";
		String baseSQL = "contact_pidx_seq.CURRVAL";
		sql = "INSERT INTO contact_club(pidx, fr_idx, clubname, nickname) VALUES (?, ?, ?, ?)";
		
		conn = prov.getConnection();
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, clubSeqSQL);
		pstmt.setString(2, baseSQL);
		pstmt.setString(3, base.getClubname());
		pstmt.setString(4, base.getNickname());
		
		return result = pstmt.executeUpdate(); 
	}
	
}
