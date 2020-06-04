package dto;

public class ContactUNIV {
	private int pidx;
	private int fr_idx;
	private String major;
	private int grade;
	
	public int getPidx() {
		return pidx;
	}
	public int getFr_idx() {
		return fr_idx;
	}
	public String getMajor() {
		return major;
	}
	public int getGrade() {
		return grade;
	}
	public void setPidx(int pidx) {
		this.pidx = pidx;
	}
	public void setFr_idx(int fr_idx) {
		this.fr_idx = fr_idx;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public void setGrade(int grade) {
		if (grade > 1 || grade < 4) {
			System.out.println("입력 범위 초과! 1~4의 값이 입력되어야 합니다.");
		}else {
			this.grade = grade;
		}
	}
	
	@Override
	public String toString() {
		return "ContactUNIV [pidx=" + pidx + ", fr_idx=" + fr_idx + ", major=" + major + ", grade=" + grade + "]";
	}
	
	public ContactUNIV(int pidx, int fr_idx, String major, int grade) {
		super();
		this.pidx = pidx;
		this.fr_idx = fr_idx;
		this.major = major;
		this.grade = grade;
	}
}
