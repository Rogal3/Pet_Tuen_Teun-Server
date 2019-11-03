package manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DB.DoctorDAO;
import model.Doctor;

@Service
public class DoctorManager {
	/**
	 * key1=hospitalID key2= docName;
	 */
	private HashMap<String,HashMap<String,Doctor>> doctors;
	// TODO
	//@Autowired
	private DoctorDAO doctorDAO;

	public DoctorManager() {
		super();
		doctorDAO = new DoctorDAO();
		// TODO
		//this.doctors=doctorDAO.load();
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
