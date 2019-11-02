package manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import model.Doctor;

public class DoctorManager {
	/**
	 * key1=hospitalID key2= docname;
	 */
	private HashMap<String,HashMap<String,Doctor>> doctors;

	public DoctorManager() {
		super();
		this.doctors=new HashMap<String,HashMap<String,Doctor>>();
		
		this.doctors.put("ddd",new HashMap<String,Doctor>());
		this.doctors.get("ddd").put("난세구",new Doctor("난세구", "원장", "고양이과", "매일"));
		this.doctors.get("ddd").put("구세",new Doctor("구세", "직책1", "강아지과", "매일"));
		this.doctors.get("ddd").put("주",new Doctor("주", "직책2", "아르마딜로과", "매일"));

	}
	public DoctorManager(HashMap<String, HashMap<String, Doctor>> doctors) {
		super();
		this.doctors = doctors;
	}
	public HashMap<String, HashMap<String, Doctor>> getDoctors() {
		return doctors;
	}
	public void setDoctors(HashMap<String, HashMap<String, Doctor>> doctors) {
		this.doctors = doctors;
	}
	
	public Doctor searchDoctor(String hospitalID,String name) {
		return this.doctors.get(hospitalID).get(name);
	}
	public ArrayList<Doctor> searchDoctors(String hospitalID){
		if(this.doctors.get(hospitalID)==null)return null;
		ArrayList<Doctor> doctors=new ArrayList<Doctor>();
		
		HashMap<String,Doctor> emp=this.doctors.get(hospitalID);
		Iterator<String> iter=emp.keySet().iterator();
		
		while(iter.hasNext()) {
			doctors.add(emp.get(iter.next()));
		}
		
		return doctors;
	}
	public byte modifyDoctor(String hospitalID,Doctor doctor) {
		if(searchDoctor(hospitalID, doctor.getName())==null)return 0;
		
		this.doctors.get(hospitalID).put(doctor.getName(),doctor);
		return 1;
	}
	public byte modifyDoctorTime(String hospitalID,String doctorName,String time) {
		if(searchDoctor(hospitalID, doctorName)==null)return 0;
		return this.doctors.get(hospitalID).get(doctorName).modifyWorkingTime(time);
		
	}
	public byte deleteDoctor(String hospitalID,String name) {
		if(searchDoctor(hospitalID, name)==null)return 0;
		this.doctors.get(hospitalID).remove(name);
		return 1;
	}
	public byte deleteDoctor(String hospitalID) {
		if(this.doctors.get(hospitalID)==null)return 0;
		this.doctors.remove(hospitalID);
		return 1;
	}
	public byte addDoctor(String hospitalID,Doctor doctor) {
		if(this.doctors.get(hospitalID)==null) {
			this.doctors.put(hospitalID, new HashMap<String,Doctor>());
		}
		this.doctors.get(hospitalID).put(doctor.getName(),doctor);
		
		return 1;
	}
}
