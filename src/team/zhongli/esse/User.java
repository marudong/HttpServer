package team.zhongli.esse;

/*
 * 用戶类
 */
public class User {
	private String phone;
	private String name;
	private String password;
	private String device;
	private int groupid; //用户组
	private int status; //账户状态（冻结0 正常1）
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public int getGroupid() {
		return groupid;
	}
	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	};
	
}
