package DB;

import java.io.Serializable;

public class DoctorDTO implements Serializable {
	private String name;
	private String hospital;
	private String position;
	private String major;
	
	public DoctorDTO() {
		super();
	}

	public DoctorDTO(String name, String hospital, String position, String major) {
		super();
		this.name = name;
		this.hospital = hospital;
		this.position = position;
		this.major = major;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
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

	@Override
	public String toString() {
		return "DoctorDTO [name=" + name + ", hospital=" + hospital + ", position=" + position + ", major=" + major
				+ "]";
	}


}
