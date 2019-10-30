package controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import service.HospitalService;

@Controller
public class HospitalController {
	@Autowired
	private HospitalService hospital;
	
	@RequestMapping(value="/listView.do")
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
		/*System.out.println(memberService.searchByID(id).getName());
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
		*/
		
 
		return attr;
	}
}
