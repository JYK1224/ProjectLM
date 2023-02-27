package lm.model;

public class OutputVo {
	private String outId;
	private String outDate;
	private String outNum;
	private String pid;
	private String shopId;
	private String userId;
	public OutputVo() {}
	public OutputVo(String outId, String outDate, String outNum, String pid, String shopId, String userId) {
		super();
		this.outId = outId;
		this.outDate = outDate;
		this.outNum = outNum;
		this.pid = pid;
		this.shopId = shopId;
		this.userId = userId;
	}
	public String getOutId() {
		return outId;
	}
	public void setOutId(String outId) {
		this.outId = outId;
	}
	public String getOutDate() {
		return outDate;
	}
	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}
	public String getOutNum() {
		return outNum;
	}
	public void setOutNum(String outNum) {
		this.outNum = outNum;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "OutputVo [outId=" + outId + ", outDate=" + outDate + ", outNum=" + outNum + ", pid=" + pid + ", shopId="
				+ shopId + ", userId=" + userId + "]";
	}

}
