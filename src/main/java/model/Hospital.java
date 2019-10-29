package model;

public class Hospital {
	private String name;
	private String address;
	private String phoneNum;
	private String openTime;
	private String closeTime;
	
	public Hospital() {
		super();
	}
	public Hospital(String name, String address, String phoneNum, String openTime, String closeTime) {
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
	public byte modifyName(String name) {
		this.name=name;
		return 1;
	}
	public byte modifyAddress(String address) {
		this.address=address;
		return 1;
	}
	public byte phoneNum(String phoneNum) {
		this.phoneNum=phoneNum;
		return 1;
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
	
}
