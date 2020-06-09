package userIf;

import java.sql.SQLException;
import dao.InsertDAO;
import dto.Contact;
import dto.ContactCLUB;
import dto.ContactCOM;
import dto.ContactUNIV;

public class ContactAddIF {
	private int fType, more;
	private String name, pnum, addr, email, fText;
	private String grade, major, company, job, clubname, nickname;
	private boolean menuExitFlag, inputOkFlag;
	RegEX ex = RegEX.getInstance();
	InsertDAO insDAO = InsertDAO.getInstance();
	
	
	private static ContactAddIF instance;
	
	private ContactAddIF() {
		// Contstructor
		name = null; pnum = null; addr = null; email = null; fText = null; fType = 0;
		grade = null; major = null; company = null; job = null; clubname = null; nickname = null;
		more = -1;
		menuExitFlag = true;
		inputOkFlag = true;
	}
	
	public static ContactAddIF getInstance() {
		if (instance == null) {
			instance = new ContactAddIF(); 
		}
		return instance;
	}
	
	// 입력 인스턴스 플래그는 하나뿐이여서 한번 내부 플래그가 False로 돌아가버리면 프로그램 재시작 전에는 다시 기능을 쓸 수 없다.
	public void setFlag() {
		if (menuExitFlag == false) {
			menuExitFlag = true;
		}
	}
	
