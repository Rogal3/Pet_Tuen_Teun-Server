package DB;

import java.io.Serializable;

public class ReservationDTO implements Serializable {
	private String hospitalName;
	private String memberID;
	private String reservationType;
	private String reservationDate;
	private byte isExecuted;
	
	public ReservationDTO() {
		super();
	}
	public ReservationDTO(String hospitalName, String memberID, String reservationType, String reservationDate,
			byte isExecuted) {
		super();
		this.hospitalName = hospitalName;
		this.memberID = memberID;
		this.reservationType = reservationType;
		this.reservationDate = reservationDate;
		this.isExecuted = isExecuted;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
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
	public byte getIsExecuted() {
		return isExecuted;
	}
	public void setIsExecuted(byte isExecuted) {
		this.isExecuted = isExecuted;
	}
	@Override
	public String toString() {
		return "ReservationDTO [hospitalName=" + hospitalName + ", memberID=" + memberID + ", reservationType="
				+ reservationType + ", reservationDate=" + reservationDate + ", isExecuted=" + isExecuted + "]";
	}

}
