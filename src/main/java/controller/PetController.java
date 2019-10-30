package controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import service.MemberService;

@Controller
public class PetController {
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="/petInfo.do")
	@ResponseBody
	public JSONObject loadHome(HttpSession session) {
		
		JSONObject attr=new JSONObject();
		System.out.println("넘어온 쿠키 id = "+session.getId());
		
		ServletContext context=session.getServletContext();
	
		
		//로그인된 아이디를 저장.
		if(session.getAttribute("id")!=null) {
			System.out.println(session.getAttribute("id"));
			String id=(String)session.getAttribute("id");
			
			attr.put("msg","ok");
			
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
