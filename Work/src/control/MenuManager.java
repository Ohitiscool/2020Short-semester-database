package control;
import control.*;
import model.*;
import util.BaseException;
import util.BussinessException;
import util.DBUtil;
import util.DbException;

import java.util.*;

import com.mysql.jdbc.Connection;

import java.lang.*;
import java.net.ConnectException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.*;
public class MenuManager {
	public List<Menu> loadallMenus() throws BaseException {
		List<Menu> list=new ArrayList<Menu>();
		Discount_time discount_time=new Discount_time();
		java.sql.Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="select * from menu order by Menu_id";
			PreparedStatement pst=connection.prepareStatement(sql);
			ResultSet rst=pst.executeQuery();
			while(rst.next()) {
				Menu p=new Menu();
				p.setId(rst.getInt(1));
				p.setName(rst.getString(2));
				list.add(p);
			}
			rst.close();
			pst.close();
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
	public List<Menu> loadallMenus(String name) throws BaseException {
		List<Menu> list=new ArrayList<Menu>();
		Discount_time discount_time=new Discount_time();
		java.sql.Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="select * from menu where Menu_name like ? order by Menu_id";
			PreparedStatement pst=connection.prepareStatement(sql);
			pst.setString(1, "%"+name+"%");
			ResultSet rst=pst.executeQuery();
			while(rst.next()) {
				Menu p=new Menu();
				p.setId(rst.getInt(1));
				p.setName(rst.getString(2));
				list.add(p);
			}
			rst.close();
			pst.close();
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
	public List<Menu_step> loadthisStep(Menu m) throws BaseException {
		List<Menu_step> list=new ArrayList<Menu_step>();
		Discount_time discount_time=new Discount_time();
		java.sql.Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="select * from menu_step where Menu_step_menuid=? order by Menu_step_id";
			PreparedStatement pst=connection.prepareStatement(sql);
			pst.setInt(1, m.getId());
			ResultSet rst=pst.executeQuery();
			while(rst.next()) {
				Menu_step p=new Menu_step();
				p.setMenu_id(rst.getInt(2));
				p.setStep_id(rst.getInt(1));
				p.setProduct(rst.getString(3));
				p.setStatement(rst.getString(4));
				list.add(p);
			}
			rst.close();
			pst.close();
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
	public void addmenu(String name) throws BaseException {
		Discount_time discount_time=new Discount_time();
		java.sql.Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="select * from menu where Menu_name=?";
			PreparedStatement pst=connection.prepareStatement(sql);
			pst.setString(1, name);
			ResultSet rst=pst.executeQuery();
			if(rst.next()) throw new BussinessException("已经存在此菜单，无法添加");
			rst.close();
			pst.close();
			sql="insert into menu(Menu_name) values(?)";
			pst=connection.prepareStatement(sql);
			pst.setString(1, name);
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
	public void addstep(Menu_step step) throws BaseException {
		java.sql.Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="insert into menu_step(Menu_step_menuid,Menu_step_product,Menu_statement) "
					+ "values(?,?,?)";
			PreparedStatement pst=connection.prepareStatement(sql);
			pst.setInt(1, step.getMenu_id());
			pst.setString(2, step.getProduct());
			pst.setString(3, step.getStatement());
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
	public void deletemenu(Menu m) throws BaseException {
		Discount_time discount_time=new Discount_time();
		java.sql.Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="delete from menu_step where Menu_step_menuid=?";
			PreparedStatement pst=connection.prepareStatement(sql);
			pst.setInt(1, m.getId());
			pst.execute();
			pst.close();
			sql="delete from menu where menu_id=?";
			pst=connection.prepareStatement(sql);
			pst.setInt(1, m.getId());
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
	public void deletestep(int n) throws BaseException {
		Discount_time discount_time=new Discount_time();
		java.sql.Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="delete from menu_step where Menu_step_id=?";
			PreparedStatement pst=connection.prepareStatement(sql);
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
