package manager;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Member;
import model.Reservation;

@Service
public class ReservationManager {
	private ArrayList<Reservation> reservations;
	@Autowired
	private MemberManager memberManager;//가입하지 않은 사람이 접근하는걸 막기위해서 필요,차후 회원이 고객인지 병원 회원인지 확인용
	
	private ReservationManager() {
		super();
		reservations=new ArrayList<Reservation>();
		reservations.add(new Reservation("r001","ddd", "aaa", "예방접종","19/09/30",(byte)1));
		reservations.add(new Reservation("r002","ddd", "aaa", "진단","19/10/13",(byte)1));
		reservations.add(new Reservation("r003","ddd", "aaa", "진단","19/10/11",(byte)1));
		reservations.add(new Reservation("r004","ddd", "aaa", "예방접종","19/10/14",(byte)1));	
		
		reservations.add(new Reservation("r004","ddd", "bbb", "예방접종","19/09/30",(byte)1));
		reservations.add(new Reservation("r005","ddd", "bbb", "진단","19/10/10",(byte)1));
		reservations.add(new Reservation("r006","ddd", "bbb", "진단","19/10/11",(byte)1));
		
		reservations.add(new Reservation("r007","ddd", "ccc", "예방접종","19/09/30",(byte)1));
		reservations.add(new Reservation("r008","ddd", "ccc", "진단","19/10/10",(byte)1));
		reservations.add(new Reservation("r009","ddd", "ccc", "진단","19/10/11",(byte)1));
		reservations.add(new Reservation("r010","ddd", "ccc", "예방접종","19/10/14",(byte)1));		
	}
	
	public ReservationManager(ArrayList<Reservation> reservations) {
		super();
		this.reservations = reservations;
	}
	//해당하는 방번호 리턴(내부로직용이라고 생각해서 private 썻다.	
	private int searchReservationIndexByID(String id) {
		for(int i=0;i<reservations.size();i++)
			if(reservations.get(i).getId().equals(id))
				return i;
		return -1;//없으면 -1
	}
	//인덱스로 예약 찾는걸 함수로 만들어야하나?
	public Reservation searchReservationByIndex(int index) {
		if(index < 0 && index>=reservations.size())
			return null;//오류 처리는 어케 하려나?
		return reservations.get(index);
	}
	
	public Reservation searchReservationByID(String id) {
		int index=searchReservationIndexByID(id);
		
		return searchReservationByIndex(index);//없으면 null리턴
	}
	
	public ArrayList<Reservation> searchReservationsByCustomerID(String customerID){
		ArrayList<Reservation> list=new ArrayList<Reservation>();
		for(int i=0;i<reservations.size();i++) {
			Reservation item=reservations.get(i);
			if(item.getCutomerID().equals(customerID)) {
				list.add(item);
			}
		}
		return list;
	}
	
	public ArrayList<Reservation> searchReservationsByHospitalID(String hospitalID){
		ArrayList<Reservation> list=new ArrayList<Reservation>();
		for(int i=0;i<reservations.size();i++) {
			Reservation item=reservations.get(i);
			if(item.getHospitalID().equals(hospitalID)) {
				list.add(item);
			}
		}
		return list;
	}
	
	public ArrayList<Reservation> searchReservationsByMemberID(String memberID,String type){
		ArrayList<Reservation> list=null;
		
		if("hospital".equals(type)) {
			list=searchReservationsByHospitalID(memberID);
		}
		else if("customer".equals(type)) {
			list=searchReservationsByCustomerID(memberID);
		}
		else;
		return list;
	}
	
	public ArrayList<Reservation> searchReservationsByExecuted(String memberID,String type,byte isExecuted){
		ArrayList<Reservation> list=searchReservationsByMemberID(memberID,type);
		
		if(list!= null)
			for(int i=0;i<list.size();i++) {
				Reservation item=list.get(i);
				if(item.getIsExecuted() != isExecuted) {
					list.remove(i);
				}
			}
		
		return list;
	}
	
