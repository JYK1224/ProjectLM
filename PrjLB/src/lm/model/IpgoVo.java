package lm.model;

public class IpgoVo {
	// Fields
	private String   indate;         // 입고일자
	private String   pid;            // 상품코드
	private String   pname;          // 상품명
	private String   stocknum;       // 입고가격
	private String   innum;          // 현재재고
	private String   orderdate;      // 주문일
	private String   ordernum;       // 주문수량
	private String   userid;         // 입고직원
	private String   dname;          // 거래처명
	
	
	// Constructor
	public IpgoVo() {}
	public IpgoVo(String indate, String pid, String pname, String stocknum, String innum, String orderdate,
			 String userid, String dname, String ordernum, String inndate) {
		this.indate         = indate;
		this.pid            = pid;
		this.pname          = pname;
		this.stocknum       = stocknum;
		this.innum          = innum;
		this.orderdate      = orderdate;
		this.userid         = userid;
		this.dname          = dname;
		this.ordernum       = ordernum;
	}
	
	
	// Getter / Setter
	public String getIndate() {
		return indate;
	}
	public void setIndate(String indate) {
		this.indate = indate;
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
	public String getStocknum() {
		return stocknum;
	}
	public void setStocknum(String stocknum) {
		this.stocknum = stocknum;
	}
	public String getInnum() {
		return innum;
	}
	public void setInnum(String innum) {
		this.innum = innum;
	}
	public String getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}
	
	
	// toString
	@Override
	public String toString() {
		return "LmVO [indate=" + indate + ", pid=" + pid + ", pname=" + pname + ", stocknum=" + stocknum + ", innum="
				+ innum + ", orderdate=" + orderdate + ", userid=" + userid + ", dname=" + dname +", ordernum=" + ordernum + "]";
	}
	
}
