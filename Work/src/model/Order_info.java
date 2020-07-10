package model;

public class Order_info {
	private int Order_info_Order_id;
	private int order_info_product_id;
	private String Order_info_user_id;
	private int order_info_count;
	private float Order_info_begin_price;
	private float Order_info_end_price;
	private int Order_info_coupon_id;
	private java.sql.Timestamp order_info_Order_planTime;
	private java.sql.Timestamp order_info_order_finishTime;
	public int getOrder_info_product_id() {
		return order_info_product_id;
	}
	public void setOrder_info_product_id(int order_info_product_id) {
		this.order_info_product_id = order_info_product_id;
	}
	public int getOrder_info_count() {
		return order_info_count;
	}
	public void setOrder_info_count(int order_info_count) {
		this.order_info_count = order_info_count;
	}
	public java.sql.Timestamp getOrder_info_order_finishTime() {
		return order_info_order_finishTime;
	}
	public void setOrder_info_order_finishTime(java.sql.Timestamp order_info_order_finishTime) {
		this.order_info_order_finishTime = order_info_order_finishTime;
	}
	private int Order_info_address_id;
	private int Order_info_statement;
	public int getOrder_info_Order_id() {
		return Order_info_Order_id;
	}
	public void setOrder_info_Order_id(int order_info_Order_id) {
		Order_info_Order_id = order_info_Order_id;
	}
	public String getOrder_info_user_id() {
		return Order_info_user_id;
	}
	public void setOrder_info_user_id(String order_info_user_id) {
		Order_info_user_id = order_info_user_id;
	}
	public float getOrder_info_begin_price() {
		return Order_info_begin_price;
	}
	public void setOrder_info_begin_price(float order_info_begin_price) {
		Order_info_begin_price = order_info_begin_price;
	}
	public float getOrder_info_end_price() {
		return Order_info_end_price;
	}
	public void setOrder_info_end_price(float order_info_end_price) {
		Order_info_end_price = order_info_end_price;
	}
	public int getOrder_info_coupon_id() {
		return Order_info_coupon_id;
	}
	public void setOrder_info_coupon_id(int order_info_coupon_id) {
		Order_info_coupon_id = order_info_coupon_id;
	}
	public java.sql.Timestamp getOrder_info_Order_planTime() {
		return order_info_Order_planTime;
	}
	public void setOrder_info_Order_planTime(java.sql.Timestamp order_info_Order_planTime) {
		this.order_info_Order_planTime = order_info_Order_planTime;
	}
	public int getOrder_info_address_id() {
		return Order_info_address_id;
	}
	public void setOrder_info_address_id(int order_info_address_id) {
		Order_info_address_id = order_info_address_id;
	}
	public int getOrder_info_statement() {
		return Order_info_statement;
	}
	public void setOrder_info_statement(int order_info_statement) {
		Order_info_statement = order_info_statement;
	}

}
