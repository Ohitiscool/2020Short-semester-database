package control;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.ResolverStyle;

import org.hibernate.sql.Select;
import org.jboss.jandex.ThrowsTypeTarget;

import javassist.bytecode.SignatureAttribute.NestedClassType;

import java.util.*;
import model.*;
import util.*;
import start.*;
import java.sql.*;
public class UserManager {
	public void reg(String userid,String pwd,String username,String tel,String email ,String city,String sex) throws BaseException{
		if(userid.length()>20||"".equals(userid)) {
			throw new BussinessException("Please enter 1-20 account");
		}
		if(pwd.length()>20||"".equals(pwd)) {
			throw new BussinessException("Please enter 1-20 passwd");
		}
		Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="select * from userlist where User_id=?";
			PreparedStatement pst=connection.prepareStatement(sql);
			pst.setString(1, userid);
			ResultSet rst=pst.executeQuery();
			if(rst.next()) throw new BussinessException("账号已存在");
			pst.close();
			rst.close();
			sql="insert into userlist(User_id,User_name,User_sex,User_pwd,User_tel,User_email,User_city,User_reg_time,User_end_time,User_vip,User_sumpay) "
				        	+ "values(?,?,?,?,?,?,?,now(),null,0,0)";
			pst=connection.prepareStatement(sql);
			pst.setString(1, userid);
			pst.setString(2, username);
			pst.setString(3, sex);
			pst.setString(4, pwd);
			pst.setString(5, tel);
			pst.setString(6, email);
			pst.setString(7, city);
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
	public User login(String userid,String pwd) throws BaseException{
		if(userid.length()>20||"".equals(userid)) {
			throw new BussinessException("Please enter 1-20 account");
		}
		if(pwd.length()>20||"".equals(pwd)) {
			throw new BussinessException("Please enter 1-20 passwd");
		}
		Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="select * from userlist where user_id=? and user_pwd=?";
			PreparedStatement pst=connection.prepareStatement(sql);
			pst.setString(1, userid);
			pst.setString(2, pwd);
			ResultSet rst=pst.executeQuery();
			if(!rst.next()) throw new BussinessException("不存在此用户或者密码错误");
			else {
				User user=new User();
				user.setUser_id(userid);
				user.setUser_pwd(pwd);
				user.setUser_sex(rst.getString(3));
				user.setUser_tel(rst.getString(5));
				user.setUser_city(rst.getString(7));
				user.setUser_reg_time(rst.getTimestamp(8));
				if(rst.getTimestamp(9) != null) throw new BussinessException("账号已经被注销"); 
				else user.setUser_end_time(null);
				user.setUer_vip(rst.getInt(10));
				user.setSumpay(rst.getFloat(11));
				return user; 
			}
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
	public SystemUser systemUserlogin(String userid,String pwd) throws BaseException{
		if(userid.length()>20||"".equals(userid)) {
			throw new BussinessException("Please enter 1-20 account");
		}
		if(pwd.length()>20||"".equals(pwd)) {
			throw new BussinessException("Please enter 1-20 passwd");
		}
		Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="select * from systemuser where SystemUser_id=? and SystemUser_pwd=?";
			PreparedStatement pst=connection.prepareStatement(sql);
			pst.setString(1, userid);
			pst.setString(2, pwd);
			ResultSet rst=pst.executeQuery();
			if(!rst.next()) throw new BussinessException("不存在此用户或者密码错误");
			else {
				SystemUser systemUser=new SystemUser();
				systemUser.setSystemUser_id(userid);
				systemUser.setSystemUser_pwd(pwd);
				systemUser.setSystemUser_name(rst.getString(2));
				return systemUser; 
			}
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
	public List<Address> loadAddresses() throws BaseException{
		List<Address> result=new ArrayList<>();
		Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="select * from address where User_id=?";
			PreparedStatement pst=connection.prepareStatement(sql);
			pst.setString(1, User.currentLoginUser.getUser_id());
			ResultSet rst=pst.executeQuery();
			while(rst.next()) {
				Address address=new Address();
				address.setAddress_id(rst.getInt(1));
				address.setUser_id(rst.getString(2));
				address.setAddress_province(rst.getString(3));
				address.setAddress_city(rst.getString(4));
				address.setAddress_area(rst.getString(5));
				address.setAddress_person(rst.getString(6));
				address.setAddress_tel(rst.getString(7));
				result.add(address);
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
	public Address add_address(String province,String city,String area,String person,String tel) throws BussinessException {
		if("".equals(province)) throw new BussinessException("省份不能为空");
		if("".equals(city)) throw new BussinessException("城市不能为空");
		if("".equals(area)) throw new BussinessException("地区不能为空");
		if("".equals(person)) throw new BussinessException("联系人姓名不能为空");
		if("".equals(tel)) throw new BussinessException("电话不能为空");
		Address address=new Address();
		Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="insert into address(User_id,address_province,address_city,address_area,address_person,address_tel) values(?,?,?,?,?,?)";
			PreparedStatement pst=connection.prepareStatement(sql);
			pst.setString(1, User.currentLoginUser.getUser_id());
			pst.setString(2, province);
			pst.setString(3, city);
			pst.setString(4, area);
			pst.setString(5, person);
			pst.setString(6, tel);
			pst.execute();
			sql="select max(address_id) from address where user_id=? ";
			pst=connection.prepareStatement(sql);
			pst.setString(1, User.currentLoginUser.getUser_id());
			ResultSet rst=pst.executeQuery();
			if(rst.next()) {
				address.setAddress_id(rst.getInt(1));
				address.setUser_id(User.currentLoginUser.getUser_id());
				address.setAddress_province(province);
				address.setAddress_city(city);
				address.setAddress_area(area);
				address.setAddress_person(person);
				address.setAddress_tel(tel);
			}
			return address;
		}catch(SQLException ex){
			ex.printStackTrace();
			try {
				throw new DbException(ex);
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		return address;
	}
	public void modifyAddress(Address address) throws BaseException{
		if(address.getAddress_id()<=0) throw new BussinessException("地址ID必须是大于0的整数");
		if("".equals(address.getAddress_province())) throw new BussinessException("省份字段不得为空");
		if("".equals(address.getAddress_city()) )throw new BussinessException("城市字段不得为空");
		if("".equals(address.getAddress_area()) )throw new BussinessException("地区字段不得为空");
		if("".equals(address.getAddress_person())) throw new BussinessException("联系人字段不得为空");
		if("".equals(address.getAddress_tel()) )throw new BussinessException("号码字段不得为空");
		Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="select * from address where Address_id=?";
			PreparedStatement pst=connection.prepareStatement(sql);
			pst.setInt(1, address.getAddress_id());
			ResultSet rst=pst.executeQuery();
			if(!rst.next()) throw new BussinessException("不存在此地址");
			rst.close();
			pst.close();
			sql="select * from order_product where Oder_info_address_id=?";
			pst=connection.prepareStatement(sql);
			pst.setInt(1, address.getAddress_id());
			rst=pst.executeQuery();
			if(rst.next()) throw new BussinessException("禁止修改关联订单的地址");
			sql="update address set address_province=?,address_city=?,address_area=?,address_person=?,address_tel=? where address_id=?";
			pst=connection.prepareStatement(sql);
			pst.setString(1, address.getAddress_province());
			pst.setString(2, address.getAddress_city());
			pst.setString(3, address.getAddress_area());
			pst.setString(4, address.getAddress_person());
			pst.setString(5, address.getAddress_tel());
			pst.setInt(6, address.getAddress_id());
			pst.execute();
			}catch(SQLException ex){
			ex.printStackTrace();
			try {
				throw new DbException(ex);
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	public void deleteAddress(int n) throws BaseException {
		Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="select * from address where address_id=?";
			PreparedStatement pst=connection.prepareStatement(sql);
			pst.setInt(1, n);
			ResultSet rst=pst.executeQuery();
			if(!rst.next()) throw new BussinessException("无此地址");
			rst.close();
			pst.close();
			sql="select * from order_product where Oder_info_address_id=?";
			pst=connection.prepareStatement(sql);
			pst.setInt(1, n);
			rst=pst.executeQuery();
			if(rst.next()) throw new BussinessException("禁止删除关联订单的地");
			pst.close();
			rst.close();
			sql="delete from address where address_id=?";
			pst=connection.prepareStatement(sql);
			pst.setInt(1, n);
			pst.execute();
			pst.close();
			}catch(SQLException ex){
			ex.printStackTrace();
			try {
				throw new DbException(ex);
			} catch (DbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	public boolean isvip(User user) throws BaseException{
		Connection connection=null;
		boolean is=false;
		try {
			connection=DBUtil.getConnection();
			user=User.currentLoginUser;
			String sql="select User_vip from Userlist where User_id=?";
			PreparedStatement pst=connection.prepareStatement(sql);
			pst.setString(1, user.getUser_id());
			ResultSet rst=pst.executeQuery();
			if(rst.next()) {
				if(rst.getInt(1)==1) is=true;
			}
 			return is;
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
	public Address anyAddress(int n) throws BaseException{
		Connection connection=null;
	    Address address=new Address();
		try {
			connection=DBUtil.getConnection();
			String sql="select * from address where User_id=?";
			PreparedStatement pst=connection.prepareStatement(sql);
			pst.setString(1, User.currentLoginUser.getUser_id());
			ResultSet rst=pst.executeQuery();
			if(!rst.next()) throw new BussinessException("此用户未位置地址，请设置地址后再购买");
			rst.close();
			pst.close();
			sql="select * from address where address_id=?";
			pst=connection.prepareStatement(sql);
			pst.setInt(1, n);
			rst=pst.executeQuery();
			if(rst.next()) {
				address.setAddress_id(rst.getInt(1));
				address.setUser_id(rst.getString(2));
				address.setAddress_province(rst.getString(3));
				address.setAddress_city(rst.getString(4));
				address.setAddress_area(rst.getString(5));
				address.setAddress_person(rst.getString(6));
				address.setAddress_tel(rst.getString(7));
			}
			//System.out.println("any函数拿到地址"+address.getAddress_id());
 			return address;
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
	public List<SystemUser> loadsystemuser() throws BaseException{
		Connection connection=null;
		List<SystemUser> list=new ArrayList<SystemUser>();
		try {
			connection=DBUtil.getConnection();
			String sql="select * from systemuser";
			PreparedStatement pst=connection.prepareStatement(sql);
			ResultSet rst=pst.executeQuery();
			while(rst.next()) {
				SystemUser s=new SystemUser();
				s.setSystemUser_id(rst.getString(1));
				s.setSystemUser_name(rst.getString(2));
				list.add(s);
			}
			rst.close();
			pst.close();
			return list;
			}
		catch(SQLException ex){
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
	public void regsystemuser(String id,String name,String pwd) throws BaseException{
		Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="select * from systemuser where SystemUser_id=?";
			PreparedStatement pst=connection.prepareStatement(sql);
			pst.setString(1, id);
			ResultSet rst=pst.executeQuery();
			if(rst.next()) {
				throw new BussinessException("已存在此账号");
			}
			rst.close();
			pst.close();
			sql="insert into systemUser(SystemUser_id,SystemUser_name,SystemUser_pwd) values(?,?,?)";
			pst=connection.prepareStatement(sql);
			pst.setString(1, id);
			pst.setString(2, name);
			pst.setString(3, pwd);
			pst.execute();
			pst.close();
			}
		catch(SQLException ex){
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
	public void modifysystemuser(String pwd1,String pwd2) throws BaseException{
		Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="select * from systemuser where SystemUser_id=? and SystemUser_pwd=?";
			PreparedStatement pst=connection.prepareStatement(sql);
			pst.setString(1, SystemUser.currenSystemUser.getSystemUser_id());
			pst.setString(2, pwd1);
			ResultSet rst=pst.executeQuery();
			if(!rst.next()) throw new BussinessException("密码错误");
			rst.close();
			pst.close();
			sql="update systemuser set SystemUser_pwd=? where SystemUser_id=?";
			pst=connection.prepareStatement(sql);
			pst.setString(1, pwd2);
			pst.setString(2, SystemUser.currenSystemUser.getSystemUser_id());
			pst.execute();
			pst.close();
		}
		catch(SQLException ex){
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
	public List<User> loadalluser() throws BaseException{
		Connection connection=null;
		List<User> list=new ArrayList<User>();
		try {
			connection=DBUtil.getConnection();
			String sql="select * from userlist";
			PreparedStatement pst=connection.prepareStatement(sql);
			ResultSet rst=pst.executeQuery();
			while(rst.next()) {
				User s=new User();
				s.setUser_id(rst.getString(1));
				s.setUser_name(rst.getString(2));
				s.setUser_sex(rst.getString(3));
				s.setUser_pwd(rst.getString(4));
				s.setUser_tel(rst.getString(5));
				s.setUser_email(rst.getString(6));
				s.setUser_city(rst.getString(7));
				s.setUser_reg_time(rst.getTimestamp(8));
				s.setUser_end_time(rst.getTimestamp(9));
				s.setUer_vip(rst.getInt(10));
				list.add(s);
			}
			rst.close();
			pst.close();
			return list;
			}
		catch(SQLException ex){
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
	public void deleteuser(String  s) throws BaseException{
		Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="select * from userlist where User_id=?";
			PreparedStatement pst=connection.prepareStatement(sql);
			pst.setString(1, s);
			ResultSet rst=pst.executeQuery();
			if(rst.next()) {
				if(rst.getTimestamp(9)!=null) throw new BussinessException("账户已经注销");
			}
			rst.close();
			pst.close();
			sql="update userlist set User_end_time=now() where User_id=?";
			pst=connection.prepareStatement(sql);
			pst.setString(1, s);
			pst.execute();
			pst.close();
			}
		catch(SQLException ex){
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
	public void vipuser(String  s) throws BaseException{
		Connection connection=null;
		try {
			connection=DBUtil.getConnection();
			String sql="update userlist set User_vip=1 where User_id=?";
			PreparedStatement pst=connection.prepareStatement(sql);
			pst.setString(1, s);
			pst.execute();
			pst.close();
			}
		catch(SQLException ex){
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

