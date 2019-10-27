package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemberController {

	@RequestMapping(value="/login.do",method=RequestMethod.POST)
	public Map<String,String> login(HttpServletRequest request) {
		System.out.println(request.getAttribute("id"));
		System.out.println(request.getAttribute("password"));
		
		
		/*JSONObject attr=new JSONObject();
		attr.put("msg","ok");*/
		
		Map<String,String> attr=new HashMap<String,String>();
		attr.put("msg","ok");
		
		return attr;
	}
}
