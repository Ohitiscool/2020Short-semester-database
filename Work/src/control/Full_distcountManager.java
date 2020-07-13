package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jboss.jandex.ThrowsTypeTarget;

import model.Discount_time;
import model.Full_discount;
import util.BaseException;
import util.BussinessException;
import util.DBUtil;
import util.DbException;

public class Full_distcountManager {
	public Full_discount getfulldistcount(int product_id,float sum) throws BaseException {
		Full_discount full_discount=null;
		Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="select * from full_distcount where full_distcount_product_id=? and full_distcount_begin_time<now()"
					+ "and full_distcount_end_time>now() and full_distcount_between<=? ";
			System.out.println(sum+"fullllllcount");
			PreparedStatement pst=connection.prepareStatement(sql);
			pst.setInt(1, product_id);
			pst.setFloat(2, sum);
			ResultSet rst=pst.executeQuery();
			if(!rst.next()) return full_discount;
			else {
				full_discount=new Full_discount();
				full_discount.setFull_distcount_id(rst.getInt(1));;
				full_discount.setFull_distcount_product_id(rst.getInt(2));
				full_discount.setFull_distcount_base(rst.getFloat(3));
				full_discount.setFull_distcount_discount(rst.getFloat(4));
				full_discount.setFull_distcount_begin_id(rst.getTimestamp(5));
				full_discount.setFull_distcount_end_id(rst.getTimestamp(6));
				full_discount.setFull_distcount_statement(rst.getString(7));
			}
			pst.close();
			rst.close();
			return full_discount;
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
	public java.util.List<Full_discount> loadallfull() throws BaseException {
		List<Full_discount> list=new ArrayList<Full_discount>();
		Full_discount full_discount;
		Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="select * from full_distcount where full_distcount_begin_time<now()"
					+ " and full_distcount_end_time>now() order by full_distcount_product_id";
			PreparedStatement pst=connection.prepareStatement(sql);
			ResultSet rst=pst.executeQuery();
			while(rst.next()) {
				full_discount=new Full_discount();
				full_discount.setFull_distcount_id(rst.getInt(1));;
				full_discount.setFull_distcount_product_id(rst.getInt(2));
				full_discount.setFull_distcount_base(rst.getFloat(3));
				full_discount.setFull_distcount_discount(rst.getFloat(4));
				full_discount.setFull_distcount_begin_id(rst.getTimestamp(5));
				full_discount.setFull_distcount_end_id(rst.getTimestamp(6));
				full_discount.setFull_distcount_statement(rst.getString(7));
				list.add(full_discount);
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
	public java.util.List<Full_discount> loadallfull(int x) throws BaseException {
		List<Full_discount> list=new ArrayList<Full_discount>();
		Full_discount full_discount;
		Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="select * from full_distcount where full_distcount_begin_time<now()"
					+ " and full_distcount_end_time>now() and full_distcount_product_id=? order by full_distcount_product_id";
			PreparedStatement pst=connection.prepareStatement(sql);
			pst.setInt(1, x);
			ResultSet rst=pst.executeQuery();
			while(rst.next()) {
				full_discount=new Full_discount();
				full_discount.setFull_distcount_id(rst.getInt(1));;
				full_discount.setFull_distcount_product_id(rst.getInt(2));
				full_discount.setFull_distcount_base(rst.getFloat(3));
				full_discount.setFull_distcount_discount(rst.getFloat(4));
				full_discount.setFull_distcount_begin_id(rst.getTimestamp(5));
				full_discount.setFull_distcount_end_id(rst.getTimestamp(6));
				full_discount.setFull_distcount_statement(rst.getString(7));
				list.add(full_discount);
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
	public java.util.List<Full_discount> loadallfull_sys() throws BaseException {
		List<Full_discount> list=new ArrayList<Full_discount>();
		Full_discount full_discount;
		Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="select * from full_distcount";
			PreparedStatement pst=connection.prepareStatement(sql);
			ResultSet rst=pst.executeQuery();
			while(rst.next()) {
				full_discount=new Full_discount();
				full_discount.setFull_distcount_id(rst.getInt(1));;
				full_discount.setFull_distcount_product_id(rst.getInt(2));
				full_discount.setFull_distcount_base(rst.getFloat(3));
				full_discount.setFull_distcount_discount(rst.getFloat(4));
				full_discount.setFull_distcount_begin_id(rst.getTimestamp(5));
				full_discount.setFull_distcount_end_id(rst.getTimestamp(6));
				full_discount.setFull_distcount_statement(rst.getString(7));
				list.add(full_discount);
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
	public java.util.List<Full_discount> loadallfull_sys(int n) throws BaseException {
		List<Full_discount> list=new ArrayList<Full_discount>();
		Full_discount full_discount;
		Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="select * from full_distcount where full_distcount_product_id=?";
			PreparedStatement pst=connection.prepareStatement(sql);
			pst.setInt(1, n);
			ResultSet rst=pst.executeQuery();
			while(rst.next()) {
				full_discount=new Full_discount();
				full_discount.setFull_distcount_id(rst.getInt(1));;
				full_discount.setFull_distcount_product_id(rst.getInt(2));
				full_discount.setFull_distcount_base(rst.getFloat(3));
				full_discount.setFull_distcount_discount(rst.getFloat(4));
				full_discount.setFull_distcount_begin_id(rst.getTimestamp(5));
				full_discount.setFull_distcount_end_id(rst.getTimestamp(6));
				full_discount.setFull_distcount_statement(rst.getString(7));
				list.add(full_discount);
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
	public void addfulldistcount(Full_discount full_discount,int n) throws BaseException {
		Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="select * from full_distcount where full_distcount_product_id=? and now()>full_distcount_begin_time"
					+ " and now()<full_distcount_end_time";
			PreparedStatement pst=connection.prepareStatement(sql);
			pst.setInt(1, n);
			ResultSet rst=pst.executeQuery();
			if(rst.next()) throw new BussinessException("此商品已存在满减优惠");
			pst.close();
			rst.close();
			sql="insert into full_distcount(full_distcount_product_id,full_distcount_between,full_distcount_disctount"
					+ ",full_distcount_begin_time,full_distcount_end_time,full_distcount_STATEMENT) values(?,?,?,?,?,?)";
			pst=connection.prepareStatement(sql);
			pst.setInt(1, n);
			pst.setFloat(2, full_discount.getFull_distcount_base());
			pst.setFloat(3, full_discount.getFull_distcount_discount());
			pst.setTimestamp(4, full_discount.getFull_distcount_begin_id());
			pst.setTimestamp(5, full_discount.getFull_distcount_end_id());
			pst.setString(6, full_discount.getFull_distcount_statement());
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
	public void deletefulldistcount(int n) throws BaseException {
		Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="select * from full_distcount where full_distcount_id=? and now()>full_distcount_end_time";
			PreparedStatement pst=connection.prepareStatement(sql);
			pst.setInt(1, n);
			ResultSet rst=pst.executeQuery();
			if(rst.next()) throw new BussinessException("此满减活动时间已过，无法终止");
			rst.close();
			pst.close();
			sql="update full_distcount set full_distcount_end_time=now() where full_distcount_id=?";
			pst=connection.prepareStatement(sql);
			pst.setInt(1, n);
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
	

}
