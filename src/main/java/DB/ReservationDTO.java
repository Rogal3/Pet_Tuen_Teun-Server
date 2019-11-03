package DB;

import java.io.Serializable;

public class ReservationDTO implements Serializable {
	private String id;
	private String hospitalID;
	private String customerID;
	private String reservationType;
	private String reservationDate;
	private int isExecuted;
	
	public ReservationDTO() {
		super();
	}

	public ReservationDTO(String id, String hospitalID, String customerID, String reservationType,
			String reservationDate, int isExecuted) {
		super();
		this.id = id;
		this.hospitalID = hospitalID;
		this.customerID = customerID;
		this.reservationType = reservationType;
		this.reservationDate = reservationDate;
		this.isExecuted = isExecuted;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHospitalID() {
		return hospitalID;
	}

	public void setHospitalID(String hospitalID) {
		this.hospitalID = hospitalID;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getReservationType() {
		return reservationType;
	}

	public void setReservationType(String reservationType) {
		this.reservationType = reservationType;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}

	public int getIsExecuted() {
		return isExecuted;
	}

	public void setIsExecuted(int isExecuted) {
		this.isExecuted = isExecuted;
	}

	@Override
	public String toString() {
		return "ReservationDTO [id=" + id + ", hospitalID=" + hospitalID + ", customerID=" + customerID
				+ ", reservationType=" + reservationType + ", reservationDate=" + reservationDate + ", isExecuted="
				+ isExecuted + "]";
	}

	
}
