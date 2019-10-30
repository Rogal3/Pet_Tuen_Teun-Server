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
				attr.put("user","hospital");
			}else {
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
	public JSONObject loadMain(HttpSession session) {
		
		JSONObject attr=new JSONObject();
		System.out.println("넘어온 쿠키 id = "+session.getId());
		
		ServletContext context=session.getServletContext();
		
		//로그인된 아이디를 저장.
		if(session.getAttribute("id")!=null) {
			System.out.println(session.getAttribute("id"));
			String id=(String)session.getAttribute("id");
			
			attr.put("msg","ok");
			
			JSONArray ary=new JSONArray();
			
			ary.add(attr);
			
			//hospitalService.
			
			attr.put("petName",memberService.searchAnimal(id).get(0).getName());
			attr.put("adopt",memberService.searchAnimal(id).get(0).getAdopt());
			attr.put("birth",memberService.searchAnimal(id).get(0).getBirth());
			attr.put("species",memberService.searchAnimal(id).get(0).getSpecies());
		}else {
			System.out.println("PetInfo -> 세션getID가 없습니다.");
			attr.put("msg","off");
		}
		
		return attr;
	}
	
}
