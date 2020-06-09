package dto;

public class ContactUNIV {
	private int pidx;
	private int fr_idx;
	private String major;
	private String grade;
	
	public int getPidx() {
		return pidx;
	}
	public int getFr_idx() {
		return fr_idx;
	}
	public String getMajor() {
		return major;
	}
	public String getGrade() {
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
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	@Override
	public String toString() {
		return "ContactUNIV [pidx=" + pidx + ", fr_idx=" + fr_idx + ", major=" + major + ", grade=" + grade + "]";
	}
	
	public ContactUNIV(String major, String grade) {
		super();
		this.major = major;
		this.grade = grade;
	}
}
