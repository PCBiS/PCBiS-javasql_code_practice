package manager;

import java.util.Scanner;

import deptManager.DeptManager;
import empManager.EmpManager;

public class CompanyManager implements CompManagerInterface{
	public static void main(String[] args) {
		EmpManager eMan = EmpManager.getInstance();
		DeptManager dMan = DeptManager.getInstance();
		Scanner sc = new Scanner(System.in);
		boolean subFlag = true;
		int mainSelect = 0, subSelect = 0, minorSelect = 0;
		String stringBuffer = null;
		
		while (true) {
			subFlag = true;
			System.out.println("회사 관리 프로그램 - 메인메뉴");
			System.out.println("==============================");
			System.out.println("1. 사원관리");
			System.out.println("2. 부서관리");
			mainSelect = sc.nextInt();
			switch (mainSelect) {
			case CompManagerInterface.EMP_MANAGER:
				while (subFlag) {
					System.out.println("회사 관리 프로그램 - 사원관리 기능");
					System.out.println("==============================");
					System.out.println("1. 신규 사원 등록");
					System.out.println("2. 사원 정보 수정");
					System.out.println("3. 사원 정보 삭제");
					System.out.println("4. 사원 정보 출력");
					System.out.println("5. 이전 메뉴로 돌아가기.");
					subSelect = sc.nextInt();
					switch (subSelect) {
						case EMP_MANAGER_ADD:
							eMan.empMemberAdd();
							break;
						case EMP_MANAGER_MODIFY:
							System.out.println("기능 구현중 " + EMP_MANAGER_MODIFY);
							break;
						case EMP_MANAGER_DELETE:
							eMan.empMemberDelete();
							break;
						case EMP_MANAGER_LIST:
							eMan.empMemberList();	
							break;
						case EMP_MANAGER_EXIT:
							System.out.println("메인 메뉴로 돌아갑니다.");
							subFlag = false;
							break;
						}
				}
				break;
			case CompManagerInterface.DEPT_MANAGER:
				while (subFlag) {
					System.out.println("회사 관리 프로그램 - 사원관리 기능");
					System.out.println("==============================");
					System.out.println("1. 신규 부서 등록");
					System.out.println("2. 부서 정보 수정");
					System.out.println("3. 부서 정보 삭제");
					System.out.println("4. 부서 정보 출력");
					System.out.println("5. 이전 메뉴로 돌아가기.");
					subSelect = sc.nextInt();
					switch (subSelect) {
						case DEPT_MANAGER_ADD:
							System.out.println("기능 구현중 " + DEPT_MANAGER_ADD);
							break;
						case DEPT_MANAGER_MODIFY:
							System.out.println("기능 구현중 " + DEPT_MANAGER_MODIFY);
							break;
						case DEPT_MANAGER_DELETE:
							System.out.println("기능 구현중 " + DEPT_MANAGER_DELETE);
							break;
						case DEPT_MANAGER_LIST:
							System.out.println("회사 관리 프로그램 - 부서정보 출력");
							System.out.println("==============================");
							System.out.println("1. 전체 정보 출력");
							System.out.println("2. 특정부서(부서번호 기준) 출력");
							minorSelect = sc.nextInt();
							switch (minorSelect) {
							case 1:
								System.out.println("");
								System.out.println("부서 전체 정보를 출력합니다!");
								dMan.deptMemberList();
								System.out.println("");	
								break;
							case 2:
								sc.nextLine();
								System.out.println("");
								System.out.println("검색 할 부서 번호를 입력해주세요.");
								stringBuffer = sc.nextLine();
								if (!stringBuffer.isEmpty()) {
									dMan.deptMemberList(stringBuffer);
								}else{
									System.out.println("부서 번호가 입력 되지 않았습니다. 다시 한번 확인해주세요!");
									stringBuffer = null;
									continue;
								}
								System.out.println("");
								break;

							default:
								break;
							}
						case DEPT_MANAGER_EXIT:
							System.out.println("메인 메뉴로 돌아갑니다.");
							subFlag = false;
							break;
						}
				}
				break;
			case CompManagerInterface.MANAGER_EXIT:
				System.exit(0);
			}
		}
	}
}
