package lm.model;

public class Shopvo {
	private int shopid;
	private String shopname;
	private String sincharge;
	private String sphone;
	public Shopvo(int shopid, String shopname, String sincharge, String sphone) {
		this.shopid = shopid;
		this.shopname = shopname;
		this.sincharge = sincharge;
		this.sphone = sphone;
	}
	public int getShopid() {
		return shopid;
	}
	public void setShopid(int shopid) {
		this.shopid = shopid;
	}
	public String getShopname() {
		return shopname;
	}
	public void setShopname(String shopname) {
		this.shopname = shopname;
	}
	public String getSincharge() {
		return sincharge;
	}
	public void setSincharge(String sincharge) {
		this.sincharge = sincharge;
	}
	public String getSphone() {
		return sphone;
	}
	public void setSphone(String sphone) {
		this.sphone = sphone;
	}
	@Override
	public String toString() {
		return "Shopvo [shopid=" + shopid + ", shopname=" + shopname + ", sincharge=" + sincharge + ", sphone=" + sphone
				+ "]";
	}


}
