package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class exController {
	@RequestMapping(value="/home",method=RequestMethod.GET)
	public String loadHome(Model m) {
		m.addAttribute("name","here?");
		
		return "home";
	}
}
