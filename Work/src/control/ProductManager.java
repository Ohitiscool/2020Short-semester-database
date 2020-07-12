package control;

import model.Address;
import model.Product;
import util.*;
import ui.*;
import start.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jboss.jandex.ThrowsTypeTarget;


public class  ProductManager {
	public List<Product> loadall() throws BaseException{
		List<Product> result=new ArrayList<>();
		Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="select * from product  where Product_statement=? and Product_stock>0 order by Product_type_id";
			PreparedStatement pst=connection.prepareStatement(sql);
			pst.setString(1, "In the sale");
			ResultSet rst=pst.executeQuery();
			while(rst.next()) {
				Product p=new Product();
				p.setProduct_id(rst.getInt(1));
				p.setProduct_type_id(rst.getInt(2));
				p.setProduct_name(rst.getString(3));
				p.setProduct_price(rst.getFloat(4));
				p.setProduct_vip_price(rst.getFloat(5));
				p.setProduct_stock(rst.getInt(6));
				p.setProduct_format(rst.getString(7));
				p.setProduct_statement(rst.getString(8));
				result.add(p);
			}
			pst.close();
			rst.close();
			return result;
		}catch(SQLException ex){
			ex.printStackTrace();
			throw new DbException(ex);
		}
		finally {
			if(connection!=null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
	}
	public void buy_product(int number,int id) throws BaseException {
		Connection connection=null;
		try {
			int n;
			connection=DBUtil.getConnection();
			String sql="select product_stock from product where product_id=?";
			PreparedStatement pst=connection.prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rst=pst.executeQuery();
			if(rst.next()) {
				n=rst.getInt(1);
				if(number>n) throw new BussinessException("购买数量超过库存量");
			}
			else {
				throw new BussinessException("不存在此商品");
			}
			rst.close();
			sql="update product set product_stock=product_stock-? where product_id=?";
			pst=connection.prepareStatement(sql);
			pst.setInt(1, number);
			pst.setInt(2,id);
			pst.execute();
			pst.close();
		}catch(SQLException ex){
			ex.printStackTrace();
			throw new DbException(ex);
		}
		finally {
			if(connection!=null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
	}
	public List<Product> load_p_sugg() throws BaseException{
		List<Product> result=new ArrayList<>();
		Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="select * from product where product_id In(select Order_info_product_id from order_product group by Order_info_product_id"
					+ " having count(*)>=5) ";
			PreparedStatement pst=connection.prepareStatement(sql);
			ResultSet rst=pst.executeQuery();
			while(rst.next()) {
				Product p=new Product();
				p.setProduct_id(rst.getInt(1));
				p.setProduct_type_id(rst.getInt(2));
				p.setProduct_name(rst.getString(3));
				p.setProduct_price(rst.getFloat(4));
				p.setProduct_vip_price(rst.getFloat(5));
				p.setProduct_stock(rst.getInt(6));
				p.setProduct_format(rst.getString(7));
				p.setProduct_statement(rst.getString(8));
				result.add(p);
			}
			pst.close();
			rst.close();
			return result;
		}catch(SQLException ex){
			ex.printStackTrace();
			throw new DbException(ex);
		}
		finally {
			if(connection!=null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
	}
	public void addproduct(Product product) throws BaseException{
		Connection connection=null;
		int max=1;
		try {
			connection=DBUtil.getConnection();
			String sql="select max(Product_id) from product";
			PreparedStatement pst=connection.prepareStatement(sql);
			ResultSet rst=pst.executeQuery();
			if(rst.next()) max=max+rst.getInt(1);
			pst.close();
			rst.close();
			sql="insert into product(Product_id,Product_type_id,Product_name,Product_price,Product_vip_price,Product_stock,Product_format,Product_statement)"
					+ " values(?,?,?,?,?,?,?,?)";
			pst=connection.prepareStatement(sql);
			pst.setInt(1, max);
			pst.setInt(2, product.getProduct_type_id() );
			pst.setString(3, product.getProduct_name());
			pst.setFloat(4, product.getProduct_price());
			pst.setFloat(5, product.getProduct_vip_price());
			pst.setInt(6, product.getProduct_stock());
			pst.setString(7, product.getProduct_format());
			pst.setString(8, product.getProduct_statement());
			pst.execute();
		}catch(SQLException ex){
			ex.printStackTrace();
			throw new DbException(ex);
		}
		finally {
			if(connection!=null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
	}
	public List<Product> load_on_product() throws BaseException{
		List<Product> result=new ArrayList<>();
		Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="select * from product where Product_stock>0 and Product_statement='In the sale' order by Product_type_id ";
			PreparedStatement pst=connection.prepareStatement(sql);
			ResultSet rst=pst.executeQuery();
			while(rst.next()) {
				Product p=new Product();
				p.setProduct_id(rst.getInt(1));
				p.setProduct_type_id(rst.getInt(2));
				p.setProduct_name(rst.getString(3));
				p.setProduct_price(rst.getFloat(4));
				p.setProduct_vip_price(rst.getFloat(5));
				p.setProduct_stock(rst.getInt(6));
				p.setProduct_format(rst.getString(7));
				p.setProduct_statement(rst.getString(8));
				result.add(p);
			}
			pst.close();
			rst.close();
			return result;
		}catch(SQLException ex){
			ex.printStackTrace();
			throw new DbException(ex);
		}
		finally {
			if(connection!=null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
	}
	public void modifyroduct(Product product) throws BaseException{
		Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="update product set Product_name=?,Product_price=?,Product_vip_price=?,Product_stock=?,Product_format=?"
					+ ",Product_statement=? where Product_id=?";
			PreparedStatement pst=connection.prepareStatement(sql);
			pst.setString(1, product.getProduct_name());
			pst.setFloat(2, product.getProduct_price());
			pst.setFloat(3, product.getProduct_vip_price());
			pst.setInt(4, product.getProduct_stock());
			pst.setString(5, product.getProduct_format());
			pst.setString(6, product.getProduct_statement());
			pst.setInt(7, product.getProduct_id());
			pst.execute();
		}catch(SQLException ex){
			ex.printStackTrace();
			throw new DbException(ex);
		}
		finally {
			if(connection!=null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
	}
	public Product anyProduct(int n) throws BaseException{
		Connection connection=null;
		Product product;
		try {
			connection=DBUtil.getConnection();
			String sql="select * from product where Product_id=? and Product_statement=?";
			PreparedStatement pst=connection.prepareStatement(sql);
			pst.setInt(1, n);
			pst.setString(2, "In the sale");
			ResultSet rst=pst.executeQuery();
			if(rst.next()) {
				product=new Product();
				product.setProduct_id(n);
				product.setProduct_type_id(rst.getInt(2));
				product.setProduct_name(rst.getString(3));
				product.setProduct_price(rst.getFloat(4));
				product.setProduct_vip_price(rst.getFloat(5));
				product.setProduct_stock(rst.getInt(6));
				product.setProduct_format(rst.getString(7));
				product.setProduct_statement(rst.getString(8));
			}
			else {
				throw new BussinessException("此商品不在售卖中");
			}
			return product;
		}catch(SQLException ex){
			ex.printStackTrace();
			throw new DbException(ex);
		}
		finally {
			if(connection!=null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
	}
}
