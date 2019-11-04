package controller;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.SecurityContextProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import manager.MemberManager;
import model.Reservation;
import service.HospitalService;
import service.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private HospitalService hospitalService;
	@Autowired
	private MemberManager MemberManager;
	
	@RequestMapping(value="/login.do")
	@ResponseBody
	public JSONObject loadHome(HttpSession session,@RequestParam("id")String id,@RequestParam("password")String pwd) {
		
		System.out.println(id+"/"+pwd);
		
		JSONObject attr=new JSONObject();
		System.out.println("넘어온 쿠키 id = "+session.getId());
		
		ServletContext context=session.getServletContext();
		
		//로그인된 아이디를 저장.
		if(context.getAttribute(session.getId())==null) {
			System.out.println(id+"/"+pwd);
			//session.getServletContext().setAttribute(session.getId(), id);
			session.setAttribute("id",id);
		}else {
			//String sessionId=(String)context.getAttribute(session.getId());
			session.setAttribute("id", id);
		}
		System.out.println(memberService.searchByID(id).getName());
		if(memberService.login(id, pwd)==1) {
			if(memberService.searchByID(id).getName().contains("병원")) {
				System.out.println("병원인디");
				session.setAttribute("type", "hospital");
				attr.put("user","hospital");
			}else {
				session.setAttribute("type", "user");
				attr.put("user","user");
			}
			
			attr.put("msg","ok");
		}else {
			attr.put("msg", "fail");
		}
		
		return attr;
	}
	
	@RequestMapping(value="/mainView.do")
	@ResponseBody
	public JSONArray loadMain(HttpSession session) {
		
		JSONObject attr=new JSONObject();
		JSONArray ary=new JSONArray();
		
		System.out.println("넘어온 쿠키 id = "+session.getId());
		
		ServletContext context=session.getServletContext();
		
		//로그인된 아이디를 저장.
		if(session.getAttribute("id")!=null) {
			String id=(String)session.getAttribute("id");
			
			attr.put("msg","ok");
			
			if("hospital".equals(session.getAttribute("type"))) {
				
				attr.put("name",memberService.searchByID(id).getName());
				attr.put("address",memberService.searchByID(id).getAddress());
				attr.put("openTime",hospitalService.searchHospitalByName(memberService.searchByID(id).getName()).getOpenTime());
				attr.put("closeTime",hospitalService.searchHospitalByName(memberService.searchByID(id).getName()).getOpenTime());
				
				ary.add(attr);
				
				//중복이지만 우얄수없음
				ArrayList<Reservation> reservations=hospitalService.searchReservation(id);
				for(int i=0;i<reservations.size();++i) {
					JSONObject emp=new JSONObject();
					Reservation item=reservations.get(i);
					emp.put("id",item.getId());
					emp.put("type",this.memberService.searchAnimal(item.getCustomerID()).get(0).getSpecies());
					emp.put("time",hospitalService.searchReservation(id).get(i).getReservationDate());
					emp.put("careType",hospitalService.searchReservation(id).get(i).getReservationType());
					emp.put("name",this.memberService.searchAnimal(item.getCustomerID()).get(0).getName());
					
					ary.add(emp);
				}
			
			}else {
				attr.put("petName",memberService.searchAnimal(id).get(0).getName());
				attr.put("adopt",memberService.searchAnimal(id).get(0).getAdopt());
				attr.put("birth",memberService.searchAnimal(id).get(0).getBirth());
				attr.put("species",memberService.searchAnimal(id).get(0).getSpecies());
				
				ary.add(attr);
				System.out.println("id: "+id);
				for(int i=0;i<hospitalService.searchReservation(id).size();++i) {
					JSONObject emp=new JSONObject();
					Reservation item=hospitalService.searchReservation(id).get(i);
					emp.put("name",MemberManager.searchMemberByID(item.getHospitalID()).getName());
					emp.put("subName",hospitalService.searchReservation(id).get(i).getReservationType());
					emp.put("contents",hospitalService.searchReservation(id).get(i).getReservationDate());
					
					ary.add(emp);
				}
			
			}
			
			
		}else {
			System.out.println("PetInfo -> 세션getID가 없습니다.");
			attr.put("msg","off");
		}
		
		return ary;
	}
	
}
