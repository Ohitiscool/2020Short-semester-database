package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Discount_time;
import model.Full_discount;
import util.BaseException;
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
	

}
