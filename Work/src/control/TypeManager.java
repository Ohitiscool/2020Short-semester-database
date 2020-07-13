package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Discount_time;
import util.BaseException;
import util.BussinessException;
import util.DBUtil;
import util.DbException;
import model.*;


public class TypeManager {
	public List<Type> loadallTypes () throws BaseException {
		List<Type> list=new ArrayList<>();
		Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="select * from product_type";
			PreparedStatement pst=connection.prepareStatement(sql);
			ResultSet rst=pst.executeQuery();
			while(rst.next()) {
				Type p=new Type();
				p.setType_id(rst.getInt(1));
				p.setType_name(rst.getString(2));
				p.setState(rst.getString(3));
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
	public List<Type> loadallTypes (String name) throws BaseException {
		List<Type> list=new ArrayList<>();
		Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="select * from product_type where Product_type_name like ?";
			PreparedStatement pst=connection.prepareStatement(sql);
			pst.setString(1, "%"+name+"%");
			ResultSet rst=pst.executeQuery();
			while(rst.next()) {
				Type p=new Type();
				p.setType_id(rst.getInt(1));
				p.setType_name(rst.getString(2));
				p.setState(rst.getString(3));
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
	public void addTypes (Type type) throws BaseException {
		Connection connection=null;
		try {
			int max=1;
			connection=DBUtil.getConnection();
			String sql="select max(Product_type_id) from Product_type ";
			PreparedStatement pst=connection.prepareStatement(sql);
			ResultSet rst=pst.executeQuery();
			if(rst.next()) {
				max=max+rst.getInt(1);
			}
			rst.close();
			pst.close();
			sql="insert into product_type(Product_type_id,Product_type_name,Product_type_statement) values(?,?,?)";
			pst=connection.prepareStatement(sql);
			pst.setInt(1, max);
			pst.setString(2, type.getType_name());
			pst.setString(3, type.getState());
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
	public void modify (Type type) throws BaseException {
		Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="select * from product_type where Product_type_id=?";
			PreparedStatement pst=connection.prepareStatement(sql);
			pst.setInt(1, type.getType_id());
			ResultSet rst=pst.executeQuery();
			if(!rst.next()) throw new BussinessException("商品类不存在");
			rst.close();
			pst.close();
			sql="update product_type set Product_type_name=? where Product_type_id=?";
			pst=connection.prepareStatement(sql);
			pst.setString(1, type.getType_name());
			pst.setInt(2, type.getType_id());
			pst.execute();
			pst.close();
			sql="update product_type set Product_type_statement=? where Product_type_id=?";
			pst=connection.prepareStatement(sql);
			pst.setString(1, type.getState());
			pst.setInt(2, type.getType_id());
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
	public List<Type> loaduseTypes (String on) throws BaseException { ///使用的
		List<Type> list=new ArrayList<>();
		Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="select * from product_type where Product_type_statement=?";
			PreparedStatement pst=connection.prepareStatement(sql);
			pst.setString(1, on);
			ResultSet rst=pst.executeQuery();
			while(rst.next()) {
				Type p=new Type();
				p.setType_id(rst.getInt(1));
				p.setType_name(rst.getString(2));
				p.setState(rst.getString(3));
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
	public Type anyType(int n) throws BaseException {
		Connection connection=null;
		Type type=null;
		try {
			connection=DBUtil.getConnection();
			String sql="select * from product_type where Product_type_id=?";
			PreparedStatement pst=connection.prepareStatement(sql);
			pst.setInt(1, n);
			ResultSet rst=pst.executeQuery();
			if(rst.next()) {
				type=new Type();
				type.setType_id(rst.getInt(1));
				type.setType_name(rst.getString(2));
				type.setState(rst.getString(3));
			}
			rst.close();
			pst.close();
			return type;
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
