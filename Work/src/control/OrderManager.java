package control;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.*;
import com.mysql.fabric.xmlrpc.base.Array;

import start.*;
import util.*;
import model.*;
import control.*;


public class OrderManager {
	public void addorder(Coupon coupon,Address address,int product_id,int count,float sum1,float sum2,Discount_time discount_time,Full_discount full_discount) throws BaseException{
		Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="insert order_product(Order_info_product_id,Oder_info_User_id,Order_info_count,Order_info_discount_time_id,Oder_info_begin_price,Oder_info_end_price,"
					+ "Oder_info_coupon_id,Oder_info_fulldiscount_ID,Oder_info_plantime,Order_info_finishtime,Oder_info_address_id,Oder_info_statement,Order_info_evatime)"
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,1)";
			PreparedStatement pst=connection.prepareStatement(sql);
			pst.setInt(1, product_id);
			pst.setString(2, User.currentLoginUser.getUser_id());
			pst.setInt(3, count);
			
			if(discount_time!=null) pst.setInt(4, discount_time.getDistcount_time_id());
			else pst.setNull(4, Types.NULL);
			
			pst.setFloat(5, sum1);
			pst.setFloat(6, sum2);
			
			if(coupon!=null) pst.setInt(7, coupon.getCoupon_id());
			else pst.setNull(7, Types.NULL);
			
			if(full_discount!=null) pst.setInt(8, full_discount.getFull_distcount_id());
			else pst.setNull(8, Types.NULL);
			
