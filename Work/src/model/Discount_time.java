package model;

public class Discount_time {
	private int distcount_time_id;
	private int Product_id;
	private float distcount_time_price;
	private int distcount_time_count;
	private java.sql.Timestamp distcount_time_begin;
	private java.sql.Timestamp distcount_time_end;
	public int getDistcount_time_id() {
		return distcount_time_id;
	}
	public void setDistcount_time_id(int distcount_time_id) {
		this.distcount_time_id = distcount_time_id;
	}
	public int getProduct_id() {
		return Product_id;
	}
	public void setProduct_id(int product_id) {
		Product_id = product_id;
	}
	public float getDistcount_time_price() {
		return distcount_time_price;
	}
	public void setDistcount_time_price(float distcount_time_price) {
		this.distcount_time_price = distcount_time_price;
	}
	public int getDistcount_time_count() {
		return distcount_time_count;
	}
	public void setDistcount_time_count(int distcount_time_count) {
		this.distcount_time_count = distcount_time_count;
	}
	public java.sql.Timestamp getDistcount_time_begin() {
		return distcount_time_begin;
	}
	public void setDistcount_time_begin(java.sql.Timestamp distcount_time_begin) {
		this.distcount_time_begin = distcount_time_begin;
	}
	public java.sql.Timestamp getDistcount_time_end() {
		return distcount_time_end;
	}
	public void setDistcount_time_end(java.sql.Timestamp distcount_time_end) {
		this.distcount_time_end = distcount_time_end;
	}

}
