package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.AllContactInfo;
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
	
	
	public int ContactBaseInfo(Contact conBase) throws SQLException {
		int result = 0;
		String seqSQL = "contact_pidx_seq.NEXTVAL";
		
		if (conBase.getAddress().isEmpty() && conBase.getEmail().isEmpty()) {
			// 값을 비워도 되는 것 중에서 둘 다 비었을 경우에 생성할 쿼리문.
			System.out.println("debug! : 둘다빔.");
			sql = "INSERT INTO contact(pidx, name, phonenum, friendtype) VALUES (?, ?, ?, ?)";
		}else if (conBase.getEmail().isEmpty()) {
			// 값을 비워도 되는 것 중에서 메일주소가 비었을 경우에 생성할 쿼리문.
			System.out.println("debug! : 이메일빔.");
			sql = "INSERT INTO contact(pidx, name, phonenum, address, friendtype) VALUES (?, ?, ?, ?, ?)";
		}else if (conBase.getAddress().isEmpty()) {
			// 값을 비워도 되는 것 중에서 주소가 비었을 경우에 생성할 쿼리문.
			System.out.println("debug! : 주소가빔.");
			sql = "INSERT INTO contact(pidx, name, phonenum, email, friendtype) VALUES (?, ?, ?, ?, ?)";
		}else {
			System.err.println("입력 받은 값에 오류가 있습니다.");
			System.err.println(base.toString());
		}
		
		conn = prov.getConnection();
		pstmt = conn.prepareStatement(sql);
		
		if (conBase.getAddress().isEmpty() && conBase.getEmail().isEmpty()) {
			pstmt.setString(1, seqSQL);
			pstmt.setString(2, conBase.getName());
			pstmt.setString(3, conBase.getPhonenum());
			pstmt.setString(4, conBase.getFriendtype());
		}else if (conBase.getEmail().isEmpty()) {
			pstmt.setString(1, seqSQL);
			pstmt.setString(2, conBase.getName());
			pstmt.setString(3, conBase.getPhonenum());
			pstmt.setString(4, conBase.getAddress());
			pstmt.setString(5, conBase.getFriendtype());
		}else if (conBase.getAddress().isEmpty()) {
			pstmt.setString(1, seqSQL);
			pstmt.setString(2, conBase.getName());
			pstmt.setString(3, conBase.getPhonenum());
			pstmt.setString(4, conBase.getEmail());
			pstmt.setString(5, conBase.getFriendtype());
		}
		return result = pstmt.executeUpdate();
	}
	
	
	
	public int contactUnivInfo(ContactUNIV conUniv) throws SQLException {
		int result = 0;
		String univSeqSQL = "contact_univ_pidx_seq.NEXTVAL";
		String baseSQL = "contact_pidx_seq.CURRVAL";
		sql = "INSERT INTO contact_univ(pidx, fr_idx, major, grade) VALUES (?, ?, ?, ?)";
		
		conn = prov.getConnection();
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, univSeqSQL);
		pstmt.setString(2, baseSQL);
		pstmt.setString(3, conUniv.getMajor());
		pstmt.setString(4, conUniv.getGrade());
		
		return result = pstmt.executeUpdate();
	}
	
	
	public int contactComInfo(ContactCOM conComp) throws SQLException {
		int result = 0;
		String comSeqSQL = "contact_com_pidx_seq.NEXTVAL";
		String baseSQL = "contact_pidx_seq.CURRVAL";
		sql = "INSERT INTO contact_com(pidx, fr_idx, company, job) VALUES (?, ?, ?, ?)";
		
		conn = prov.getConnection();
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, comSeqSQL);
		pstmt.setString(2, baseSQL);
		pstmt.setString(3, conComp.getCompany());
		pstmt.setString(4, conComp.getJob());
		
		return result = pstmt.executeUpdate(); 
	}
	
	
	public int contactClubInfo(ContactCLUB conClub) throws SQLException {
		int result = 0;
		String clubSeqSQL = "contact_club_pidx_seq.NEXTVAL";
		String baseSQL = "contact_pidx_seq.CURRVAL";
		sql = "INSERT INTO contact_club(pidx, fr_idx, clubname, nickname) VALUES (?, ?, ?, ?)";
		
		conn = prov.getConnection();
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, clubSeqSQL);
		pstmt.setString(2, baseSQL);
		pstmt.setString(3, conClub.getClubname());
		pstmt.setString(4, conClub.getNickname());
		
		return result = pstmt.executeUpdate(); 
	}
	
}
