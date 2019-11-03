package controller;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import VO.ReservationCancelVO;
import VO.testVO;
import service.HospitalService;
import service.ReservationService;

@Controller
public class HospitalController {
	@Autowired
	private HospitalService hospitalService;
	@Autowired
	private ReservationService ReservationService;
	@RequestMapping(value="/cancel.do")
	@ResponseBody//일단 이거 넣어야 string이나 json으로 전달되네
	public String cancelReserve(ReservationCancelVO vo){
		//삭제 로직을 넣어야한다.
		int result;
		System.out.println(vo.getMemberID()+"/"+vo.getReserveID());
		result=ReservationService.deleteReservation(vo.getReserveID(),vo.getMemberID());	
		System.out.println(result);
		if(result == 1)
			return "ok";
		else
			return "false";
	}
	
	@RequestMapping(value="/listView.do")
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
		/*System.out.println(memberService.searchByID(id).getName());
		if(memberService.login(id, pwd)==1) {
			if(memberService.searchByID(id).getName().contains("병원")) {
				System.out.println("병원인디");
				attr.put("user","hospital");
			}else {
				attr.put("user","user");
			}
			
			attr.put("msg","ok");
		}else {
			attr.put("msg", "fail");
		}
		*/
		
 
		return attr;
	}
}
