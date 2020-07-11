package model;

import java.sql.Timestamp;

public class Product_eva {
	private int Product_Evaluation_id;
	private String Product_Evaluation_user_id;
	private int Product_Evaluation_product_id;
	private Timestamp Product_Evaluation_time;
	private int Product_Evaluation_level;
	private String Product_Evaluation_imag;
	public int getProduct_Evaluation_id() {
		return Product_Evaluation_id;
	}
	public void setProduct_Evaluation_id(int product_Evaluation_id) {
		Product_Evaluation_id = product_Evaluation_id;
	}
	public String getProduct_Evaluation_user_id() {
		return Product_Evaluation_user_id;
	}
	public void setProduct_Evaluation_user_id(String product_Evaluation_user_id) {
		Product_Evaluation_user_id = product_Evaluation_user_id;
	}
	public int getProduct_Evaluation_product_id() {
		return Product_Evaluation_product_id;
	}
	public void setProduct_Evaluation_product_id(int product_Evaluation_product_id) {
		Product_Evaluation_product_id = product_Evaluation_product_id;
	}
	public Timestamp getProduct_Evaluation_time() {
		return Product_Evaluation_time;
	}
	public void setProduct_Evaluation_time(Timestamp product_Evaluation_time) {
		Product_Evaluation_time = product_Evaluation_time;
	}
	public int getProduct_Evaluation_level() {
		return Product_Evaluation_level;
	}
	public void setProduct_Evaluation_level(int product_Evaluation_level) {
		Product_Evaluation_level = product_Evaluation_level;
	}
	public String getProduct_Evaluation_imag() {
		return Product_Evaluation_imag;
	}
	public void setProduct_Evaluation_imag(String product_Evaluation_imag) {
		Product_Evaluation_imag = product_Evaluation_imag;
	}
}
