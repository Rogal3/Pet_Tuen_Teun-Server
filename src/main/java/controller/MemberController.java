package controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.SecurityContextProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import service.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	
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
			session.getServletContext().setAttribute(session.getId(), id);
		}else {
			String sessionId=(String)context.getAttribute(session.getId());
			System.out.println("�̹� �α��ε� ���̵� : "+sessionId);
		}
		System.out.println(memberService.searchByID(id).getName());
		if(memberService.login(id, pwd)==1) {
			if(memberService.searchByID(id).getName().contains("����")) {
				System.out.println("�����ε�");
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
	
	
}
