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
			String sql="select * from product where Product_stock>0 order by Product_type_id";
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
}
