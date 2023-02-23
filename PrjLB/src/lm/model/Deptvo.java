package lm.model;

public class Deptvo {
		
	private int deptid;
	private String deptdname;
	private String deptname;
	private String deptphone;
		
	public Deptvo () {}

	public Deptvo(int deptid, String deptdname, String deptname, String deptphone) {
		super();
		this.deptid = deptid;
		this.deptdname = deptdname;
		this.deptname = deptname;
		this.deptphone = deptphone;
	}

	public int getDeptid() {
		return deptid;
	}

	public void setDeptid(int deptid) {
		this.deptid = deptid;
	}

	public String getDeptdname() {
		return deptdname;
	}

	public void setDeptdname(String deptdname) {
		this.deptdname = deptdname;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getDeptphone() {
		return deptphone;
	}

	public void setDeptphone(String deptphone) {
		this.deptphone = deptphone;
	}

	@Override
	public String toString() {
		return "Deptvo [deptid=" + deptid + ", deptaname=" + deptdname + ", deptname=" + deptname + ", deptphone="
				+ deptphone + "]";
	}
	

}
