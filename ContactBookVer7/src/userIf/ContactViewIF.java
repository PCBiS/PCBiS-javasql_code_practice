package userIf;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ViewDAO;
import dto.AllContactInfo;

public class ContactViewIF implements ContactMenuNumber {
	private int menuSel;
	private boolean menuExitFlag;
	private String bufferStr;
	ViewDAO view = ViewDAO.getInstance();
	List<dto.AllContactInfo> allList = new ArrayList<dto.AllContactInfo>();
	
	private static ContactViewIF instance;
	
	private ContactViewIF() {
		// Constructor
		menuSel = 0;
		menuExitFlag = true;
		bufferStr = null;
	}
	
	public static ContactViewIF getInstance() {
		if (instance == null) {
			instance = new ContactViewIF();
		}
		return instance;
	}
	
	public void setFlag() {
		if (menuExitFlag == false) {
			menuExitFlag = true;
		}
	}
	
	public void viewIF() throws SQLException {
		while (menuExitFlag) {
			System.out.println("=========================");
			System.out.println("=  전화번호부 조회 기능입니다. =");
			System.out.println("=========================");
			System.out.println("=   1.  전  체  조  회    =");
			System.out.println("=   2.  이  름  검  색    =");
			System.out.println("=   3.  종  료  하  기    =");
			System.out.println("=========================");
			menuSel = ContactUserIF.sc.nextInt();
			
			switch (menuSel) {
			case VIEW_ALL:
				// 해당 기능이 호출되면 저장된 친구 정보 전체를 출력하도록 함.
				System.out.println("등록된 전체 정보를 출력합니다.");
				System.out.println("===================================================");
				// DAO에서 가지고 온 정보를 printList 기능으로 던진다.
				printList(view.contactView());
				break;
			case VIEW_PERSON:
				// 해당 기능이 호출되면 저장된 친구 정보를 이름을 기반으로 검색하도록 함.
				System.out.println("검색 할 사람의 이름을 입력하세요.");
				System.out.println("예시 01) KIM -> KIM이라는 이름을 가진 사람을 모두 검색합니다.");
				System.out.println("예시 02) L -> L로 시작하는 이름을 가진 사람을 모두 검색합니다.");
				System.out.print("검색 할 사람의 이름을 입력 하세요. : ");
				ContactUserIF.sc.nextLine();
				bufferStr = ContactUserIF.sc.nextLine();
				// DAO에서 가지고 온 정보를 printList 기능으로 던진다.
				printList(view.contactView(bufferStr));
				break;
			case VIEW_EXIT:
				// 외부 루프를 돌리는 While문의 Flag를 변경해준다.
				menuExitFlag = false;
				break;
			}
		}
	}
	public void printList(List<AllContactInfo> allList) {
		// 받아 온 내용이 비어 있지 않으면 출력한다.
		if (allList != null && !allList.isEmpty()) {
			// 받아온 내용의 크기만큼.
			for (int i = 0; i < allList.size(); i++) {
				
				// 만약 친구 종류가 'UNIV' 타입이면.
				if (allList.get(i).getFriendtype().contentEquals("UNIV")) {
					System.out.println(
							" 번   호 : " + allList.get(i).getPidx() +  
							" 이   름 : " + allList.get(i).getName() + 
							" 전화번호 : " + allList.get(i).getPhonenum() +
							" 주   소 : " + allList.get(i).getAddress() +
							" 이 메 일 : " + allList.get(i).getEmail() + 
							" 전   공 : " + allList.get(i).getMajor() + 
							" 학   년 : " + allList.get(i).getGrade()
							);
					
					// 만약 친구 종류가 COM' 타입이면.
				}else if (allList.get(i).getFriendtype().contentEquals("COM")) {
					System.out.println(
							" 번   호 : " + allList.get(i).getPidx() +  
							" 이   름 : " + allList.get(i).getName() + 
							" 전화번호 : " + allList.get(i).getPhonenum() +
							" 주   소 : " + allList.get(i).getAddress() +
							" 이 메 일 : " + allList.get(i).getEmail() +
							" 회 사 명 : " + allList.get(i).getCompany() + 
							" 직   급 : " + allList.get(i).getJob()
							);
					// 만약 친구 종류가 'CLUB' 타입이면.
				}else if (allList.get(i).getFriendtype().contentEquals("CLUB")) {
					System.out.println(
							" 번   호 : " + allList.get(i).getPidx() +  
							" 이   름 : " + allList.get(i).getName() + 
							" 전화번호 : " + allList.get(i).getPhonenum() +
							" 주   소 : " + allList.get(i).getAddress() +
							" 이 메 일 : " + allList.get(i).getEmail() +
							" 동호회명 : " + allList.get(i).getClubname() +
							" 닉 네 임 : " + allList.get(i).getNickname()
							);
					// 만약 친구 종류가 셋다 아닌 알 수 없는 데이터가 들어가있다면
				}else {
					System.out.println("알 수 없는 에러가 발생하였습니다.");
					break;
				}
			}
			//이 리스트는 버퍼 역할을 하기 때문에 클리어 해주어야 한다.
			allList.clear();
		}else {
			System.out.println("받은 데이터가 없습니다.");
			// 이건 필요없지만 안전장치겸.
			allList.clear();
		}
	}
	
}
