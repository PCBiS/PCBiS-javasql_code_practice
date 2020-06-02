package empManager;

import java.util.List;
import java.util.ArrayList;

public class EmpDAO {
	public static void main(String[] args) {
		List<EMP> empList = new ArrayList<>();
		
		while(rs.next()) {
			EMP emp = new EMP(empno, ename, job, mgr, hiredate, sal, comm, deptno);
			empList.add(emp);
		}
		
	}
}
