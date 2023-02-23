package lm.model;

public class LoginVo {
	
	//fields 
	private  String uid;

	public LoginVo(String userid) {
		this.uid = userid;
	}
	//Getter Setter
	public String getUid() {
		return uid;
	}

	public void setUid(String userid) {
		this.uid = userid;
	}
	//toString
	@Override
	public String toString() {
		return "LoginVo [userid=" + uid + "]";
	}
	
	

}
