package control;

import java.sql.*;
import java.util.Date;

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
					+ "Oder_info_coupon_id,Oder_info_plantime,Order_info_finishtime,Oder_info_address_id,Oder_info_statement)"
					+ "values(?,?,?,?,?,?,?,?,?,?,?)";
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
			
			Date planDate=new Date(new Date().getTime()+1000*60*60*3);
			java.sql.Timestamp timestamp=new Timestamp(planDate.getTime());
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

}
