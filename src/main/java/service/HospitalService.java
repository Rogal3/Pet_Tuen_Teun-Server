package service;

import java.util.ArrayList;

import manager.DoctorManager;
import manager.HospitalManager;
import manager.ReservationManager;
import model.Doctor;
import model.Hospital;
import model.Reservation;

public class HospitalService {
	private HospitalManager hospitalManager;
	private ReservationManager reservationManager;
	private DoctorManager doctorManager;
	
	public HospitalService() {
		super();
	}
	public HospitalService(HospitalManager hospitalManager, ReservationManager reservationManager,
			DoctorManager doctorManager) {
		super();
		this.hospitalManager = hospitalManager;
		this.reservationManager = reservationManager;
		this.doctorManager = doctorManager;
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
	public byte addReservation(String hospitalID,String memberID,Reservation reservation) {
		return this.reservationManager.addReservation(hospitalID, memberID, reservation);
	}
	public byte deleteReservation(String hospitalID,String memberID,String hospitalName) {
		return this.reservationManager.deleteReservation(hospitalID, memberID,hospitalName);
	}
	public byte addHospital(Hospital hospital) {
		return this.hospitalManager.addHospital(hospital);
	}
	public byte deleteHospital(String hospitalName) {
		return this.hospitalManager.deleteHospital(hospitalName);
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
}
