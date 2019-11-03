package manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DB.ReservationDAO;
import model.Member;
import model.Reservation;

@Service
public class ReservationManager {
	private List<Reservation> reservations;
	@Autowired
	private MemberManager memberManager;//媛��엯�븯吏� �븡�� �궗�엺�씠 �젒洹쇳븯�뒗嫄� 留됯린�쐞�빐�꽌 �븘�슂,李⑦썑 �쉶�썝�씠 怨좉컼�씤吏� 蹂묒썝 �쉶�썝�씤吏� �솗�씤�슜
	@Autowired
	private ReservationDAO reservationDAO;
	
	private ReservationManager() {
		super();
		reservations = reservationDAO.load();
	}
	
	public ReservationManager(ArrayList<Reservation> reservations) {
		super();
		this.reservations = reservations;
	}
	//�빐�떦�븯�뒗 諛⑸쾲�샇 由ы꽩(�궡遺�濡쒖쭅�슜�씠�씪怨� �깮媛곹빐�꽌 private �띀�떎.	
	private int searchReservationIndexByID(String id) {
		for(int i=0;i<reservations.size();i++)
			if(reservations.get(i).getId()==id)
				return i;
		return -1;//�뾾�쑝硫� -1
	}
	//�씤�뜳�뒪濡� �삁�빟 李얜뒗嫄� �븿�닔濡� 留뚮뱾�뼱�빞�븯�굹?
	public Reservation searchReservationByIndex(int index) {
		if(index < 0 && index>=reservations.size())
			return null;//�삤瑜� 泥섎━�뒗 �뼱耳� �븯�젮�굹?
		return reservations.get(index);
	}
	
	public Reservation searchReservationByID(String id) {
		int index=searchReservationIndexByID(id);
		
		return searchReservationByIndex(index);//�뾾�쑝硫� null由ы꽩
	}
	
	public ArrayList<Reservation> searchReservationsByCustomerID(String customerID){
		ArrayList<Reservation> list=new ArrayList<Reservation>();
		for(int i=0;i<reservations.size();i++) {
			Reservation item=reservations.get(i);
			if(item.getCustomerID().equals(customerID)) {
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
		//�삁�빟 �닔�젙�� 怨좉컼 �쉶�썝留� 媛��뒫
		//議댁옱�븯吏� �븡�뒗 �쉶�썝�씪 寃쎌슦
		if(memberManager.searchMemberByID(memberID)==null)
			return 0;
		//�닔�젙�븷 �삁�빟
		Reservation item=searchReservationByID(reserveID);
		//議댁옱�븯吏� �븡�뒗 �삁�빟�씪 寃쎌슦
		if(item == null)
			return 0;
		//�쉶�썝怨� �삁�빟�씠 愿��젴 �뾾�쓣 寃쎌슦
		if(item.getHospitalID().equals(memberID) ==false || item.getCustomerID().equals(memberID) == false)
			return 0;
		//�닔�젙怨쇱젙
		settingInfo(reserveID,reservation);
		return 1;
	}
	//�삁�빟id,怨좉컼id,蹂묒썝id�뒗 �닔�젙�씠 遺덇��뒫�븯�떎
	private byte settingInfo(String reserveID,Reservation reservation) {
		Reservation item=searchReservationByID(reserveID);
		//�씠�윴嫄� �씠�젃寃� �떎 �빐�빞�븯�굹 �뀑�뀑 �삁�쇅濡� �궇由щ뒗寃� 留욌뒗嫄곌컳�떎.
		//議댁옱�븯吏� �븡�뒗 �삁�빟�씠�씪 �꽭�똿�떎�뙣
		if(item ==null)
			return 0;
		//�닔�젙�븷 �삁�빟�젙蹂닿� �썝�옒 �삁�빟�씠 留욌뒗吏� �솗�씤
		if(reservation.getId().equals(reserveID))
			return 0;
		
		item.setIsExecuted(reservation.getIsExecuted());
		item.setReservationDate(reservation.getReservationDate());
		item.setReservationType(reservation.getReservationType());
		return 1;
	}
	//洹몃깷 �뜮�뼱�벐湲곗슜 �븿�닔(�쑀�슚�꽦 寃��궗 x)
	private byte settingInfo(Reservation item,String type,String date,byte is) {
		//議댁옱�빐�빞 �뜮�뼱�벖�떎.
		if(item ==null)
			return 0;
		
		item.setIsExecuted(is);
		item.setReservationDate(date);
		item.setReservationType(type);
		return 1;
	}
	public byte modifyIsReservation(String reserveID,String memberID,String type,String date,byte is) {
		Reservation item=searchReservationByID(reserveID);
		//�씠�윴嫄� �씠�젃寃� �떎 �빐�빞�븯�굹 �뀑�뀑 �삁�쇅濡� �궇由щ뒗寃� 留욌뒗嫄곌컳�떎.
		
		return settingInfo(item,type,date,is);
	}
	//�궘�젣�뒗 洹� �삁�빟愿� 愿��젴�맂 怨좉컼�씠�굹 蹂묒썝 �쉶�썝留� 媛��뒫�븯�떎.
	public byte delteReservationByID(String reservationID,String memberID) {
		Member member=memberManager.searchMemberByID(memberID);
		//議댁옱�븯吏� �븡�뒗 �쉶�썝�씪 寃쎌슦
		if(member==null)
			return 0;
		int index=searchReservationIndexByID(reservationID);
		//議댁옱�븯吏� �븡�뒗 �삁�빟�씪 寃쎌슦
		if(index < 0)
			return 0;
		Reservation item=searchReservationByIndex(index);
		//�삁�빟怨� 愿��젴�씠 �엳�뒗 �쉶�썝�씪 寃쎌슦
		if(item.getHospitalID().equals(memberID) || item.getCustomerID().equals(memberID)) {
			reservations.remove(index);
			return 1;
		}
		//�삁�빟 �떎�뙣�븳 寃쎌슦
		return 0;
	}
	
	public byte deleteReservationsBymemberID(String memberID,String type) {
		//議댁옱�븯吏� �쉶�썝�씤吏� �솗�씤
		if(memberManager.searchMemberByID(memberID) == null)
			return 0;
		
		for(int i=0;i<reservations.size();i++) {
			Reservation item=reservations.get(i);
			if("hospital".equals(type) && item.getHospitalID() == memberID) {
				reservations.remove(i);
			}
			else if("customer".equals(type) && item.getCustomerID() == memberID) {
				reservations.remove(i);
			}
			else;
		}
		
		return 1;
	}
	
	public byte addReservation(String hospitalID,String memberID,Reservation reservation) {
		//�쑀�슚�꽦 寃�利앺븯�뒗嫄� - 蹂묒썝id(蹂묒썝�� 鍮꾪쉶�썝�룄 媛��뒫�빐�꽌 議곌굔寃��궗 x),memberID �솗�씤
		//�븘�씠�뵒媛� 遺��젙�븷寃쎌슦 �삁�빟�씠 遺덇��뒫�븯�떎.
		if(memberManager.searchMemberByID(memberID)==null)
			return 0;
		reservations.add(reservation);
		return 1;
	}
}
