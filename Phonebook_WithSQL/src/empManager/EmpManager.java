package empManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import dbConn.OracleDriver;

public class EmpManager implements OracleDriver {
	Scanner sc = null;
	private static EmpManager instance;

	private EmpManager() {
		// 초기화 생성
		sc = new Scanner(System.in);
	}

	public static EmpManager getInstance() {
		if (instance == null) {
			instance = new EmpManager();
		}
		return instance;
	}

	public void empMemberAdd() {
		int empno = 0; 
		String ename = null; 
		String job = null; 
		int mgr = 0; 
		String hiredate = null; 
		int sal = 0; 
		int comm = 0; 
		int deptno = 0;
		
		String sql = "INSERT INTO EMP(EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO) VALUES (?,?,?,?,?,?,?,?)";
		
		System.out.println("회사 관리 프로그램 - 신규 사원정보 입력");
		System.out.println("==============================");
		System.out.print("1. 사번을 입력하세요 : ");
		empno = sc.nextInt();
		System.out.print("2. 신규사원의 이름을 입력하세요 : ");
		sc.nextLine();
		ename = sc.nextLine();
		System.out.print("3. 신규사원의 직급을 입력하세요 : ");
		job = sc.nextLine();
		System.out.print("4. 신규사원의 상사를 입력하세요 : ");
		mgr = sc.nextInt();
		while(true) {
		System.out.print("5. 신규사원의 고용일을 입력하세요 : ");
		sc.nextLine();
		hiredate = sc.nextLine();
			if (hiredate.length() < 9) {
				System.out.println("고용일은 YYYY-MM-DD 형식으로 입력해주세요");
				System.out.println("ex) 1987-01-01");
			}else {
				break;
			}
		}
		System.out.print("6. 신규사원의 급여를 입력하세요 : ");
		sal = sc.nextInt();
		System.out.print("7. 신규사원의 실적수당을 입력하세요 : ");
		sc.nextLine();
		comm = sc.nextInt();
		System.out.print("8. 신규사원이 소속 할 부서번호를 입력하세요 : ");
		sc.nextLine();
		deptno = sc.nextInt();
		
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, ID, PW);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, empno);
			pstmt.setString(2, ename);
			pstmt.setString(3, job);
			pstmt.setInt(4, mgr);
			pstmt.setString(5, hiredate);
			pstmt.setInt(6, sal);
			pstmt.setInt(7, comm);
			pstmt.setInt(8, deptno);
			
			int result = pstmt.executeUpdate();
			
			if (result > 0) {
				System.out.println("사원 정보가 정상적으로 입력 되었습니다.");
				System.out.println(result + " 행이 입력 되었습니다.");
			}else {
				System.out.println("사원 정보가 정상적으로 입력 되지 않았습니다. 다시 확인해주세요.");
			}
			
			conn.close();
			pstmt.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void empMemberUpdate() {
		String sql = "SELECT * FROM emp";
		String sql1 = "UPDATE emp SET ename = '?' WHERE empno = ?";
		String sql2 = "UPDATE emp SET deptno = ? WHERE empno = ?";
		String sql3 = "UPDATE emp SET sal = ? WHERE empno = ?";
		String empno = null, ename = null, sal = null, deptno = null;
		int select = 0;
		
		System.out.println("회사 관리 프로그램 - 사원 정보 수정");
		System.out.println("==============================");
		
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, ID, PW);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = null;
			rs = pstmt.executeQuery();
			
			if (rs != null) {
				System.out.println("사원 정보");
				System.out.println("-------------------------------------------------------------------");
				System.out.println("EMPNO | ENAME | SAL | DEPTNO");
				System.out.println("-------------------------------------------------------------------");
				while (rs.next()) {
					System.out.println(rs.getInt("EMPNO") + " | " + rs.getString("ENAME") + rs.getInt("SAL") + " | " + rs.getInt("DEPTNO"));
				}
				System.out.println("-------------------------------------------------------------------");
			}
			
			if (rs != null) {
				rs.close();	
			}
			if (pstmt != null) {
				pstmt.close();	
			}
			if (conn !=null) {
				conn.close();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.print("수정 할 사원의 사번을 입력하세요 : ");
		empno = sc.nextLine();
		System.out.println("어떤 정보를 수정하시겠습니까?");
		System.out.println("1. 사원 이름 / 2. 수당 / 3. 부서 번호 / 4. 나가기.");
		System.out.print(" >>  ");
		select = sc.nextInt();
		switch (select) {
		case 1:
			sql = sql1;
			break;
		case 2:
			sql = sql2;
			break;
		case 3:
			sql = sql3;
			break;
		case 4:
			System.out.println("수정을 취소합니다.");
			break;
		}
	}
	
	public void empMemberDelete() {
		int empno = 0;
		String sql = "DELETE FROM emp WHERE empno = ?";
		
		System.out.println("회사 관리 프로그램 - 사원 정보 삭제");
		System.out.println("==============================");
		System.out.print("삭제 할 사원의 사번을 입력하세요 : ");
		empno = sc.nextInt();
		
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, ID, PW);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, empno);
			int result = pstmt.executeUpdate();
			
			if (result > 0) {
				System.out.println("사원 정보가 정상적으로 삭제 되었습니다.");
				System.out.println(result + " 행이 변경 되었습니다.");
			}else {
				System.out.println("사번이 정상적으로 입력 되지 않았습니다. 다시 확인해주세요.");
			}
			
			conn.close();
			pstmt.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void empMemberList() {
		int empno = 0, result = 0, select = 0;
		String ename = null;
		
		String sql = null;
		String sql1 = "SELECT * FROM emp";
		String sql2 = "SELECT * FROM emp WHERE empno = ?";
		String sql3 = "SELECT * FROM emp WHERE ename LIKE '%'||?||'%'";
		
		System.out.println("회사 관리 프로그램 - 사원 정보 조회");
		System.out.println("==============================");
		System.out.println("1. 사원 전체 조회");
		System.out.println("2. 특정 사원 조회(사번을 기준으로)");
		System.out.println("3. 특정 사원 조회(이름을 기준으로)");
		System.out.println("==============================");
		System.out.print("기능을 선택하세요 : ");
		select = sc.nextInt();
		switch (select) {
		case 1:
			sql = sql1;
			System.out.println("전체 사원을 조회합니다.");
			break;
		case 2:
			sql = sql2;
			System.out.print("검색 할 사원의 사번을 입력해주세요.");
			sc.nextLine();
			empno = sc.nextInt();
			break;
		case 3:
			sql = sql3;
			System.out.println("검색 할 사원의 이름을 입력해주세요, 일부만 입력하면 유사한 이름의 사원을 출력합니다.");
			sc.nextLine();
			ename = sc.nextLine();
			break;
		}
		
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, ID, PW);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = null;
			if (select == 1) {
				rs = pstmt.executeQuery();				
			}else if (select == 2) {
				pstmt.setInt(1, empno);
				rs = pstmt.executeQuery();
			}else if (select == 3) {
				pstmt.setString(1, ename);
				rs = pstmt.executeQuery();			
			}
			
			if (rs != null) {
				System.out.println("사원 정보");
				System.out.println("-------------------------------------------------------------------");
				System.out.println("EMPNO | ENAME | JOB | MGR's_EMPNO | HIREDATE | SAL | COMM | DEPTNO");
				System.out.println("-------------------------------------------------------------------");
				while (rs.next()) {
					System.out.println(rs.getInt("EMPNO") + " | " + rs.getString("ENAME") + " | " + rs.getString("JOB")
							+ " | " + rs.getInt("MGR") + " | " + rs.getDate("HIREDATE") + " | " + rs.getInt("SAL")
							+ " | " + rs.getInt("COMM") + " | " + rs.getInt("DEPTNO"));
				}
				System.out.println("-------------------------------------------------------------------");
			} else {
				System.out.println("사원 테이블의 사원이 존재하지 않습니다");
			}
			
			sql = null;
			ename = null;
			result = 0;
			
			if (rs != null) {
				rs.close();	
			}
			if (pstmt != null) {
				pstmt.close();	
			}
			if (conn !=null) {
				conn.close();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
