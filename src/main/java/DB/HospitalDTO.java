package DB;

import java.io.Serializable;

public class HospitalDTO implements Serializable {
	private String name;
	private String address;
	private String phoneNum;
	private String openTime;
	private String closeTime;
	
	public HospitalDTO() {
		super();
	}
	public HospitalDTO(String name, String address, String phoneNum, String openTime, String closeTime) {
		super();
		this.name = name;
		this.address = address;
		this.phoneNum = phoneNum;
		this.openTime = openTime;
		this.closeTime = closeTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getOpenTime() {
		return openTime;
	}
	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}
	public String getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}
	@Override
	public String toString() {
		return "HospitalDTO [name=" + name + ", address=" + address + ", phoneNum=" + phoneNum + ", openTime="
				+ openTime + ", closeTime=" + closeTime + "]";
	}
}
