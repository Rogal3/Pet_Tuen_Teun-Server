package manager;

import java.util.ArrayList;
import java.util.HashMap;

import model.Reservation;

public class ReservationManager {
	private HashMap<String,ArrayList<Reservation>> reservations;

	public ReservationManager() {
		super();
		this.reservations=new HashMap<String,ArrayList<Reservation>>();
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
	public byte modifyReservation(String hospitalID,String memberID,Reservation reservation) {
		if(searchReservations(memberID)==null)return 0;
		
		ArrayList<Reservation> reser=this.reservations.get(memberID);
		
		for(int i=0;i<reser.size();++i) {
			if(reser.get(i).getHospitalName().equals(hospitalID)) {
				Reservation emp=reser.get(i);
				emp=reservation;
				break;
			}
		}
		
		return 1;
	}
	public byte deleteReservation(String hospitalID,String memberID) {
		if(searchReservations(memberID)==null)return 0;
		ArrayList<Reservation> reser=this.reservations.get(memberID);
		
		for(int i=0;i<reser.size();++i) {
			if(reser.get(i).getHospitalName().equals(hospitalID)) {
				reser.remove(i);
				break;
			}
		}
	
		return 1;
	}
	public byte addReservation(String hospitalID,String memberID,Reservation reservation) {
		if(searchReservations(memberID)!=null)return 0;
		
		this.reservations.get(memberID).add(reservation);
		
		return 1;
	}
}
