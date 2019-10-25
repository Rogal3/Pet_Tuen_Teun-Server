package manager;

import java.util.HashMap;

import model.Hospital;

public class HospitalManager {
	private HashMap<String,Hospital> hospitals;

	public HospitalManager() {
		super();
		this.hospitals=new HashMap<String,Hospital>();
	}
	public HospitalManager(HashMap<String, Hospital> hospital) {
		super();
		this.hospitals = hospital;
	}
	public HashMap<String, Hospital> getHospital() {
		return hospitals;
	}
	public void setHospital(HashMap<String, Hospital> hospital) {
		this.hospitals = hospital;
	}
	public Hospital searchHospital(String name) {
		return this.hospitals.get(name);
	}
	public byte modifyHospital(Hospital hospital) {
		if(this.hospitals.get(hospital).getName()==null)return 0;
		
		this.hospitals.put(hospital.getName(), hospital);
		return 1;
	}
	public byte deleteHospital(String name) {
		if(searchHospital(name)==null)return 0;
		this.hospitals.remove(name);
		return 1;
	}
	public byte addHospital(Hospital hospital) {
		if(searchHospital(hospital.getName())!=null)return 0;
		this.hospitals.put(hospital.getName(), hospital);
		return 1;
	}
	
	
}
