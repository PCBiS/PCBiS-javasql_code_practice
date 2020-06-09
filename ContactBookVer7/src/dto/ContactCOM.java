package dto;

public class ContactCOM {
	private int pidx;
	private int fr_idx;
	private String company;
	private String job;
	
	public int getPidx() {
		return pidx;
	}
	public int getFr_idx() {
		return fr_idx;
	}
	public String getCompany() {
		return company;
	}
	public String getJob() {
		return job;
	}
	public void setPidx(int pidx) {
		this.pidx = pidx;
	}
	public void setFr_idx(int fr_idx) {
		this.fr_idx = fr_idx;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public void setJob(String job) {
		this.job = job;
	}
	
	@Override
	public String toString() {
		return "ContactCOM [pidx=" + pidx + ", fr_idx=" + fr_idx + ", company=" + company + ", job=" + job + "]";
	}
	
	public ContactCOM(String company, String job) {
		super();
		this.company = company;
		this.job = job;
	}
	
}
