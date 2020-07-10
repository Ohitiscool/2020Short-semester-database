package model;

public class Address {
	private int Address_id;
	private String User_id;
	public int getAddress_id() {
		return Address_id;
	}
	public void setAddress_id(int address_id) {
		Address_id = address_id;
	}
	public String getUser_id() {
		return User_id;
	}
	public void setUser_id(String user_id) {
		User_id = user_id;
	}
	public String getAddress_province() {
		return Address_province;
	}
	public void setAddress_province(String address_province) {
		Address_province = address_province;
	}
	public String getAddress_city() {
		return Address_city;
	}
	public void setAddress_city(String address_city) {
		Address_city = address_city;
	}
	public String getAddress_area() {
		return Address_area;
	}
	public void setAddress_area(String address_area) {
		Address_area = address_area;
	}
	public String getAddress_person() {
		return Address_person;
	}
	public void setAddress_person(String address_person) {
		Address_person = address_person;
	}
	public String getAddress_tel() {
		return Address_tel;
	}
	public void setAddress_tel(String address_tel) {
		Address_tel = address_tel;
	}
	private String Address_province;
	private String Address_city;
	private String Address_area;
	private String Address_person;
	private String Address_tel;
	

}
