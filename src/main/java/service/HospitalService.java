package service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import manager.DoctorManager;
import manager.HospitalManager;
import manager.MemberManager;
import manager.RatingManager;
import manager.ReservationManager;
import model.Doctor;
import model.Hospital;
import model.Rating;
import model.Reservation;

@Service
public class HospitalService {
	@Autowired
	private HospitalManager hospitalManager;
	@Autowired
	private ReservationManager reservationManager;
	@Autowired
	private DoctorManager doctorManager;
	@Autowired
	private RatingManager ratingManager;
	@Autowired
	private MemberManager memberManager;
	public HospitalService() {
		super();
	}
	public HospitalService(HospitalManager hospitalManager, ReservationManager reservationManager,
			DoctorManager doctorManager, RatingManager ratingManager, MemberManager memberManager) {
		super();
		this.hospitalManager = hospitalManager;
		this.reservationManager = reservationManager;
		this.doctorManager = doctorManager;
		this.ratingManager = ratingManager;
		this.memberManager=memberManager;
	}
	
	public MemberManager getMemberManager() {
		return memberManager;
	}
	public void setMemberManager(MemberManager memberManager) {
		this.memberManager = memberManager;
	}
	public HospitalManager getHospitalManager() {
		return hospitalManager;
	}
	public void setHospitalManager(HospitalManager hospitalManager) {
		this.hospitalManager = hospitalManager;
	}
	public ReservationManager getReservationManager() {
		return reservationManager;
	}
	public void setReservationManager(ReservationManager reservationManager) {
		this.reservationManager = reservationManager;
	}
	public DoctorManager getDoctorManager() {
		return doctorManager;
	}
	public void setDoctorManager(DoctorManager doctorManager) {
		this.doctorManager = doctorManager;
	}
	public RatingManager getRatingManager() {
		return ratingManager;
	}
	public void setRatingManager(RatingManager ratingManager) {
		this.ratingManager = ratingManager;
	}
	public byte addReservation(String hospitalID,String memberID,Reservation reservation) {
		return this.reservationManager.addReservation(hospitalID, memberID, reservation);
	}
	public byte deleteReservation(String reserveID,String memberID) {
		String type=memberManager.searchMemberByID(memberID).getType();
		return this.reservationManager.deleteReservationsBymemberID(memberID, type);
	}
	public byte addHospital(Hospital hospital) {
		return this.hospitalManager.addHospital(hospital);
	}
	public byte deleteHospital(String hospitalName) {
		return this.hospitalManager.deleteHospital(hospitalName);
	}
	
	public ArrayList<Reservation> searchReservation(String memberID){
		String type=memberManager.searchMemberByID(memberID).getType();
		return this.reservationManager.searchReservationsByMemberID(memberID, type);
	}
	public Hospital searchHospitalByName(String hospitalName) {
		return this.hospitalManager.searchHospital(hospitalName);
	}
	public ArrayList<Hospital> searchHospital(String curTime){
		return this.hospitalManager.searchHospitalByTime(curTime);
	}
	public byte modifyDoctorTime(String hospitalID,String name,String time) {
		return this.doctorManager.modifyDoctorTime(hospitalID, name, time);
	}
	public byte addDoctor(String hospitalID,Doctor doctor) {
		return this.doctorManager.addDoctor(hospitalID, doctor);
	}
	public byte deleteDoctor(String hospitalID, String name) {
		return this.doctorManager.deleteDoctor(hospitalID,name);
	}
	public byte addRating(String writer, String hospital, int scale, String content) {
		return this.ratingManager.addRating(writer, hospital, scale, content);
	}
	
}
