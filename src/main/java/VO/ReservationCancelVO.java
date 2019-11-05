package VO;

public class ReservationCancelVO{
	private String reserveID;
	private String memberID;
	
	public ReservationCancelVO() {
		super();
	}

	public String getReserveID() {
		return reserveID;
	}

	public void setReserveID(String reserveID) {
		this.reserveID = reserveID;
	}

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	@Override
	public String toString() {
		return "ReservationCancelVO [reserveID=" + reserveID + ", memberID=" + memberID + "]";
	}
	
}