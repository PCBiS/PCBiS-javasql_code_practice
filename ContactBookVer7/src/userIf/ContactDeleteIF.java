package userIf;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DeleteDAO;
import dao.ViewDAO;
import dto.AllContactInfo;

public class ContactDeleteIF implements ContactMenuNumber {
	private int menuSel;
	private boolean menuExitFlag;
	private String bufferStr;
	RegEX ex = RegEX.getInstance();
	ViewDAO view = ViewDAO.getInstance();
	DeleteDAO del = DeleteDAO.getInstance();
	ContactViewIF viewIF = ContactViewIF.getInstance();
	List<AllContactInfo> allList = new ArrayList<AllContactInfo>();
	
	private static ContactDeleteIF instance;
	
	private ContactDeleteIF() {
		// Constructor
		menuExitFlag = true;
		menuSel = -1; bufferStr = null;
	}
	
	public static ContactDeleteIF getInstance() {
		if (instance == null) {
			instance = new ContactDeleteIF();
		}
		return instance;
	}
	
	public void setFlag() {
		if (menuExitFlag == false) {
			menuExitFlag = true;
		}
	}
	
	public void deleteIF() throws SQLException {
		while (menuExitFlag) {
			System.out.println("=========================");
			System.out.println("=  전화번호부 삭제 기능입니다. =");
			System.out.println("=========================");
			System.out.println("=   1. 이 름 기 준 삭 제   =");
			System.out.println("=   2. 전 번 기 준 삭 제   =");
			System.out.println("=   3. INDEX 기 준 삭 제  =");
			System.out.println("=   4. 종   료   하   기  =");
			System.out.println("=========================");
			menuSel = ContactUserIF.sc.nextInt();
			
			switch (menuSel) {
			case DELETE_NAME:
				System.out.println("이름을 기준으로 삭제 하는 기능입니다.");
				System.out.println("삭제 할 이름을 입력해주세요.");
				System.out.println(">> ");
				ContactUserIF.sc.nextLine();
				this.bufferStr = ContactUserIF.sc.nextLine();
				if (!this.bufferStr.isEmpty() && !this.bufferStr.contentEquals("")) {
					viewIF.printList(view.contactView(bufferStr));
					if (yesNoCheck()) {
						if (del.deleteName(bufferStr)) {
							System.out.println(this.bufferStr + " 의 삭제가 정상적으로 되었습니다.");
						}else {
							System.out.println("삭제가 정상적으로 되지 않았습니다.");
						}
						this.bufferStr = null;
					}else {
						System.out.println("삭제를 취소하였습니다.");
						this.bufferStr = null;
						break;
					}
				}else {
					System.out.println("입력이 잘못되었습니다.");
				}
				break;
				
				
			case DELETE_PHONE:
				System.out.println("전화번호를 기준으로 삭제 하는 기능입니다.");
				System.out.println("삭제 할 전화번호를 입력해주세요.");
				System.out.println(">> ");
				ContactUserIF.sc.nextLine();
				this.bufferStr = ContactUserIF.sc.nextLine();
				if (!this.bufferStr.isEmpty() && !this.bufferStr.contentEquals("") && ex.phoneNumberCheck(bufferStr)) {
					viewIF.printList(view.contactViewByPhoneNumber(bufferStr));
					if (yesNoCheck()) {
						if (del.deletePhoneNum(bufferStr)) {
							System.out.println("삭제가 정상적으로 되었습니다.");
						}else {
							System.out.println("삭제가 정상적으로 되지 않았습니다.");
						}
						this.bufferStr = null;
					}else {
						System.out.println("삭제를 취소하였습니다.");
						this.bufferStr = null;
						break;
					}
				}else {
					System.out.println("입력이 잘못되었습니다.");
				}
				break;
				
				
			case DELETE_IDX:
				System.out.println("Index 번호를 기준으로 삭제 하는 기능입니다.");
				System.out.println("삭제 할 Index 번호를 입력해주세요.");
				System.out.println(">> ");
				ContactUserIF.sc.nextLine();
				this.bufferStr = ContactUserIF.sc.nextLine();
				if (!this.bufferStr.isEmpty() && !this.bufferStr.contentEquals("") && ex.isNumber(bufferStr)) {
					viewIF.printList(view.contactViewByPIDX(bufferStr));
					if (yesNoCheck()) {
						if (del.deletePIDX(bufferStr)) {
							System.out.println("삭제가 정상적으로 되었습니다.");
						}else {
							System.out.println("삭제가 정상적으로 되지 않았습니다.");
						}
						this.bufferStr = null;
					}else {
						System.out.println("삭제를 취소하였습니다.");
						this.bufferStr = null;
						break;
					}
					this.bufferStr = null;
				}else {
					System.out.println("입력이 잘못되었습니다.");
				}
				break;
			case DELETE_EXIT:
				menuExitFlag = false;
				break;
			}
		}
	}
	private boolean yesNoCheck() {
		boolean rtnBool = false;
		System.out.println("이 사람을 삭제하시겠습니까? ");
		System.out.println("1. 삭제하기 / 2. 취소하기");
		int checkVal = ContactUserIF.sc.nextInt();
		if (checkVal==1) {
			rtnBool = true;
		}else if (checkVal==2) {
			rtnBool = false;
		}else {
			System.out.println("입력 범위를 벗어났습니다.");
			rtnBool = false;
		}
		return rtnBool;
	}
}
