package model;

public class Doctor {
	private String name;
	private String position;
	private String major;
	private String workingTime;
	
	public Doctor() {
		super();
	}
	public Doctor(String name, String position, String major, String workingTime) {
		super();
		this.name = name;
		this.position = position;
		this.major = major;
		this.workingTime = workingTime;
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
	public String getWorkingTime() {
		return workingTime;
	}
	public void setWorkingTime(String workingTime) {
		this.workingTime = workingTime;
	}
	public byte modifyWorkingTime(String workingTime) {
		this.workingTime=workingTime;
		return 1;
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
