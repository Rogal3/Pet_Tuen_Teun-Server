package model;

public class Reservation {
	//id가 필요하다.
	private String id;
	private String hospitalID;
	private String cutomerID;
	private String reservationType;
	private String reservationDate;
	private byte isExecuted;
	 
	public Reservation() {
		super();
	}
	public Reservation(String id,String hospitalID, String cutomerID, String reservationType, String reservationDate,
			byte isExecuted) {
		super();
		this.id=id;
		this.hospitalID = hospitalID;
		this.cutomerID = cutomerID;
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
	public String getCutomerID() {
		return cutomerID;
	}
	public void setCutomerID(String cutomerID) {
		this.cutomerID = cutomerID;
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
	
}
