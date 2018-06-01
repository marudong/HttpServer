package team.zhongli.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import team.ml.mysql.DBHelper;
import team.zhongli.esse.Message;
import team.zhongli.esse.User;

public class MessageDAO {
	private DBHelper helper = new DBHelper();
	/**
	   * 查询全部用户
	   * 
	   * @return
	   * @throws SQLException
	   */
	  public List<Message> queryAllUser()
	  {
	    List<Message> msgList = new ArrayList<Message>();
	 
	    // 获得数据库连接
	    Connection conn;
		try {
			conn = DBHelper.getConnection();
			String sql = "select * from message;";
			ResultSet rs = helper.getRS(conn, sql);
			while (rs.next())
			{
				Message msg = new Message();
				msg.setUser(rs.getString("user"));
				msg.setMessage(rs.getString("message"));
				msg.setStatus(rs.getInt("status"));
				
				msgList.add(msg);
			}
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         return msgList;
	  }
	 
	  /**
	   * 查询单个用户
	   * 
	   * @return
	   * @throws SQLException
	   */
	  public Message queryById(String user)
	  {
	    Message msg = null;
	 
	    Connection conn;
		try {
			conn = DBHelper.getConnection();
			String sql = "" + " select * from message " + " where user= '" + user +"'";
		    ResultSet rs = helper.getRS(conn, sql);
			while (rs.next())
			{
			    msg = new Message();
				msg.setUser(rs.getString("user"));
				msg.setMessage(rs.getString("message"));
				msg.setStatus(rs.getInt("status"));
			}
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return msg;
	  }
	 
	  /**
	   * 添加用户
	   * 
	   * @throws SQLException
	   */
	  public void addMessage(Message msg) throws SQLException
	  {
	    // 获得数据库连接
	    try {
			Connection conn = DBHelper.getConnection();
		    String sql = "insert into message(user,message,status) values('" +msg.getUser() + "','" + msg.getMessage() +"'," + msg.getStatus() +")";
		    
		    helper.DoInsert(conn, sql);
		    conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
	    
	  }
	 
	  /**
	   * 修改用户资料
	 * @throws Exception 
	   */
	  public void updateMessage(Message msg) throws Exception
	  {
	    Connection conn = DBHelper.getConnection();
	 
	    String sql = "update message set message=?,status=? where user=?";
	 
	    PreparedStatement ptmt = conn.prepareStatement(sql);
	 
	    ptmt.setString(1, msg.getMessage());
	    ptmt.setInt(2, msg.getStatus());
 
	    ptmt.execute();
		conn.close();
	  }
	 
	  /**
	   * 删除用户
	 * @throws Exception 
	   */
	  public void deleteMessage(String user) throws Exception
	  {
	    Connection conn = DBHelper.getConnection();
	 
	    String sql = "delete from message where user=?";
	 
	    PreparedStatement ptmt = conn.prepareStatement(sql);
	 
	    ptmt.setString(1, user);
	 
	    ptmt.execute();
		conn.close();
	  }
}
