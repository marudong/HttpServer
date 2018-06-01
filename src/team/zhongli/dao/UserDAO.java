package team.zhongli.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import team.ml.mysql.DBHelper;
import team.zhongli.esse.User;

public class UserDAO {
	private DBHelper helper = new DBHelper();
	/**
	   * ��ѯȫ���û�
	   * 
	   * @return
	   * @throws SQLException
	   */
	  public List<User> queryAllUser()
	  {
	    List<User> userList = new ArrayList<User>();
	 
	    // ������ݿ�����
	    Connection conn;
		try {
			conn = DBHelper.getConnection();
			String sql = "select * from user;";
			ResultSet rs = helper.getRS(conn, sql);
			while (rs.next())
			{
				User user = new User();
				user.setPhone(rs.getString("phone"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setDevice(rs.getString("device"));
				user.setGroupid(rs.getInt("groupid"));
				user.setStatus(rs.getInt("status"));
				
				userList.add(user);
			}
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         return userList;
	  }
	  
	  /**
	   * ��¼��֤
	   * 
	   * @return
	   * @throws SQLException
	   */
	  public User checkLogin(String phone, String password)
	  {
	    User user = null;
	 
	    Connection conn;
		try {
			conn = DBHelper.getConnection();
			String sql = "" + " select * from user " + " where phone= '" + phone +"' and password= '" + password +"'";
		    ResultSet rs = helper.getRS(conn, sql);
			while (rs.next())
			{
				user = new User();
				user.setPhone(rs.getString("phone"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setDevice(rs.getString("device"));
				user.setGroupid(rs.getInt("groupid"));
				user.setStatus(rs.getInt("status"));
			}
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;
	  }
	 
	  /**
	   * ��ѯ�����û�
	   * 
	   * @return
	   * @throws SQLException
	   */
	  public User queryById(String phone)
	  {
	    User user = null;
	 
	    Connection conn;
		try {
			conn = DBHelper.getConnection();
			String sql = "" + " select * from user " + " where phone= '" + phone +"'";
		    ResultSet rs = helper.getRS(conn, sql);
			while (rs.next())
			{
				user = new User();
				user.setPhone(rs.getString("phone"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setDevice(rs.getString("device"));
				user.setGroupid(rs.getInt("groupid"));
				user.setStatus(rs.getInt("status"));
			}
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;
	  }
	 
	  /**
	   * ����û�
	   * 
	   * @throws SQLException
	   */
	  public void addUser(User user) throws SQLException
	  {
	    // ������ݿ�����
	    try {
			Connection conn = DBHelper.getConnection();
		    String sql = "insert into user(phone,name,password,device,groupid,status) values('" +user.getPhone() + "','" + user.getName() +"','" + user.getPassword() +"','"+ user.getDevice() +"',"+ user.getGroupid() +","+ user.getStatus() +")";
		    
		    helper.DoInsert(conn, sql);
		    conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
	    
	  }
	 
	  /**
	   * �޸��û�����
	 * @throws Exception 
	   */
	  public void updateUser(User user) throws Exception
	  {
	    Connection conn = DBHelper.getConnection();
	 
	    String sql = "update user set name=?,password=?,device=?,groupid=?,status=? where phone=?";
	 
	    PreparedStatement ptmt = conn.prepareStatement(sql);
	 
	    ptmt.setString(1, user.getName());
	    ptmt.setString(2, user.getPassword());
	    ptmt.setString(3, user.getDevice());
	    ptmt.setInt(4, user.getGroupid());
	    ptmt.setInt(5, user.getStatus());
	 
	    ptmt.execute();
		conn.close();
	  }
	 
	  /**
	   * ɾ���û�
	 * @throws Exception 
	   */
	  public void deleteUser(String phone) throws Exception
	  {
	    Connection conn = DBHelper.getConnection();
	 
	    String sql = "delete from user where phone=?";
	 
	    PreparedStatement ptmt = conn.prepareStatement(sql);
	 
	    ptmt.setString(1, phone);
	 
	    ptmt.execute();
		conn.close();
	  }
}
