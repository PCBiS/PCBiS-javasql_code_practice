package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DeptDao {
	
	// DAO = Data Acess Object
	// 데이터베이스 처리 하는 클래스
	// 
	
	// MVC -> Model, View, Controller
	// model -> Service , Dao
	// 데이터베이스 
	
	

	public void deptEdit(Dept dept) {

		// JDBC 사용 객체
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		
		try {
			conn = DriverManager.getConnection();
			stmt = conn.createStatement();

			String selectSql = "select * from dept where dname='" + searchName + "'";

			rs = stmt.executeQuery(selectSql);

			int sDeptno = 0;
			String sDname = "";
			String sLoc = "";

			if (rs.next()) {
				sDeptno = rs.getInt("deptno");
				sDname = rs.getString("dname");
				sLoc = rs.getString("loc");
			} else {
				System.out.println("검색하신 이름의 데이터가 존재하지 않습니다.");
				return;
			}

			// 사용자 입력정보 변수
			System.out.println("부서 정보를 입력해주세요.");

			System.out.println("부서 번호 : " + sDeptno);
			System.out.println("부서 번호는 수정되지 않습니다.");

			System.out.println("부서 이름 ( " + sDname + "  ) : ");
			String dname = sc.nextLine();

			System.out.println("지역 ( " + sLoc + "  ) : ");
			String loc = sc.nextLine();

			// 공백 입력에 대한 예외처리가 있어야 하나 이번 버전에서는 모두 잘 입력된것으로 처리합니다.

			String sql = "update dept  set  dname=?, loc=? " + " where deptno=?";

			pstmt = conn.prepareStatement(sql);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dname);
			pstmt.setString(2, loc);
			pstmt.setInt(3, sDeptno);

			int resultCnt = pstmt.executeUpdate();

			if (resultCnt > 0) {
				System.out.println("정상적으로 수정 되었습니다.");
				System.out.println(resultCnt + "행이 수정되었습니다.");
			} else {
				System.out.println("수정이 되지않았습니다. 확인후 재 시도해주세요.");
			}

			// 4. 데이터베이스 연결 종료
			// pstmt.close();
			// conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// 4. 데이터베이스 연결 종료
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}

	}

	public int deptDelete(String dname) {

		// JDBC 사용 객체
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int resultCnt = 0;


		try {

			// Connection 객체 생성
			conn = ConnectionProvider.getConnection();

			String sql = "delete from dept  where dname=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dname);
			
			resultCnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// 4. 데이터베이스 연결 종료
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}
		
		return resultCnt;

	}

	public List<Dept> deptSearch(String dname) {

		// JDBC 사용 객체
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		List<Dept> list = new ArrayList<Dept>();


		try {

			// 2. 데이터베이스 연결
			// Connection 객체 생성
			conn = ConnectionProvider.getConnection();

			// 3. SQL 처리
			// Statement or PreparedStatement
			// pstmt = conn.prepareStatement(SQL 문장)

			// Mysql
			// "SELECT * FROM dept WHERE dname LIKE ?"
			// psmt.setString(1, "%"+name+"%");

			// Oracle
			// select * from dept where dname like '%'||?||'%'

			String sql = "select * from dept  where dname like '%'||?||'%' or  loc like '%'||?||'%'";
			// String sql = "select * from dept where dname=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dname);
			pstmt.setString(2, dname);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new Dept(
						rs.getInt("deptno"), 
						rs.getString("dname"), 
						rs.getString("loc")));
			}
			


			// 4. 데이터베이스 연결 종료
			// pstmt.close();
			// conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// 4. 데이터베이스 연결 종료
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}
		
		return list;

	}

	public int deptInsert(Dept dept) {

		// JDBC 사용 객체
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int resultCnt = 0;


		try {

			// Connection 객체 생성
			conn = ConnectionProvider.getConnection();

			// 3. SQL 처리
			// Statement or PreparedStatement
			// pstmt = conn.prepareStatement(SQL 문장)

			String sql = "insert into dept  (deptno, dname, loc)  values (?, ?, ?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dept.getDeptno());
			pstmt.setString(2, dept.getDname());
			pstmt.setString(3, dept.getLoc());

			resultCnt = pstmt.executeUpdate();


			// 4. 데이터베이스 연결 종료
			// pstmt.close();
			// conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// 4. 데이터베이스 연결 종료
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}
		
		return resultCnt;

	}

	public List<Dept> deptList() {
		
		// VO : Value Object , read only , getter
		// DTO : Data Transfer Object  getter/setter , toString, equals

		// JDBC 사용 객체
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// Dao 클래스 추가
		List<Dept> deptList= new ArrayList<>();

		// 공백 입력에 대한 예외처리가 있어야 하나 이번 버전에서는 모두 잘 입력된것으로 처리합니다.

		try {
			// 2. 데이터베이스 연결
			conn = ConnectionProvider.getConnection();

			String sql = "select * from dept  order by dname";

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			
			while (rs.next()) {
				
				Dept dept = new Dept(
						rs.getInt("deptno"), 
						rs.getString("dname"), 
						rs.getString("loc"));
				
				deptList.add(dept);
				
//				System.out.print(rs.getInt("deptno") + "\t");
//				System.out.printf("%15s", rs.getString("dname") + "\t");
//				System.out.printf("%15s", rs.getString("loc") + "\n");
//				resultCnt++;
			}

			System.out.println("=======================================================================");
			

			// 4. 데이터베이스 연결 종료
			// pstmt.close();
			// conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// 4. 데이터베이스 연결 종료
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}
		return deptList;

	}

	
	
	
	public int deptSearchName(String searchName) {
		
		Connection conn = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		Dept dept = null;
		int rowCnt = 0;
		
		try {
			conn = ConnectionProvider.getConnection();
			String sql = "select count(*) from dept where dname=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchName);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dept = new Dept(rs.getInt(dept.getDeptno()), rs.getString(dept.getDname()), rs.getString(dept.getLoc()));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return rowCnt;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}