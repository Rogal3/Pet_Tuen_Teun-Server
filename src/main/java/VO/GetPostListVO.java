package VO;

public class GetPostListVO{
	private String memberID;
	
	public GetPostListVO() {
	
	}

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	
	@Override
	public String toString() {
		return "GetPostListVO [memberID=" + memberID + "]";
	}
	
}