	public void AddIF() throws SQLException {
		while (menuExitFlag) {
			inputFlagReset(); 
			// 재입력을 선택 했을경우 직전 inputOKFlag상태는 False 이며 플래그 초기화 없이는 마지막(전화번호 추가등록 여부 질문)으로 직행한다.
			System.out.println("=========================");
			System.out.println("=  전화번호부 등록 기능입니다. =");
			System.out.println("=========================");
			
			while (inputOkFlag) {
				ContactUserIF.sc.nextLine();
				System.out.println("등록할 이름을 입력해주세요.");
				System.out.print(">> ");
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
			

			// 주소는 입력이 안되면 기본 값으로 들어가기 때문에 굳이 while문을 통한 로직검증 필요 없음.
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
		
			while (inputOkFlag) {
				System.out.println("등록 할 친구는 어떤 친구인가요? 아래 선택지에서 선택해주세요.");
				System.out.println(" 1. 대학교 / 2. 회사 / 3. 동호회");
				System.out.print(">> ");
				this.fType = ContactUserIF.sc.nextInt();
				
				if (this.fType < 1 || this.fType > 3) {
					System.out.println("1~3 중에서 선택해주셔야 합니다!");
					this.fType = 0;
					this.fText = null;
				}else if (this.fType == 1) {
					fText = "UNIV";
					inputOkFlag = false;
				}else if (this.fType == 2) {
					fText = "COM";
					inputOkFlag = false;
				}else if (this.fType == 3){
					fText = "CLUB";
					inputOkFlag = false;
				}
			}
			inputFlagReset();
			
			System.out.println("Debug!! Input Value!");
			System.out.println("name : " + this.name);
			System.out.println("pnum : " + this.pnum);
			System.out.println("email : " + this.email);
			System.out.println("addr : " + this.addr);
			System.out.println("ftype : " + fType + " text : " + fText);
			
			
				if (this.fType == 1) {
					while (inputOkFlag) {
						// 1. Univ
						System.out.println("대학교 친구를 선택하셨습니다. 친구의 학년을 입력해주세요.");
						System.out.println("만약 입력하지 않으면 1학년으로 기본 등록됩니다.");
						System.out.print(">> ");
						ContactUserIF.sc.nextLine();
						this.grade = ContactUserIF.sc.nextLine();
						if (!ex.grade(this.grade)) {
							System.out.println("입력이 잘못되었습니다. 1~4 이내의 범위의 숫자만을 입력해주세요.");
						}else {
							inputOkFlag = false;
						}
					}
					inputFlagReset();
					while (inputOkFlag) {
						System.out.println("친구의 전공을 입력해주세요.");
						System.out.print(">> ");
						this.major = ContactUserIF.sc.nextLine();
						if (!this.major.isEmpty() && !this.major.contentEquals("")) {
							inputOkFlag = false;
						}else {
							System.out.println("전공은 입력되어야 합니다. 다시 입력해주세요.");
						}
					}
					inputFlagReset();
					insDAO.ContactBaseInfo(new Contact(this.name, this.pnum, this.addr, this.email, this.fText));
					insDAO.contactUnivInfo(new ContactUNIV(this.major, this.grade));
					break;
				}else if (this.fType == 2) {
					// 2. Com
					while (inputOkFlag) {
						System.out.println("회사 동료를 선택하셨습니다. 동료가 다니는 회사 이름을 입력해주세요.");
						System.out.print(">> ");
						ContactUserIF.sc.nextLine();
						this.company = ContactUserIF.sc.nextLine();
						if (!this.company.isEmpty() && !this.company.contentEquals("") ) {
							inputOkFlag = false;
						}else {
							System.out.println("회사 이름 입력이 필요합니다. 다시 입력해주세요.");
						}
					}
					inputFlagReset();
					while (inputOkFlag) {
						System.out.println("동료의 직급을 입력해주세요.");
						System.out.print(">> ");
						this.job = ContactUserIF.sc.nextLine();
						if (!this.job.isEmpty() && !this.job.contentEquals("")) {
							inputOkFlag = false;
						}else {
							System.out.println("직급을 입력하지 않으셨습니다. 다시 확인해주세요.");
						}
					}
					inputFlagReset();
					insDAO.ContactBaseInfo(new Contact(this.name, this.pnum, this.addr, this.email, this.fText));
					insDAO.contactComInfo(new ContactCOM(company, job));
					break;
				}else if (this.fType == 3) {
					// 3. Club
					while (inputOkFlag) {
						System.out.println("클럽 동료를 선택하셨습니다. 클럽명을 입력해주세요.");
						ContactUserIF.sc.nextLine();
						this.clubname = ContactUserIF.sc.nextLine();
						if (!this.clubname.isEmpty() && !this.clubname.contentEquals("")) {
							inputOkFlag = false;
						}else {
							System.out.println("클럽명이 입력 되지 않았습니다. 클럽명을 입력해주세요.");
						}
					}
					inputFlagReset();
					while (inputOkFlag) {
						System.out.println("클럽원의 닉네임을 입력해주세요.");
						this.nickname = ContactUserIF.sc.nextLine();
						if (!this.nickname.isEmpty() && !this.nickname.contentEquals("")) {
							inputOkFlag = false;
						}else {
							System.out.println("닉네임이 입력되지 않았습니다. 닉네임을 입력해주세요.");
						}
					}
					inputFlagReset();
					insDAO.ContactBaseInfo(new Contact(this.name, this.pnum, this.addr, this.email, this.fText));
					insDAO.contactClubInfo(new ContactCLUB(clubname, nickname));
				}else {
					System.out.println("입력 된 자료에 문제가 있습니다. 다시 확인해주세요.");
				}	
			
			while (inputOkFlag) {
				System.out.println("계속해서 전화번호를 등록하시겠습니까?");
				System.out.println("1. 종료하기 / 2. 계속등록");
				this.more = ContactUserIF.sc.nextInt();
				
				if (this.more < 1 || this.more > 2) {
					System.out.println("입력 범위를 초과하였습니다!");
					this.more = -1;
				}else if (this.more == 1){
					System.out.println("추가 입력을 종료합니다.");
					inputOkFlag = false;
					menuExitFlag = false;
				}else if (this.more == 2) {
					System.out.println("추가 입력을 위하여 처음으로 돌아갑니다.");
					for (int i = 0; i < 10; i++) {
						System.out.println("                               ");
						inputOkFlag = false;
					}
				}
			}
		}
	}
	
	private void inputFlagReset() {
		if (inputOkFlag == false) {
			inputOkFlag = true;
		}
	}
}
