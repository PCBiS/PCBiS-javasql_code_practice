package dto;

public class ContactCLUB {
	private int pidx;
	private int fr_idx;
	private String clubname;
	private String nickname;
	
	public int getPidx() {
		return pidx;
	}
	public int getFr_idx() {
		return fr_idx;
	}
	public String getClubname() {
		return clubname;
	}
	public String getNickname() {
		return nickname;
	}
	public void setPidx(int pidx) {
		this.pidx = pidx;
	}
	public void setFr_idx(int fr_idx) {
		this.fr_idx = fr_idx;
	}
	public void setClubname(String clubname) {
		this.clubname = clubname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	@Override
	public String toString() {
		return "ContactCOM [pidx=" + pidx + ", fr_idx=" + fr_idx + ", clubname=" + clubname + ", nickname=" + nickname
				+ "]";
	}
}
