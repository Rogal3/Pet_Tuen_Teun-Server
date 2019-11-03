package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import manager.ReservationManager;

@Service
public class ReservationService {
	@Autowired
	private ReservationManager reservationManager;
	
	public byte deleteReservation(String reservationID,String memberID) {
		return reservationManager.deleteReservationByID(reservationID, memberID);
	}
}
