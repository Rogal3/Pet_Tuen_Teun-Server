package manager;

import java.util.ArrayList;
import java.util.HashMap;

import model.Reservation;

public class ReservationManager {
	/**
	 * key memberid
	 */
	private HashMap<String,ArrayList<Reservation>> reservations;
	
	public ReservationManager() {
		super();
		this.reservations=new HashMap<String,ArrayList<Reservation>>();
		ArrayList<Reservation> reser=new ArrayList<Reservation>();
		reser.add(new Reservation("xx병원", "aaa", "예방접종","19/09/30",(byte)1));
		reser.add(new Reservation("xx병원", "aaa", "진단","19/10/10",(byte)1));
		reser.add(new Reservation("xx병원", "aaa", "진단","19/10/11",(byte)1));
		reser.add(new Reservation("xx병원", "aaa", "예방접종","19/10/14",(byte)1));
		
		ArrayList<Reservation> reser1=new ArrayList<Reservation>();
		reser1.add(new Reservation("xx병원", "bbb", "예방접종","19/09/30",(byte)1));
		reser1.add(new Reservation("xx병원", "bbb", "진단","19/10/10",(byte)1));
		reser1.add(new Reservation("xx병원", "bbb", "진단","19/10/11",(byte)1));
		
		ArrayList<Reservation> reser2=new ArrayList<Reservation>();
		reser2.add(new Reservation("xx병원", "ccc", "예방접종","19/09/30",(byte)1));
		reser2.add(new Reservation("xx병원", "ccc", "진단","19/10/10",(byte)1));
		reser2.add(new Reservation("xx병원", "ccc", "진단","19/10/11",(byte)1));
		reser2.add(new Reservation("xx병원", "ccc", "예방접종","19/10/14",(byte)1));
		reser2.add(new Reservation("xx병원", "ccc", "진단","19/10/15",(byte)1));
		reser2.add(new Reservation("xx병원", "ccc", "진단","19/10/16",(byte)1));
		reser2.add(new Reservation("xx병원", "ccc", "예방접종","19/10/17",(byte)1));
		
		ArrayList<Reservation> reser3=new ArrayList<Reservation>();
		reser3.add(new Reservation("ccc", "ddd", "예방접종","19/09/30",(byte)1));
		reser3.add(new Reservation("ccc", "ddd", "진단","19/10/10",(byte)1));
		reser3.add(new Reservation("ccc", "ddd", "진단","19/10/11",(byte)1));
		reser3.add(new Reservation("ccc", "ddd", "예방접종","19/10/14",(byte)1));
		reser3.add(new Reservation("ccc", "ddd", "진단","19/10/15",(byte)1));
		reser3.add(new Reservation("ccc", "ddd", "진단","19/10/16",(byte)1));
		reser3.add(new Reservation("ccc", "ddd", "예방접종","19/10/17",(byte)1));
		reser3.add(new Reservation("bbb", "ddd", "예방접종","19/09/30",(byte)1));
		reser3.add(new Reservation("bbb", "ddd", "진단","19/10/10",(byte)1));
		reser3.add(new Reservation("bbb", "ddd", "진단","19/10/11",(byte)1));
		reser3.add(new Reservation("aaa", "ddd", "예방접종","19/09/30",(byte)1));
		reser3.add(new Reservation("aaa", "ddd", "진단","19/10/10",(byte)1));
		reser3.add(new Reservation("aaa", "ddd", "진단","19/10/11",(byte)1));
		reser3.add(new Reservation("aaa", "ddd", "예방접종","19/10/14",(byte)1));
		
		this.reservations.put("aaa",reser);
		this.reservations.put("bbb",reser1);
		this.reservations.put("ccc",reser2);
		this.reservations.put("ddd",reser3);
		
	}
	public ReservationManager(HashMap<String, ArrayList<Reservation>> reservations) {
		super();
		this.reservations = reservations;
	}
	public HashMap<String, ArrayList<Reservation>> getReservations() {
		return reservations;
	}
	public void setReservations(HashMap<String, ArrayList<Reservation>> reservations) {
		this.reservations = reservations;
	}
	public ArrayList<Reservation> searchReservations(String memberID){
		return this.reservations.get(memberID);
	}
	public ArrayList<Reservation> searchReservationByExecuted(String memberID,byte isExecuted){
		if(this.reservations.get(memberID)==null)return null;
		ArrayList<Reservation> reser=new ArrayList<Reservation>();
		ArrayList<Reservation> getter=this.reservations.get(memberID);
		
		for(int i=0;i<getter.size();++i) {
			if(getter.get(i).getIsExecuted()==isExecuted) {
				reser.add(getter.get(i));
			}
		}
		return reser;
	}
	public ArrayList<Reservation> searchReservationByDate(String memberID,String reservationDate){
		if(searchReservations(memberID)==null)return null;
		ArrayList<Reservation> reser=new ArrayList<Reservation>();
		ArrayList<Reservation> getter=this.reservations.get(memberID);
		
		for(int i=0;i<getter.size();++i) {
			if(getter.get(i).getReservationDate()==reservationDate) {
				reser.add(getter.get(i));
			}
		}
		return reser;
	}
	public byte modifyReservation(String hospitalID,String hospitalName,String memberID,Reservation reservation) {
		if(searchReservations(memberID)==null)return 0;
		
		ArrayList<Reservation> reser=this.reservations.get(memberID);
		ArrayList<Reservation> reser2=this.reservations.get(hospitalID);
		
		for(int i=0;i<reser.size();++i) {
			if(reser.get(i).getHospitalName().equals(hospitalName)) {
				reser.remove(i);
				reser.add(i, reservation);
				break;
			}
		}
		
		for(int i=0;i<reser2.size();++i) {
			if(reser2.get(i).getHospitalName().equals(hospitalID)) {
				reser2.remove(i);
				reser2.add(i, reservation);
				break;
			}
		}
		
		return 1;
	}
	public byte modifyIsReservation(String hospitalID,String hospitalName,String memberID,String date,byte is) {
		if(searchReservations(memberID)==null)return 0;
		
		ArrayList<Reservation> reser=this.reservations.get(memberID);
		ArrayList<Reservation> reser2=this.reservations.get(hospitalID);
		
		for(int i=0;i<reser.size();++i) {
			if(reser.get(i).getHospitalName().equals(hospitalName)&&reser.get(i).getReservationDate().equals(date)) {
				reser.get(i).modifyExecution(is);
				break;
			}
		}
		
		for(int i=0;i<reser2.size();++i) {
			if(reser2.get(i).getHospitalName().equals(memberID)&&reser.get(i).getReservationDate().equals(date)) {
				reser2.get(i).modifyExecution(is);
				break;
			}
		}
		return 1;
	}
	public byte deleteReservation(String hospitalID,String memberID,String hospitalName) {
		if(searchReservations(memberID)==null)return 0;
		ArrayList<Reservation> reser=this.reservations.get(memberID);
		ArrayList<Reservation> reser2=this.reservations.get(hospitalID);
		
		for(int i=0;i<reser.size();++i) {
			if(reser.get(i).getHospitalName().equals(hospitalName)) {
				reser.remove(i);
				break;
			}
		}
		
		for(int i=0;i<reser2.size();++i) {
			if(reser2.get(i).getHospitalName().equals(memberID)) {
				reser2.remove(i);
				break;
			}
		}
	
		return 1;
	}
	public byte addReservation(String hospitalID,String memberID,Reservation reservation) {
		if(searchReservations(memberID)==null) {
			this.reservations.put(memberID,new ArrayList<Reservation>());
		}
		this.reservations.get(memberID).add(reservation);
		
		if(searchReservations(hospitalID)==null) {
			this.reservations.put(hospitalID,new ArrayList<Reservation>());
		}
		this.reservations.get(hospitalID).add(reservation);
		
		return 1;
	}
}
