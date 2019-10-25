package model;

public class Member {
	private String id;
	private String password;
	private String name;
	private String address;
	private String phoneNum;
	
	public Member() {
		super();
	}
	
	public Member(String id, String password, String name, String address, String phoneNum) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.address = address;
		this.phoneNum = phoneNum;
	}
	
	public String getId() {
		return id;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getName() {
		return name;
	}
	
	
	public String getAddress() {
		return address;
	}
	
	public String getPhoneNum() {
		return phoneNum;
	}
	public String get(String attribute, String data) {
		switch(attribute) {
			case "id":
				return getId();
			case "password":
				return getPassword();
			case "name":
				return getName();
			case "address":
				return getAddress();
			case "phoneNum":
				return getPhoneNum();
			default:
				return null;
		}
	}
}
