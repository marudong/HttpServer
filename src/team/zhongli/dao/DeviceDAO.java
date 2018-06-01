package team.zhongli.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import team.ml.mysql.DBHelper;
import team.zhongli.esse.Device;
import team.zhongli.esse.User;

public class DeviceDAO {
	private DBHelper helper = new DBHelper();
	/**
	   * 查询全部用户
	   * 
	   * @return
	   * @throws SQLException
	   */
	  public List<Device> queryAllDevice()
	  {
	    List<Device> deviceList = new ArrayList<Device>();
	 
	    // 获得数据库连接
	    Connection conn;
		try {
			conn = DBHelper.getConnection();
			String sql = "select * from device;";
			ResultSet rs = helper.getRS(conn, sql);
			while (rs.next())
			{
				Device device = new Device();
				device.setDeviceip(rs.getString("deviceip"));
				device.setDevicename(rs.getString("name"));
				device.setThretemp(rs.getInt("thretemp"));
				device.setRoomtemp(rs.getInt("roomtemp"));
				device.setCurrent(rs.getString("current"));
				device.setCounter(rs.getString("counter"));
				device.setFire(rs.getInt("fire"));
				device.setFan(rs.getInt("fan"));
				device.setMode(rs.getString("mode"));
				device.setWeek(rs.getString("week"));
				device.setErrinfo(rs.getString("errinfo"));
				
				deviceList.add(device);
			}
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         return deviceList;
	  }
	 
	  /**
	   * 查询单个用户
	   * 
	   * @return
	   * @throws SQLException
	   */
	  public Device queryById(String deviceip)
	  {
	    Device device = null;
	 
	    Connection conn;
		try {
			conn = DBHelper.getConnection();
			String sql = "" + " select * from device " + " where deviceip= '" + deviceip +"'";
		    ResultSet rs = helper.getRS(conn, sql);
			while (rs.next())
			{
				device = new Device();
				device.setDeviceip(rs.getString("deviceip"));
				device.setDevicename(rs.getString("name"));
				device.setThretemp(rs.getInt("thretemp"));
				device.setRoomtemp(rs.getInt("roomtemp"));
				device.setCurrent(rs.getString("current"));
				device.setCounter(rs.getString("counter"));
				device.setFire(rs.getInt("fire"));
				device.setFan(rs.getInt("fan"));
				device.setMode(rs.getString("mode"));
				device.setWeek(rs.getString("week"));
				device.setErrinfo(rs.getString("errinfo"));
			}
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return device;
	  }
	 
	  /**
	   * 添加设备
	   * 
	   * @throws SQLException
	   */
	  public void addDevice(Device dev) throws SQLException
	  {
	    // 获得数据库连接
	    try {
			Connection conn = DBHelper.getConnection();
		    String sql = "insert into device(deviceip,devicename) values('" +dev.getDeviceip() + "','" + dev.getDevicename() +"',)";
		    
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
	  public void updateDevice(Device dev) throws Exception
	  {
	    Connection conn = DBHelper.getConnection();
	 
	    String sql = "update device set devicename=? where deviceip=?";
	 
	    PreparedStatement ptmt = conn.prepareStatement(sql);
	 
	    ptmt.setString(1, dev.getDevicename());
	    ptmt.setString(2, dev.getDeviceip());
	   
	 
	    ptmt.execute();
		conn.close();
	  }
	 
	  /**
	   * 删除用户
	 * @throws Exception 
	   */
	  public void deleteDevice(String deviceip) throws Exception
	  {
	    Connection conn = DBHelper.getConnection();
	 
	    String sql = "delete from device where deviceip=?";
	 
	    PreparedStatement ptmt = conn.prepareStatement(sql);
	 
	    ptmt.setString(1, deviceip);
	 
	    ptmt.execute();
		conn.close();
	  }
}
