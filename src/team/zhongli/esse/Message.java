package team.zhongli.esse;

public class Message {
	private String user;
	private String message;
	private int status; //留言状态（已处理2  跟进中1 未处理0）
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
