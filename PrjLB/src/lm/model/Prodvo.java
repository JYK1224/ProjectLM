package lm.model;

public class Prodvo {

	private String pid;
	private String pname;
	private int iprice;
	private int sprice;
	private String aname;
	private String dname;
	
	public Prodvo () {}

	public Prodvo(String pid, String pname, int iprice, int sprice, String aname, String dname) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.iprice = iprice;
		this.sprice = sprice;
		this.aname = aname;
		this.dname = dname;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getIprice() {
		return iprice;
	}

	public void setIprice(int iprice) {
		this.iprice = iprice;
	}

	public int getSprice() {
		return sprice;
	}

	public void setSprice(int sprice) {
		this.sprice = sprice;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	@Override
	public String toString() {
		return "Prodvo [pid=" + pid + ", pname=" + pname + ", iprice=" + iprice + ", sprice=" + sprice + ", aname="
				+ aname + ", dname=" + dname + "]";
	}

	

	
	
}