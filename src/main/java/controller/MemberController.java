package controller;

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

import service.HospitalService;
import service.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private HospitalService hospitalService;
	
	@RequestMapping(value="/login.do")
	@ResponseBody
	public JSONObject loadHome(HttpSession session,@RequestParam("id")String id,@RequestParam("password")String pwd) {
		System.out.println(id+"/"+pwd);
		
		JSONObject attr=new JSONObject();
		System.out.println("�Ѿ�� ��Ű id = "+session.getId());
		
		ServletContext context=session.getServletContext();
		
		//�α��ε� ���̵� ����.
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
			if(memberService.searchByID(id).getName().contains("����")) {
				System.out.println("�����ε�");
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
		
		System.out.println("�Ѿ�� ��Ű id = "+session.getId());
		
		ServletContext context=session.getServletContext();
		
		//�α��ε� ���̵� ����.
		if(session.getAttribute("id")!=null) {
			System.out.println(session.getAttribute("id"));
			String id=(String)session.getAttribute("id");
			
			attr.put("msg","ok");
			
			
			
			if("hospital".equals(session.getAttribute("type"))) {
				attr.put("name",memberService.searchByID(id).getName());
				attr.put("address",memberService.searchByID(id).getAddress());
				attr.put("openTime",hospitalService.searchHospitalByName(memberService.searchByID(id).getName()).getOpenTime());
				attr.put("closeTime",hospitalService.searchHospitalByName(memberService.searchByID(id).getName()).getOpenTime());
				
				ary.add(attr);
				
				//�ߺ������� ��������
				for(int i=0;i<hospitalService.searchReservation(id).size();++i) {
					JSONObject emp=new JSONObject();
					
					emp.put("id",hospitalService.searchReservation(id).get(i).getHospitalName());
					emp.put("type",this.memberService.searchAnimal(hospitalService.searchReservation(id).get(i).getHospitalName()).get(0).getSpecies());
					emp.put("time",hospitalService.searchReservation(id).get(i).getReservationDate());
					emp.put("careType",hospitalService.searchReservation(id).get(i).getReservationType());
					emp.put("name",this.memberService.searchAnimal(hospitalService.searchReservation(id).get(i).getHospitalName()).get(0).getName());
					
					ary.add(emp);
				}
			
			}else {
				attr.put("petName",memberService.searchAnimal(id).get(0).getName());
				attr.put("adopt",memberService.searchAnimal(id).get(0).getAdopt());
				attr.put("birth",memberService.searchAnimal(id).get(0).getBirth());
				attr.put("species",memberService.searchAnimal(id).get(0).getSpecies());
				
				ary.add(attr);
				
				
				for(int i=0;i<hospitalService.searchReservation(id).size();++i) {
					JSONObject emp=new JSONObject();
					
					emp.put("name",hospitalService.searchReservation(id).get(i).getHospitalName());
					emp.put("subName",hospitalService.searchReservation(id).get(i).getReservationType());
					emp.put("contents",hospitalService.searchReservation(id).get(i).getReservationDate());
					
					ary.add(emp);
				}
			
			}
			
			
		}else {
			System.out.println("PetInfo -> ����getID�� �����ϴ�.");
			attr.put("msg","off");
		}
		
		return ary;
	}
	
}
