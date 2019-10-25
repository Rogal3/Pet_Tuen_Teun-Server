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

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	
	public byte modifyPassword(String password) {
		this.password=password;
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
	public byte modifyName(String name) {
		this.name=name;
		return 1;
	}
}
