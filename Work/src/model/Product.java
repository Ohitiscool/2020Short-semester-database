package model;

public class Product {
	public static final String[] tabletitles= {"产品id","产品类型","产品名称","价格","会员价","库存","规格","描述"};
	private int Product_id;
	private int Product_type_id;
	private String Product_name;
	private float Product_price;
	private float Product_vip_price;
	private int Product_stock;
	private String Product_format;
	private String Product_statement;
	public String getcell(int col) {
		if(col==0) return ""+getProduct_id();
		else if(col==1) return ""+getProduct_type_id(); 
		else if(col==2) return getProduct_name();
		else if(col==3) return ""+getProduct_price();
		else if(col==4) return ""+getProduct_vip_price();
		else if(col==5) return ""+getProduct_stock();
		else if(col==6) return getProduct_format();
		else if(col==7) return getProduct_statement();
		return "";
	}
	public int getProduct_id() {
		return Product_id;
	}
	public void setProduct_id(int product_id) {
		Product_id = product_id;
	}
	public int getProduct_type_id() {
		return Product_type_id;
	}
	public void setProduct_type_id(int product_type_id) {
		Product_type_id = product_type_id;
	}
	public String getProduct_name() {
		return Product_name;
	}
	public void setProduct_name(String product_name) {
		Product_name = product_name;
	}
	public float getProduct_price() {
		return Product_price;
	}
	public void setProduct_price(float product_price) {
		Product_price = product_price;
	}
	public float getProduct_vip_price() {
		return Product_vip_price;
	}
	public void setProduct_vip_price(float product_vip_price) {
		Product_vip_price = product_vip_price;
	}
	public int getProduct_stock() {
		return Product_stock;
	}
	public void setProduct_stock(int product_stock) {
		Product_stock = product_stock;
	}
	public String getProduct_format() {
		return Product_format;
	}
	public void setProduct_format(String product_format) {
		Product_format = product_format;
	}
	public String getProduct_statement() {
		return Product_statement;
	}
	public void setProduct_statement(String product_statement) {
		Product_statement = product_statement;
	}

}
