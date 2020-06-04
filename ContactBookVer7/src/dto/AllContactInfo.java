package dto;

public class AllContactInfo {
	private int pidx;
	private String name;
	private String phonenum;
	private String address;
	private String email;
	private String friendtype;
	private String clubname;
	private String nickname;
	private String company;
	private String job;
	private String major;
	private int grade;
	
	
	
	@Override
	public String toString() {
		return "AllContactInfo [pidx=" + pidx + ", name=" + name + ", phonenum=" + phonenum + ", address=" + address
				+ ", email=" + email + ", friendtype=" + friendtype + ", clubname=" + clubname
				+ ", nickname=" + nickname + ", company=" + company + ", job=" + job + ", major=" + major + ", grade="
				+ grade + "]";
	}
	
	public AllContactInfo(int pidx, String name, String phonenum, String address, String email, String friendtype,
		String clubname, String nickname, String company, String job, String major, int grade) {
		super();
		this.pidx = pidx;
		this.name = name;
		this.phonenum = phonenum;
		this.address = address;
		this.email = email;
		this.friendtype = friendtype;
		this.clubname = clubname;
		this.nickname = nickname;
		this.company = company;
		this.job = job;
		this.major = major;
		this.grade = grade;
	}

	public int getPidx() {
		return pidx;
	}
	public String getName() {
		return name;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public String getAddress() {
		return address;
	}
	public String getEmail() {
		return email;
	}
	public String getFriendtype() {
		return friendtype;
	}
	public String getClubname() {
		return clubname;
	}
	public String getNickname() {
		return nickname;
	}
	public String getCompany() {
		return company;
	}
	public String getJob() {
		return job;
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
	public void setName(String name) {
		this.name = name;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setFriendtype(String friendtype) {
		this.friendtype = friendtype;
	}
	public void setClubname(String clubname) {
		this.clubname = clubname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	
	
}
