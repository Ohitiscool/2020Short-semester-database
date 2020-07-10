package model;

import javax.print.attribute.standard.Media;

public class Menu {
	private int Menu_id;
	private String Menu_name;
	public int getMenu_id() {
		return Menu_id;
	}
	public void setMenu_id(int menu_id) {
		Menu_id = menu_id;
	}
	public String getMenu_name() {
		return Menu_name;
	}
	public void setMenu_name(String menu_name) {
		Menu_name = menu_name;
	}
	public String getMenu_product() {
		return menu_product;
	}
	public void setMenu_product(String menu_product) {
		this.menu_product = menu_product;
	}
	public String getMenu_step() {
		return Menu_step;
	}
	public void setMenu_step(String menu_step) {
		Menu_step = menu_step;
	}
	public Media getMenu_imag() {
		return Menu_imag;
	}
	public void setMenu_imag(Media menu_imag) {
		Menu_imag = menu_imag;
	}
	private String menu_product;
	private String Menu_step;
	private Media Menu_imag;  //后面再说
	

}