	public ArrayList<Reservation> searchReservationsByDate(String memberID,String type,String reservationDate){
		ArrayList<Reservation> list=searchReservationsByMemberID(memberID,type);
		
		if(list != null)
			for(int i=0;i<list.size();i++) {
				Reservation item=list.get(i);
				if(item.getReservationDate().equals(reservationDate) ==false) {
					list.remove(i);
				}
			}
		return list;
	}
	
	public byte modifyReservation(String reserveID,String memberID,Reservation reservation) {
		//예약 수정은 고객 회원만 가능
		//존재하지 않는 회원일 경우
		if(memberManager.searchMemberByID(memberID)==null)
			return 0;
		//수정할 예약
		Reservation item=searchReservationByID(reserveID);
		//존재하지 않는 예약일 경우
		if(item == null)
			return 0;
		//회원과 예약이 관련 없을 경우
		if(item.getHospitalID().equals(memberID) ==false || item.getCutomerID().equals(memberID) == false)
			return 0;
		//수정과정
		settingInfo(reserveID,reservation);
		return 1;
	}
	//예약id,고객id,병원id는 수정이 불가능하다
	private byte settingInfo(String reserveID,Reservation reservation) {
		Reservation item=searchReservationByID(reserveID);
		//이런걸 이렇게 다 해야하나 ㅋㅋ 예외로 날리는게 맞는거같다.
		//존재하지 않는 예약이라 세팅실패
		if(item ==null)
			return 0;
		//수정할 예약정보가 원래 예약이 맞는지 확인
		if(reservation.getId().equals(reserveID))
			return 0;
		
		item.setIsExecuted(reservation.getIsExecuted());
		item.setReservationDate(reservation.getReservationDate());
		item.setReservationType(reservation.getReservationType());
		return 1;
	}
	//그냥 덮어쓰기용 함수(유효성 검사 x)
	private byte settingInfo(Reservation item,String type,String date,byte is) {
		//존재해야 덮어쓴다.
		if(item ==null)
			return 0;
		
		item.setIsExecuted(is);
		item.setReservationDate(date);
		item.setReservationType(type);
		return 1;
	}
	public byte modifyIsReservation(String reserveID,String memberID,String type,String date,byte is) {
		Reservation item=searchReservationByID(reserveID);
		//이런걸 이렇게 다 해야하나 ㅋㅋ 예외로 날리는게 맞는거같다.
		
		return settingInfo(item,type,date,is);
	}
	//삭제는 그 예약관 관련된 고객이나 병원 회원만 가능하다.
	public byte deleteReservationByID(String reservationID,String memberID) {
		Member member=memberManager.searchMemberByID(memberID);
		//존재하지 않는 회원일 경우
		if(member==null)
			return 0;
		int index=searchReservationIndexByID(reservationID);
		//존재하지 않는 예약일 경우
		System.out.println("index"+index);
		if(index < 0)
			return 0;
		Reservation item=searchReservationByIndex(index);
		//예약과 관련이 있는 회원일 경우
		if(item.getHospitalID().equals(memberID) || item.getCutomerID().equals(memberID)) {
			reservations.remove(index);
			return 1;
		}
		//예약 실패한 경우
		return 0;
	}
	
	public byte deleteReservationsBymemberID(String memberID,String type) {
		//존재하지 회원인지 확인
		if(memberManager.searchMemberByID(memberID) == null)
			return 0;
		
		for(int i=0;i<reservations.size();i++) {
			Reservation item=reservations.get(i);
			if("hospital".equals(type) && item.getHospitalID() == memberID) {
				reservations.remove(i);
			}
			else if("customer".equals(type) && item.getCutomerID() == memberID) {
				reservations.remove(i);
			}
			else;
		}
		
		return 1;
	}
	
	public byte addReservation(String hospitalID,String memberID,Reservation reservation) {
		//유효성 검증하는거 - 병원id(병원은 비회원도 가능해서 조건검사 x),memberID 확인
		//아이디가 부정할경우 예약이 불가능하다.
		if(memberManager.searchMemberByID(memberID)==null)
			return 0;
		reservations.add(reservation);
		return 1;
	}
}
