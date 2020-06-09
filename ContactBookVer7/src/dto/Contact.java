package dto;

public class Contact {
	private int pidx;
	private String name;
	private String phonenum;
	private String address;
	private String email;
	private String friendtype;
	
	public Contact(String name, String phonenum, String address, String email, String friendtype) {
		this.name = name;
		this.phonenum = phonenum;
		this.address = address;
		this.email = email;
		this.friendtype = friendtype;
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
	
	@Override
	public String toString() {
		return "Contact [pidx=" + pidx + ", name=" + name + ", phonenum=" + phonenum + ", address=" + address
				+ ", email=" + email + ", friendtype=" + friendtype + "]";
	}
}
