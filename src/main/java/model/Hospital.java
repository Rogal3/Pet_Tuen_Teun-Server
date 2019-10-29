package model;

public class Hospital {
	private String name;
	private String address;
	private String phoneNum;
	
	
	public Hospital() {
		super();
	}
	public Hospital(String name, String address, String phoneNum) {
		super();
		this.name = name;
		this.address = address;
		this.phoneNum = phoneNum;
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
	public byte modifyPhoneNum(String phoneNum) {
		this.phoneNum=phoneNum;
		return 1;
	}
}
