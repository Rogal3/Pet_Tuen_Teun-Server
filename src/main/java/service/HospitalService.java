package service;

import manager.DoctorManager;
import manager.HospitalManager;
import manager.RatingManager;
import manager.ReservationManager;
import model.Doctor;
import model.Hospital;
import model.Rating;
import model.Reservation;

public class HospitalService {
	private HospitalManager hospitalManager;
	private ReservationManager reservationManager;
	private DoctorManager doctorManager;
	private RatingManager ratingManager;
	
	public HospitalService() {
		super();
	}
	public HospitalService(HospitalManager hospitalManager, ReservationManager reservationManager,
			DoctorManager doctorManager, RatingManager ratingManager) {
		super();
		this.hospitalManager = hospitalManager;
		this.reservationManager = reservationManager;
		this.doctorManager = doctorManager;
		this.ratingManager = ratingManager;
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
	public byte deleteReservation(String hospitalID,String memberID) {
		return this.reservationManager.deleteReservation(hospitalID, memberID);
	}
	public byte addHospital(Hospital hospital) {
		return this.hospitalManager.addHospital(hospital);
	}
	public byte deleteHospital(String hospitalName) {
		return this.hospitalManager.deleteHospital(hospitalName);
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
	public byte addRating(String id, String writer, String hospital, int scale, String content) {
		return this.ratingManager.addRating(new Rating(id, writer, hospital, scale, content));
	}
}
