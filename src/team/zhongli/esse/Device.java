package team.zhongli.esse;

public class Device {
	private String deviceip;
	private String devicename;
	private int thretemp; //����
	private int roomtemp; //�����¶�
	private String current; //��ǰʱ��
	private String counter; //�ۼ�����ʱ��
	private int fire; //������λ
	private int fan; //�����λ
	private String mode; //��ǰ����ģʽ
	private String week; //�ܶ�ʱģʽ
	private String errinfo; //���д�����Ϣ
	public String getDeviceip() {
		return deviceip;
	}
	public void setDeviceip(String deviceip) {
		this.deviceip = deviceip;
	}
	public String getDevicename() {
		return devicename;
	}
	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}
	public int getThretemp() {
		return thretemp;
	}
	public void setThretemp(int thretemp) {
		this.thretemp = thretemp;
	}
	public int getRoomtemp() {
		return roomtemp;
	}
	public void setRoomtemp(int roomtemp) {
		this.roomtemp = roomtemp;
	}
	public String getCurrent() {
		return current;
	}
	public void setCurrent(String current) {
		this.current = current;
	}
	public String getCounter() {
		return counter;
	}
	public void setCounter(String counter) {
		this.counter = counter;
	}
	public int getFire() {
		return fire;
	}
	public void setFire(int fire) {
		this.fire = fire;
	}
	public int getFan() {
		return fan;
	}
	public void setFan(int fan) {
		this.fan = fan;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getErrinfo() {
		return errinfo;
	}
	public void setErrinfo(String errinfo) {
		this.errinfo = errinfo;
	}
}
