package model;

public class Coupon {
	private int Coupon_id;
	private String Coupon_statement;
	private String Coupon_Userid;
	public int getCoupon_id() {
		return Coupon_id;
	}
	public void setCoupon_id(int coupon_id) {
		Coupon_id = coupon_id;
	}
	public String getCoupon_Userid() {
		return Coupon_Userid;
	}
	public void setCoupon_Userid(String coupon_Userid) {
		Coupon_Userid = coupon_Userid;
	}
	public String getCoupon_statement() {
		return Coupon_statement;
	}
	public void setCoupon_statement(String coupon_statement) {
		Coupon_statement = coupon_statement;
	}
	public float getCoupon_between() {
		return Coupon_between;
	}
	public void setCoupon_between(float coupon_between) {
		Coupon_between = coupon_between;
	}
	public float getCoupon_sub() {
		return Coupon_sub;
	}
	public void setCoupon_sub(float coupon_sub) {
		Coupon_sub = coupon_sub;
	}
	public java.sql.Timestamp getCoupon_begin() {
		return Coupon_begin;
	}
	public void setCoupon_begin(java.sql.Timestamp coupon_begin) {
		Coupon_begin = coupon_begin;
	}
	public java.sql.Timestamp getCoupon_end() {
		return Coupon_end;
	}
	public void setCoupon_end(java.sql.Timestamp coupon_end) {
		Coupon_end = coupon_end;
	}
	private float Coupon_between;
	private float Coupon_sub;
	private java.sql.Timestamp Coupon_begin;
	private java.sql.Timestamp Coupon_end;

}
