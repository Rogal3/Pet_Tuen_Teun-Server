package DB;

import java.io.Serializable;

public class ReservationDTO implements Serializable {
	private String id;
	private String hospitalMemberID;
	private String customerID;
	private String reservationType;
	private String reservationDate;
	private int isExecuted;
	
	public ReservationDTO() {
		super();
	}

	public ReservationDTO(String id, String hospitalMemberID, String customerID, String reservationType,
			String reservationDate, int isExecuted) {
		super();
		this.id = id;
		this.hospitalMemberID = hospitalMemberID;
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

	public String gethospitalMemberID() {
		return hospitalMemberID;
	}

	public void sethospitalMemberID(String hospitalMemberID) {
		this.hospitalMemberID = hospitalMemberID;
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
		return "ReservationDTO [id=" + id + ", hospitalMemberID=" + hospitalMemberID + ", customerID=" + customerID
				+ ", reservationType=" + reservationType + ", reservationDate=" + reservationDate + ", isExecuted="
				+ isExecuted + "]";
	}

	
}
