package userIf;

public class ContactModifyIF implements ContactMenuNumber{
	private int menuSel;
	private String name, pnum, addr, email, bufferStr;
	private boolean menuExitFlag, inputOkFlag;
	RegEX ex = RegEX.getInstance();
	
	private static ContactModifyIF instance;
	private ContactModifyIF() {
		//Constructor
		menuSel = -1;
		name = null; pnum = null; addr = null; email = null; bufferStr = null;
		menuExitFlag = true; inputOkFlag = true;
	}
	public static ContactModifyIF getInstance() {
		if (instance == null) {
			instance = new ContactModifyIF();
		}
		return instance;
	}
	
	// 입력 인스턴스 플래그는 하나뿐이여서 한번 내부 플래그가 False로 돌아가버리면 프로그램 재시작 전에는 다시 기능을 쓸 수 없다.
	public void setFlag() {
		if (menuExitFlag == false) {
			menuExitFlag = true;
		}
	}
	
	public void ModifyIF() {
		while (menuExitFlag) {
			inputFlagReset();
			System.out.println("=========================");
			System.out.println("=  전화번호부 수정 기능입니다. =");
			System.out.println("=========================");
			System.out.println("=   1. 이 름 기 준 수 정   =");
			System.out.println("=   2. 전 번 기 준 수 정   =");
			System.out.println("=   3. INDEX 기 준 수 정  =");
			System.out.println("=   4. 종   료   하   기  =");
			System.out.println("=========================");
			ContactUserIF.sc.nextLine();
			menuSel = ContactUserIF.sc.nextInt();
			
			switch (menuSel) {
			case MODIFY_NAME:
				System.out.println("이름을 기준으로 수정 하는 기능입니다.");
				System.out.println("수정 할 이름을 입력해주세요.");
				System.out.println(">> ");
				ContactUserIF.sc.nextLine();
				this.bufferStr = ContactUserIF.sc.nextLine();
				if (!this.bufferStr.isEmpty() && !this.bufferStr.contentEquals("")) {
					System.out.println("기능 구현중. " + this.bufferStr);
					// 이 자리에 이름을 기준으로 검색하여 수정할 사람이 맞는지 확인 해 주고.
					inputValue();
					// 이 자리에 DAO에서 받아온 친구 종류에 따라서 수정할 추가 정보를 구현해야 하며.
					// 이 자리에 ModifyDAO로 일괄로 Update할 데이터를 add해서 배열단위로 입력해야 한다.
					this.bufferStr = null;
				}
				break;
			case MODIFY_PHONE:
				System.out.println("전화번호를 기준으로 수정 하는 기능입니다.");
				System.out.println("수정 할 전화번호를 입력해주세요.");
				System.out.println(">> ");
				ContactUserIF.sc.nextLine();
				this.bufferStr = ContactUserIF.sc.nextLine();
				if (!this.bufferStr.isEmpty() && !this.bufferStr.contentEquals("") && ex.phoneNumberCheck(bufferStr)) {
					System.out.println("기능 구현중. " + this.bufferStr);
					//이 자리에 이름을 기준으로 검색하여 수정할 사람이 맞는지 확인 해 주고.
					inputValue();
					// 이 자리에 DAO에서 받아온 친구 종류에 따라서 수정할 추가 정보를 구현해야 하며.
					// 이 자리에 ModifyDAO로 일괄로 Update할 데이터를 add해서 배열단위로 입력해야 한다.
					this.bufferStr = null;
				}else {
					System.out.println("입력이 잘못되었습니다.");
				}
				break;
			case MODIFY_IDX:
				System.out.println("Index 번호를 기준으로 수정 하는 기능입니다.");
				System.out.println("수정 할 Index 번호를 입력해주세요.");
				System.out.println(">> ");
				ContactUserIF.sc.nextLine();
				this.bufferStr = ContactUserIF.sc.nextLine();
				if (!this.bufferStr.isEmpty() && !this.bufferStr.contentEquals("") && ex.isNumber(bufferStr)) {
					System.out.println("기능 구현중. " + this.bufferStr);
					//이 자리에 이름을 기준으로 검색하여 수정할 사람이 맞는지 확인 해 주고.
					inputValue();
					// 이 자리에 DAO에서 받아온 친구 종류에 따라서 수정할 추가 정보를 구현해야 하며.
					// 이 자리에 ModifyDAO로 일괄로 Update할 데이터를 add해서 배열단위로 입력해야 한다.
					this.bufferStr = null;
				}else {
					System.out.println("입력이 잘못되었습니다.");
				}
				break;
			case MODIFY_EXIT:
				menuExitFlag = false;
				break;
			}
		}
	}
	
	private void inputValue() {
		System.out.println("=========================");
		System.out.println("=  전화번호부 수정 기능입니다. =");
		System.out.println("=========================");
			
		while (inputOkFlag) {
			System.out.println("등록할 이름을 입력해주세요.");
			System.out.print(">> ");
			ContactUserIF.sc.nextLine();
			this.name = ContactUserIF.sc.nextLine();
			if (name.isEmpty()) {
				System.out.println("이름 입력이 되지 않았습니다.");
			}else {
				inputOkFlag = false;
			}
		}
		inputFlagReset();
		
		while (inputOkFlag) {
			System.out.println("등록할 전화번호를 입력해주세요.");
			System.out.print(">> ");
			this.pnum = ContactUserIF.sc.nextLine();
				if (!ex.phoneNumberCheck(pnum)) {
				System.out.println("전화번호 입력 규칙에 맞지 않습니다!");
				System.out.println("예제 : 010-1234-5678, 02-987-6543");
				this.pnum = null;
			}else {
				inputOkFlag = false;
			}
		}
		inputFlagReset();
			
		System.out.println("등록할 주소를 입력해주세요, 입력하지 않으시면 생략됩니다.");
		System.out.print(">> ");		
		this.addr = ContactUserIF.sc.nextLine();
			
		// 이메일주소는 입력되면 주소검증이 필요하기 때문에 DB에서 기본값이 있는것과 관계없이 체크필요.
		while (inputOkFlag) {
			System.out.println("등록할 이메일 주소를 입력해주세요, 입력하지 않으시면 생략됩니다.");
			System.out.print(">> ");
			this.email = ContactUserIF.sc.nextLine();
							
			if (this.email.isEmpty()) {
				inputOkFlag = false;
				continue;
			}else if (!ex.emailCheck(this.email)) {
				System.out.println("이메일 주소 입력 규칙에 어긋납니다.");
				System.out.println("예제 : thisisexampleadd@thisismaximumle.emaila");
				this.email = null;
			}
		}
		inputFlagReset();
			
		System.out.println("Debug!! Input Value!");
		System.out.println("name : " + this.name);
		System.out.println("pnum : " + this.pnum);
		System.out.println("email : " + this.email);
		System.out.println("addr : " + this.addr);
	}
	
	private void inputFlagReset() {
		if (inputOkFlag == false) {
			inputOkFlag = true;
		}
	}
}
