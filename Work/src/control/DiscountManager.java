package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Address;
import model.Discount_time;
import model.User;
import util.BussinessException;
import util.DBUtil;
import util.DbException;
import util.*;
public class DiscountManager {
	public Discount_time getdistcount(int count,int product_id) throws BaseException {
		Discount_time discount_time=new Discount_time();
		Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="select * from distcount_time where Product_id=? and distcount_time_begin_time<now()"
					+ "and distcount_time_end_time>now() and distcount_time_count>0";
			PreparedStatement pst=connection.prepareStatement(sql);
			pst.setInt(1, product_id);
			ResultSet rst=pst.executeQuery();
			if(!rst.next()) return null;
			else {
				discount_time=new Discount_time();
				discount_time.setDistcount_time_id(rst.getInt(1));
				discount_time.setProduct_id(rst.getInt(2));
				discount_time.setDistcount_time_price(rst.getFloat(3));
				discount_time.setDistcount_time_count(rst.getInt(4));
				discount_time.setDistcount_time_begin(rst.getTimestamp(5));
				discount_time.setDistcount_time_end(rst.getTimestamp(6));
			}
			pst.close();
			rst.close();
			sql="update distcount_time set distcount_time_count= distcount_time_count-? where product_id=?";
			pst=connection.prepareStatement(sql);
			pst.setInt(1, count);
			pst.setInt(2, product_id);
			pst.execute();
			return discount_time;
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
