package model;

public class User {
	public static User currentLoginUser=null;
	private String User_id;
	private String User_name;
	private String User_sex;
	private String User_pwd;
	private String User_tel;
	private String User_email;
	private String User_city;
	private java.sql.Timestamp User_reg_time;
	private java.sql.Timestamp User_end_time;
	private int Uer_vip;
	private float sumpay;
	public float getSumpay() {
		return sumpay;
	}
	public void setSumpay(float sumpay) {
		this.sumpay = sumpay;
	}
	public String getUser_id() {
		return User_id;
	}
	public void setUser_id(String user_id) {
		User_id = user_id;
	}
	public String getUser_name() {
		return User_name;
	}
	public void setUser_name(String user_name) {
		User_name = user_name;
	}
	public String getUser_sex() {
		return User_sex;
	}
	public void setUser_sex(String user_sex) {
		User_sex = user_sex;
	}
	public String getUser_pwd() {
		return User_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		User_pwd = user_pwd;
	}
	public String getUser_tel() {
		return User_tel;
	}
	public void setUser_tel(String user_tel) {
		User_tel = user_tel;
	}
	public String getUser_email() {
		return User_email;
	}
	public void setUser_email(String user_email) {
		User_email = user_email;
	}
	public String getUser_city() {
		return User_city;
	}
	public void setUser_city(String user_city) {
		User_city = user_city;
	}
	public java.sql.Timestamp getUser_reg_time() {
		return User_reg_time;
	}
	public void setUser_reg_time(java.sql.Timestamp user_reg_time) {
		User_reg_time = user_reg_time;
	}
	public java.sql.Timestamp getUser_end_time() {
		return User_end_time;
	}
	public void setUser_end_time(java.sql.Timestamp user_end_time) {
		User_end_time = user_end_time;
	}
	public int getUer_vip() {
		return Uer_vip;
	}
	public void setUer_vip(int uer_vip) {
		Uer_vip = uer_vip;
	}
	
	

}
