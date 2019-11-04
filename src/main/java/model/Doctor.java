package model;

public class Doctor {
	private String name;
	private String position;
	private String major;
	
	public Doctor() {
		super();
	}
	public Doctor(String name, String position, String major) {
		super();
		this.name = name;
		this.position = position;
		this.major = major;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public byte modifyPosition(String position) {
		this.position=position;
		return 1;
	}
	public byte modifyMajor(String major) {
		this.major=major;
		return 1;
	}
	
}
