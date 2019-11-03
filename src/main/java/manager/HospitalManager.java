package manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import model.Hospital;

public class HospitalManager {
	/**
	 * key name
	 */
	private HashMap<String,Hospital> hospitals;
	public HospitalManager() {
		super();
		this.hospitals=new HashMap<String,Hospital>();	
		this.hospitals.put("xx병원",new Hospital("xx병원", "서울시중랑구", "010-1234-1234", "10시30분", "20시30분"));
		this.hospitals.put("yy병원",new Hospital("yy병원", "서울시구로구", "010-1234-1234", "00시30분", "20시30분"));
		this.hospitals.put("zz병원",new Hospital("zz병원", "서울시남대문구", "010-1234-1234", "13시30분", "23시30분"));
		this.hospitals.put("ww병원",new Hospital("ww병원", "서울시동대문구", "010-1234-1234", "10시30분", "20시30분"));
		
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