			Date planDate=new Date(System.currentTimeMillis());
			java.sql.Timestamp timestamp=new Timestamp(planDate.getTime()+1000*60*60*3);
			pst.setTimestamp(9, timestamp);
			pst.setNull(10, Types.NULL);
			pst.setInt(11,address.getAddress_id() );
			pst.setString(12, "Processing");
			
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
	public List<Order_info> loadmyroder() throws BaseException{
		List<Order_info> list=new ArrayList<>();
		Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="select * from order_product where Oder_info_User_id=? order by Order_info_product_id";
			PreparedStatement pst=connection.prepareStatement(sql);
			pst.setString( 1, User.currentLoginUser.getUser_id());
			ResultSet rst=pst.executeQuery();
			while(rst.next()) {
				Order_info p=new Order_info();
				p.setOrder_info_Order_id(rst.getInt(1));
				p.setOrder_info_product_id(rst.getInt(2));
				p.setOrder_info_user_id(rst.getString(3));
				p.setOrder_info_count(rst.getInt(4));
				p.setOrder_info_discount_time_id(rst.getInt(5));
				p.setOrder_info_begin_price(rst.getFloat(6));
				p.setOrder_info_end_price(rst.getFloat(7));
				p.setOrder_info_coupon_id(rst.getInt(8));
				p.setOder_info_fulldiscount_ID(rst.getInt(9));
				p.setOrder_info_Order_planTime(rst.getTimestamp(10));
				p.setOrder_info_order_finishTime(rst.getTimestamp(11));
				p.setOder_info_address_id(rst.getInt(12));
				p.setOder_info_statement(rst.getString(13));
				list.add(p);
			}
			return list;
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
	public List<Order_info> loadmyroder(int n) throws BaseException{
		List<Order_info> list=new ArrayList<>();
		Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="select * from order_product where Oder_info_User_id=? and Order_info_product_id=? order by Order_info_product_id";
			PreparedStatement pst=connection.prepareStatement(sql);
			pst.setString( 1, User.currentLoginUser.getUser_id());
			pst.setInt(2, n);
			ResultSet rst=pst.executeQuery();
			while(rst.next()) {
				Order_info p=new Order_info();
				p.setOrder_info_Order_id(rst.getInt(1));
				p.setOrder_info_product_id(rst.getInt(2));
				p.setOrder_info_user_id(rst.getString(3));
				p.setOrder_info_count(rst.getInt(4));
				p.setOrder_info_discount_time_id(rst.getInt(5));
				p.setOrder_info_begin_price(rst.getFloat(6));
				p.setOrder_info_end_price(rst.getFloat(7));
				p.setOrder_info_coupon_id(rst.getInt(8));
				p.setOder_info_fulldiscount_ID(rst.getInt(9));
				p.setOrder_info_Order_planTime(rst.getTimestamp(10));
				p.setOrder_info_order_finishTime(rst.getTimestamp(11));
				p.setOder_info_address_id(rst.getInt(12));
				p.setOder_info_statement(rst.getString(13));
				list.add(p);
			}
			return list;
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
		public List<Order_info> loadallroder() throws BaseException{
			List<Order_info> list=new ArrayList<>();
			Connection connection=null;
			try {
				connection=DBUtil.getConnection();
				String sql="select * from order_product ";
				PreparedStatement pst=connection.prepareStatement(sql);
				ResultSet rst=pst.executeQuery();
				while(rst.next()) {
					Order_info p=new Order_info();
					p.setOrder_info_Order_id(rst.getInt(1));
					p.setOrder_info_product_id(rst.getInt(2));
					p.setOrder_info_user_id(rst.getString(3));
					p.setOrder_info_count(rst.getInt(4));
					p.setOrder_info_discount_time_id(rst.getInt(5));
					p.setOrder_info_begin_price(rst.getFloat(6));
					p.setOrder_info_end_price(rst.getFloat(7));
					p.setOrder_info_coupon_id(rst.getInt(8));
					p.setOder_info_fulldiscount_ID(rst.getInt(9));
					p.setOrder_info_Order_planTime(rst.getTimestamp(10));
					p.setOrder_info_order_finishTime(rst.getTimestamp(11));
					p.setOder_info_address_id(rst.getInt(12));
					p.setOder_info_statement(rst.getString(13));
					list.add(p);
					System.out.println(p.getOrder_info_Order_id()+"   "+p.getOder_info_fulldiscount_ID()+"    "+p.getOrder_info_order_finishTime());
				}
				
				return list;
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
		public void evaluate_Order(int n,String evaluation,int product_id,int level) throws BaseException{
			Connection connection=null;
			try {
				connection=DBUtil.getConnection();
				String sql="select * from order_product where Oder_info_Order_id=?";
				PreparedStatement pst=connection.prepareStatement(sql);
				pst.setInt(1, n);
				ResultSet rst=pst.executeQuery();
				if(!rst.next()) throw new BussinessException("不存在此订单");
				else {
					if(rst.getTime(11)==null) throw new BussinessException("订单未完成，无法评价");
					if("Refund".equals(rst.getString(13))) throw new BussinessException("订单已经退款，无法评价");
					if(rst.getInt(14)==0) throw new BussinessException("此订单已评价过");
				}
				pst.close();
				rst.close();
				sql="insert into product_evaluation(Product_Evaluation_user_id,Product_Evaluation_product_id,Product_Evaluation_time,"
						+ "Product_Evaluation_level,Product_Evaluation_imag) values(?,?,now(),?,?)";
				pst=connection.prepareStatement(sql);
				pst.setString(1, User.currentLoginUser.getUser_id());
				pst.setInt(2, product_id);
				pst.setInt(3,level);
				pst.setString(4,evaluation);
				pst.execute();
				pst.close();
				sql="update order_product set Order_info_evatime=0 where Oder_info_Order_id=?";
				pst=connection.prepareStatement(sql);
				pst.setInt(1, n);
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
		public List<Product_eva> load_produ_Evas() throws BaseException{
			List<Product_eva> list=new ArrayList<>();
			Connection connection=null;
			try {
				connection=DBUtil.getConnection();
				String sql="select * from product_evaluation order by Product_Evaluation_product_id";
				PreparedStatement pst=connection.prepareStatement(sql);
				ResultSet rst=pst.executeQuery();
				while(rst.next()) {
					Product_eva p=new Product_eva();
					p.setProduct_Evaluation_id(rst.getInt(1));
					p.setProduct_Evaluation_user_id(rst.getString(2));
					p.setProduct_Evaluation_product_id(rst.getInt(3));
					p.setProduct_Evaluation_time(rst.getTimestamp(4));
					p.setProduct_Evaluation_level(rst.getInt(5));
					p.setProduct_Evaluation_imag(rst.getString(6));
					list.add(p);					
				}
				pst.close();
				rst.close();
				return list;
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
		public List<Product_eva> load_produ_Evas(int n) throws BaseException{
			List<Product_eva> list=new ArrayList<>();
			Connection connection=null;
			try {
				connection=DBUtil.getConnection();
				String sql="select * from product_evaluation where Product_Evaluation_product_id=? order by Product_Evaluation_product_id";
				PreparedStatement pst=connection.prepareStatement(sql);
				pst.setInt(1, n);
				ResultSet rst=pst.executeQuery();
				while(rst.next()) {
					Product_eva p=new Product_eva();
					p.setProduct_Evaluation_id(rst.getInt(1));
					p.setProduct_Evaluation_user_id(rst.getString(2));
					p.setProduct_Evaluation_product_id(rst.getInt(3));
					p.setProduct_Evaluation_time(rst.getTimestamp(4));
					p.setProduct_Evaluation_level(rst.getInt(5));
					p.setProduct_Evaluation_imag(rst.getString(6));
					list.add(p);					
				}
				pst.close();
				rst.close();
				return list;
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
	public static void main(String[] args) throws BaseException {
		OrderManager orderManager=new OrderManager();
		orderManager.loadallroder();
	}
	public List<Order_info> loadroder() throws BaseException{
		List<Order_info> list=new ArrayList<>();
		Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="select * from order_product ";
			PreparedStatement pst=connection.prepareStatement(sql);
			ResultSet rst=pst.executeQuery();
			while(rst.next()) {
				Order_info p=new Order_info();
				p.setOrder_info_Order_id(rst.getInt(1));
				p.setOrder_info_product_id(rst.getInt(2));
				p.setOrder_info_user_id(rst.getString(3));
				p.setOrder_info_count(rst.getInt(4));
				p.setOrder_info_discount_time_id(rst.getInt(5));
				p.setOrder_info_begin_price(rst.getFloat(6));
				p.setOrder_info_end_price(rst.getFloat(7));
				p.setOrder_info_coupon_id(rst.getInt(8));
				p.setOder_info_fulldiscount_ID(rst.getInt(9));
				p.setOrder_info_Order_planTime(rst.getTimestamp(10));
				p.setOrder_info_order_finishTime(rst.getTimestamp(11));
				p.setOder_info_address_id(rst.getInt(12));
				p.setOder_info_statement(rst.getString(13));
				list.add(p);
			}
			return list;
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
	public List<Order_info> loadroder(int n) throws BaseException{
		List<Order_info> list=new ArrayList<>();
		Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="select * from order_product where Order_info_product_id=?";
			PreparedStatement pst=connection.prepareStatement(sql);
			pst.setInt(1, n);
			ResultSet rst=pst.executeQuery();
			while(rst.next()) {
				Order_info p=new Order_info();
				p.setOrder_info_Order_id(rst.getInt(1));
				p.setOrder_info_product_id(rst.getInt(2));
				p.setOrder_info_user_id(rst.getString(3));
				p.setOrder_info_count(rst.getInt(4));
				p.setOrder_info_discount_time_id(rst.getInt(5));
				p.setOrder_info_begin_price(rst.getFloat(6));
				p.setOrder_info_end_price(rst.getFloat(7));
				p.setOrder_info_coupon_id(rst.getInt(8));
				p.setOder_info_fulldiscount_ID(rst.getInt(9));
				p.setOrder_info_Order_planTime(rst.getTimestamp(10));
				p.setOrder_info_order_finishTime(rst.getTimestamp(11));
				p.setOder_info_address_id(rst.getInt(12));
				p.setOder_info_statement(rst.getString(13));
				list.add(p);
			}
			return list;
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
	public List<Order_info> loadroder(String userid) throws BaseException{
		List<Order_info> list=new ArrayList<>();
		Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="select * from order_product where Oder_info_User_id like ?";
			PreparedStatement pst=connection.prepareStatement(sql);
			pst.setString(1, "%"+userid+"%");
			ResultSet rst=pst.executeQuery();
			while(rst.next()) {
				Order_info p=new Order_info();
				p.setOrder_info_Order_id(rst.getInt(1));
				p.setOrder_info_product_id(rst.getInt(2));
				p.setOrder_info_user_id(rst.getString(3));
				p.setOrder_info_count(rst.getInt(4));
				p.setOrder_info_discount_time_id(rst.getInt(5));
				p.setOrder_info_begin_price(rst.getFloat(6));
				p.setOrder_info_end_price(rst.getFloat(7));
				p.setOrder_info_coupon_id(rst.getInt(8));
				p.setOder_info_fulldiscount_ID(rst.getInt(9));
				p.setOrder_info_Order_planTime(rst.getTimestamp(10));
				p.setOrder_info_order_finishTime(rst.getTimestamp(11));
				p.setOder_info_address_id(rst.getInt(12));
				p.setOder_info_statement(rst.getString(13));
				list.add(p);
			}
			return list;
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
	public List<Order_info> loadroder(int n,String userid) throws BaseException{
		List<Order_info> list=new ArrayList<>();
		Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="select * from order_product where Order_info_product_id=? and Oder_info_User_id like ?";
			PreparedStatement pst=connection.prepareStatement(sql);
			pst.setInt(1, n);
			pst.setString(2, "%"+userid+"%");
			ResultSet rst=pst.executeQuery();
			while(rst.next()) {
				Order_info p=new Order_info();
				p.setOrder_info_Order_id(rst.getInt(1));
				p.setOrder_info_product_id(rst.getInt(2));
				p.setOrder_info_user_id(rst.getString(3));
				p.setOrder_info_count(rst.getInt(4));
				p.setOrder_info_discount_time_id(rst.getInt(5));
				p.setOrder_info_begin_price(rst.getFloat(6));
				p.setOrder_info_end_price(rst.getFloat(7));
				p.setOrder_info_coupon_id(rst.getInt(8));
				p.setOder_info_fulldiscount_ID(rst.getInt(9));
				p.setOrder_info_Order_planTime(rst.getTimestamp(10));
				p.setOrder_info_order_finishTime(rst.getTimestamp(11));
				p.setOder_info_address_id(rst.getInt(12));
				p.setOder_info_statement(rst.getString(13));
				list.add(p);
			}
			return list;
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
	public void finishorder(int n) throws BaseException{
		Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="select * from order_product where Oder_info_Order_id=?";
			PreparedStatement pst=connection.prepareStatement(sql);
			pst.setInt(1, n);
			ResultSet rst=pst.executeQuery();
			if(rst.next()) {
				if(rst.getTimestamp(11)!=null) throw new BussinessException("订单已经完成");
			}
			rst.close();
			pst.close();
			sql="update order_product set Order_info_finishtime=now(),Oder_info_statement='finished' where Oder_info_Order_id=?";
			pst=connection.prepareStatement(sql);
			pst.setInt(1, n);
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

}
