package manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DB.HospitalDAO;
import model.Hospital;

@Service
public class HospitalManager {
	/**
	 * key name
	 */
	private HashMap<String,Hospital> hospitals;
	// TODO
	//@Autowired
	private HospitalDAO hospitalDAO;
	
	public HospitalManager() {
		super();
		hospitalDAO = new HospitalDAO();
		this.hospitals=hospitalDAO.load();
	}
	public HospitalManager(HashMap<String, Hospital> hospital) {
		super();
		this.hospitals = hospital;
		this.hospitalDAO = new HospitalDAO();
	}
	public HashMap<String, Hospital> getHospital() {
		return hospitals;
	}
	public void setHospital(HashMap<String, Hospital> hospital) {
		this.hospitals = hospital;
	}
	public Hospital searchHospital(String name) {
		System.out.println(name);
		return this.hospitals.get(name);
	}
	public ArrayList<Hospital> searchHospitalByTime(String curTime){
		Iterator<String> keys=this.hospitals.keySet().iterator();
		ArrayList<Hospital> ans=new ArrayList<Hospital>();
		
		if(keys.hasNext()) {
			String key=keys.next();
			Hospital hos=this.hospitals.get(key);
			if(hos.getOpenTime().compareTo(curTime)<=0) {
				if(hos.getCloseTime().compareTo(curTime)>0) {
					ans.add(hos);
				}
			}
		}
		
		return ans;
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
