package lm.model;

public class Uservo {
	
	private int    userid;
	private String username;
	private String userpw;
	private String ty;
	private String intro;
	private String indate;
	
	
	
	
	public Uservo() {};
	
	public Uservo(int userid, String userpw, String username, String ty, String intro, String indate) {
		this.userid = userid;
		this.username = username;
		this.userpw = userpw;
		this.ty = ty;
		this.intro = intro;
		this.indate = indate;
	}
	public Uservo(String username2) {
		this.username = username2;
	}

	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUserpw() {
		return userpw;
	}
	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getty() {
		return ty;
	}
	public void setty(String ty) {
		this.ty = ty;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getIndate() {
		return indate;
	}
	public void setIndate(String indate) {
		this.indate = indate;
	}
	@Override
	public String toString() {
		return "Uservo [userid=" + userid + ", userpw=" + userpw + ", username=" + username + ", ty=" + ty
				+ ", intro=" + intro + ", indate=" + indate + "]";
	}
	

}
