package model;

public class Full_discount {
	private int full_distcount_id;
	private int full_distcount_product_id;
	private float full_distcount_base; //base对应between
	private String full_distcount_statement; //描述
	private float full_distcount_discount;
	private java.sql.Timestamp full_distcount_begin_id;
	private java.sql.Timestamp full_distcount_end_id;
	public int getFull_distcount_id() {
		return full_distcount_id;
	}
	public void setFull_distcount_id(int full_distcount_id) {
		this.full_distcount_id = full_distcount_id;
	}
	public String getFull_distcount_statement() {
		return full_distcount_statement;
	}
	public void setFull_distcount_statement(String full_distcount_statement) {
		this.full_distcount_statement = full_distcount_statement;
	}
	public float getFull_distcount_discount() {
		return full_distcount_discount;
	}
	public void setFull_distcount_discount(float full_distcount_discount) {
		this.full_distcount_discount = full_distcount_discount;
	}
	public java.sql.Timestamp getFull_distcount_begin_id() {
		return full_distcount_begin_id;
	}
	public void setFull_distcount_begin_id(java.sql.Timestamp full_distcount_begin_id) {
		this.full_distcount_begin_id = full_distcount_begin_id;
	}
	public java.sql.Timestamp getFull_distcount_end_id() {
		return full_distcount_end_id;
	}
	public void setFull_distcount_end_id(java.sql.Timestamp full_distcount_end_id) {
		this.full_distcount_end_id = full_distcount_end_id;
	}
	public float getFull_distcount_base() {
		return full_distcount_base;
	}
	public void setFull_distcount_base(float full_distcount_base) {
		this.full_distcount_base = full_distcount_base;
	}
	public int getFull_distcount_product_id() {
		return full_distcount_product_id;
	}
	public void setFull_distcount_product_id(int full_distcount_product_id) {
		this.full_distcount_product_id = full_distcount_product_id;
	}

}
