package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jboss.jandex.ThrowsTypeTarget;

import javassist.bytecode.SignatureAttribute.NestedClassType;
import model.Coupon;
import model.Product;
import model.User;
import util.BaseException;
import util.BussinessException;
import util.DBUtil;
import util.DbException;

public class CouponManager {
	public List<Coupon> loadallCoupons() throws BaseException{//用户用的
		List<Coupon> result=new ArrayList<>();
		Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="select * from coupon where User_id is null and now()<Coupon_end_time "
					+ " and Coupon_used_time is null";
			PreparedStatement pst=connection.prepareStatement(sql);
			ResultSet rst=pst.executeQuery();
			while(rst.next()) {
				Coupon p=new Coupon();
				p.setCoupon_id(rst.getInt(1));
				p.setCoupon_Userid(rst.getString(2));
				p.setCoupon_statement(rst.getString(3));
				p.setCoupon_between(rst.getFloat(4));
				p.setCoupon_sub(rst.getFloat(5));
				p.setCoupon_begin(rst.getTimestamp(6));
				p.setCoupon_end(rst.getTimestamp(7));
				p.setCoupon_used(rst.getTimestamp(8));
				System.out.println(p);
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
	public List<Coupon> loadmyCoupons(float sum) throws BaseException{
		List<Coupon> result=new ArrayList<>();
		Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="select * from coupon where User_id=? and now()<Coupon_end_time and now()>Coupon_start_time"
					+ " and coupon_used_time is null and Coupon_between<=?";
			PreparedStatement pst=connection.prepareStatement(sql);
			pst.setString(1, User.currentLoginUser.getUser_id());
			pst.setFloat(2, sum);
			ResultSet rst=pst.executeQuery();
			while(rst.next()) {
				Coupon p=new Coupon();
				p.setCoupon_id(rst.getInt(1));
				p.setCoupon_Userid(rst.getString(2));
				p.setCoupon_statement(rst.getString(3));
				p.setCoupon_between(rst.getFloat(4));
				p.setCoupon_sub(rst.getFloat(5));
				p.setCoupon_begin(rst.getTimestamp(6));
				p.setCoupon_end(rst.getTimestamp(7));
				p.setCoupon_used(rst.getTimestamp(8));
				System.out.println(p);
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
	public Coupon usemycoupon(int coupon_id,float sum) throws BaseException{
		Connection connection=null;
		Coupon coupon=null;
		try {
			connection=DBUtil.getConnection();
			String sql="select * from coupon where coupon_id=?  and Coupon_between<=?";
			PreparedStatement pst=connection.prepareStatement(sql);
			pst.setInt(1, coupon_id);
			pst.setFloat(2, sum);
			ResultSet rst=pst.executeQuery();
			if(!rst.next()) throw new BussinessException("不存在此优惠券");
			else {
				coupon=new Coupon();
				coupon.setCoupon_id(rst.getInt(1));
				coupon.setCoupon_Userid(rst.getString(2));
				coupon.setCoupon_statement(rst.getString(3));
				coupon.setCoupon_between(rst.getFloat(4));
				coupon.setCoupon_sub(rst.getFloat(5));
				coupon.setCoupon_begin(rst.getTimestamp(6));
				coupon.setCoupon_end(rst.getTimestamp(7));
				coupon.setCoupon_used(rst.getTimestamp(8));
			}
			rst.close();
			sql="update coupon set coupon_used_time=now() where coupon_id=?";
			pst=connection.prepareStatement(sql);
			pst.setInt(1, coupon_id);
			pst.execute();
			return coupon;
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
	public void receivecoupon(int couid) throws BaseException{
		Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="select * from coupon where coupon_id=?";
			PreparedStatement pst=connection.prepareStatement(sql);
			pst.setInt(1, couid);
			ResultSet rst=pst.executeQuery();
			if(!rst.next()) {
				throw new BussinessException("不存在此优惠券");
			}
			pst.close();
			rst.close();
			sql="update coupon set User_id=? where Coupon_id=?";
			pst=connection.prepareStatement(sql);
			pst.setString(1, User.currentLoginUser.getUser_id());
			pst.setInt(2, couid);
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
	public List<Coupon> loadmyCoupons() throws BaseException{
		List<Coupon> result=new ArrayList<>();
		Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="select * from coupon where User_id=? and now()<Coupon_end_time"
					+ " and coupon_used_time is null ";
			PreparedStatement pst=connection.prepareStatement(sql);
			pst.setString(1, User.currentLoginUser.getUser_id());
			ResultSet rst=pst.executeQuery();
			while(rst.next()) {
				Coupon p=new Coupon();
				p.setCoupon_id(rst.getInt(1));
				p.setCoupon_Userid(rst.getString(2));
				p.setCoupon_statement(rst.getString(3));
				p.setCoupon_between(rst.getFloat(4));
				p.setCoupon_sub(rst.getFloat(5));
				p.setCoupon_begin(rst.getTimestamp(6));
				p.setCoupon_end(rst.getTimestamp(7));
				p.setCoupon_used(rst.getTimestamp(8));
				System.out.println(p);
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
	public List<Coupon> loadallCoupons_sys() throws BaseException{//管理员用的
		List<Coupon> result=new ArrayList<>();
		Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="select * from coupon where User_id is null";
			PreparedStatement pst=connection.prepareStatement(sql);
			ResultSet rst=pst.executeQuery();
			while(rst.next()) {
				Coupon p=new Coupon();
				p.setCoupon_id(rst.getInt(1));
				p.setCoupon_Userid(rst.getString(2));
				p.setCoupon_statement(rst.getString(3));
				p.setCoupon_between(rst.getFloat(4));
				p.setCoupon_sub(rst.getFloat(5));
				p.setCoupon_begin(rst.getTimestamp(6));
				p.setCoupon_end(rst.getTimestamp(7));
				p.setCoupon_used(rst.getTimestamp(8));
				System.out.println(p);
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
	public void addcoupon(Coupon coupon) throws BaseException{
		Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="insert into coupon(Coupon_statement,Coupon_between,Coupon_sub,Coupon_start_time,Coupon_end_time)"
					+ " values(?,?,?,?,?)";
			PreparedStatement pst=connection.prepareStatement(sql);
			pst.setString(1, coupon.getCoupon_statement());
			pst.setFloat(2, coupon.getCoupon_between());
			pst.setFloat(3, coupon.getCoupon_sub());
			pst.setTimestamp(4, coupon.getCoupon_begin());
			pst.setTimestamp(5, coupon.getCoupon_end());
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
	public void deletecoupon(int n) throws BaseException{
		Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="select * from coupon where Coupon_id=?";
			PreparedStatement pst=connection.prepareStatement(sql);
			pst.setInt(1, n);
			ResultSet rst=pst.executeQuery();
			if(rst.next()) {
				if(rst.getString(2)!=null) throw new BussinessException("此优惠券已经用户领取，不能删除");
			}
			else {
				throw new BussinessException("不存在此优惠券");
			}
			rst.close();
			pst.close();
			sql="delete from coupon where Coupon_id=?";
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
