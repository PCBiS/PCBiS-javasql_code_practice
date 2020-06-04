package userIf;

import java.sql.SQLException;
import java.util.Scanner;

public class ContactUserIF implements ContactMenuNumber{
	public static Scanner sc = null;
	
	public static void main(String[] args) throws SQLException{
		ContactViewIF view = ContactViewIF.getInstance();
		ContactAddIF add = ContactAddIF.getInstance();
		ContactModifyIF mod = ContactModifyIF.getInstance();
		ContactDeleteIF del = ContactDeleteIF.getInstance();
	
		sc = new Scanner(System.in);
		int menuSel = 0;

		while (true) {
			System.out.println("=========================");
			System.out.println("전화번호부 with SQL버전입니다.");
			System.out.println("=========================");
			System.out.println("=     1. 전화번호 조회     =");
			System.out.println("=     2. 전화번호 등록     =");
			System.out.println("=     3. 등록정보 수정     =");
			System.out.println("=     4. 등록정보 삭제     =");
			System.out.println("=     5. 종 료 하 기      =");
			System.out.println("=========================");
			System.out.print(">>");
			menuSel = sc.nextInt();

			switch (menuSel) {
			case CONTACT_VIEW:
				// 이걸 호출시 ContactViewIF를 호출
				view.setFlag();
				view.viewIF();
				break;
			case CONTACT_ADD:
				// 이걸 호출시 ContactAddIF를 호출
				add.setFlag();
				add.AddIF();
				break;
			case CONTACT_MOD:
				// 이걸 호출시 ContactModifyIF를 호출
				mod.setFlag();
				mod.ModifyIF();
				break;
			case CONTACT_DEL:
				// 이걸 호출시 ContactDeleteIF를 호출
				del.setFlag();
				del.deleteIF();
				break;
			case CONTACT_EXIT:
				// 최종 종료이니 Scanner를 종료하고 프로그램을 종료.
				// 다른 곳에서는 절대 스캐너 객체를 생성 하지도, 닫는일도 없어야 한다.
				sc.close();
				System.exit(0);
				break;
			}
		}
	}
}
