package lm.model;

public class OrderVo {
	// field
	private String orderId;
	private int order_quantity; //
	private String orderDate;	//
	private String startDate;	
	private String endDate;		
	private String userId;
	private String pCode;
	private int stock_id; 
	private String inDate;
	
	// contructor
	public OrderVo() {}
	public OrderVo(String orderId, int order_quantity, String orderDate, String startDate, String endDate,
			String userId, String pCode, int stock_id, String inDate) {
		super();
		this.orderId = orderId;
		this.order_quantity = order_quantity;
		this.orderDate = orderDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.userId = userId;
		this.pCode = pCode;
		this.stock_id = stock_id;
		this.inDate = inDate;
	}
	// getter, setter
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public int getOrder_quantity() {
		return order_quantity;
	}
	public void setOrder_quantity(int order_quantity) {
		this.order_quantity = order_quantity;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getpCode() {
		return pCode;
	}
	public void setpCode(String pCode) {
		this.pCode = pCode;
	}
	public int getStock_id() {
		return stock_id;
	}
	public void setStock_id(int stock_id) {
		this.stock_id = stock_id;
	}
	public String getInDate() {
		return inDate;
	}
	public void setInDate(String inDate) {
		this.inDate = inDate;
	}
	
	@Override
	public String toString() {
		return "OrderTable [orderId=" + orderId + ", order_quantity=" + order_quantity + ", orderDate=" + orderDate
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", userId=" + userId + ", pCode=" + pCode
				+ ", stock_id=" + stock_id + ", inDate=" + inDate + "]";
	}

}
