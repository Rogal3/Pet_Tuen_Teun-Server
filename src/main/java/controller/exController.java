package controller;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import model.Animal;
import model.Hospital;
import model.Member;
import model.Reservation;
import service.HospitalService;
import service.MemberService;

@Controller
public class exController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private HospitalService hospitalService;

	@RequestMapping(value="/home",method=RequestMethod.GET)
	public String loadHome(Model m) {
		m.addAttribute("name","here?");
		System.out.println("asdfasdf");
		return "home";
	}
	
	@RequestMapping(value="/hosDetail.do")
	@ResponseBody
	public JSONObject loadHome(HttpSession session,@RequestParam("hospitalName")String hospitalName) {
		System.out.println("디테일뷰 값 : "+hospitalName);
		
		JSONObject attr=new JSONObject();
		System.out.println("넘어온 쿠키 id = "+session.getId());
		
		ServletContext context=session.getServletContext();
		
		//로그인된 아이디를 저장.
		if(session.getAttribute("id")!=null) {
			System.out.println(session.getAttribute("id"));
			String id=(String)session.getAttribute("id");
			
			attr.put("msg","ok");
			ArrayList<Member> mem=this.memberService.searchByName(hospitalName);
			if(mem!=null) {
				if(mem.size()>0) {
					attr.put("existence","ok");
					attr.put("name",mem.get(0).getName());
					attr.put("location",mem.get(0).getAddress());
					attr.put("phone",mem.get(0).getPhoneNum());
				}else {
					attr.put("existence","no");
				}
			}
			
		}else {
			attr.put("msg","no");
		}
		
		
		return attr;
	}
	
	@RequestMapping(value="/join.do")
	@ResponseBody
	public JSONObject joinUser(@RequestParam("id")String id,@RequestParam("password")String password,
			@RequestParam("name")String name,@RequestParam("phoneNum")String phoneNum,@RequestParam("pet_name")String petName,
			@RequestParam("pet_birth")String petBirth,@RequestParam("adopt_day")String adoptDay,@RequestParam("pet_age")String petAge
			,@RequestParam("species")String species) {
		
		System.out.println(id+" "+password+" "+name+" "+phoneNum+" "+petName+" "+petBirth+" "+adoptDay+" "+species+" "+petAge);
		JSONObject attr=new JSONObject();
		
		
		byte j=this.memberService.join(id, password, name, "", phoneNum);
		byte a=this.memberService.addAnimal(id, petName, petBirth, adoptDay, species, petAge);
		
		if(j==1) {
			System.out.println("조인은 완료");
			attr.put("msg","ok");
			if(a==1) {
				attr.put("petMsg","ok");
			}else {
				attr.put("petMsg","no");
			}
		}else {
			attr.put("msg","no");
		}
		
		return attr;
	}
	
	@RequestMapping(value="/addReserve.do")
	@ResponseBody
	public JSONObject reserveAdd(@RequestParam("id")String id,@RequestParam("hospitalName")String hospitalName,
			@RequestParam("reserveInfo")String reserveInfo,@RequestParam("reserveTime")String reserveTime) {
		
		System.out.println(id);
		JSONObject attr=new JSONObject();
		
		if(this.memberService.searchByID(id)!=null) {
			attr.put("msg","ok");
			String hospitalID=this.memberService.searchByName(hospitalName).get(0).getId();
			hospitalService.addReservation(hospitalID, id, new Reservation(hospitalID, hospitalID, id, reserveInfo, reserveTime, (byte)0));
		}else {
			attr.put("msg","no");
		}
		
	
		
		return attr;
	}
	@RequestMapping(value="/petModify.do")
	@ResponseBody
	public JSONObject petModify(@RequestParam("id")String id,@RequestParam("pet_name")String petName,
			@RequestParam("pet_birth")String petBirth,@RequestParam("adopt_day")String adoptDay,@RequestParam("pet_age")String petAge
			,@RequestParam("species")String species) {
		
		System.out.println(id);
		JSONObject attr=new JSONObject();
		
		if(this.memberService.searchByID(id)!=null) {
			attr.put("msg","ok");
			byte a=memberService.modifyAnimal(id,this.memberService.searchAnimal(id).get(0).getName(), new Animal(petName, species, Integer.parseInt(petAge), adoptDay, petBirth));
			System.out.println(a);
		}else {
			attr.put("msg","no");
		}
		
	
		
		return attr;
	}
	@RequestMapping(value="/userCancel.do")
	@ResponseBody
	public String userCancel(@RequestParam("removeID")String removeid,@RequestParam("memberID")String id) {
		
		int result;
		System.out.println(removeid);
		result=this.hospitalService.deleteReservationVer2(removeid,id);	
		System.out.println(result);
		if(result == 1)
			return "ok";
		else
			return "false";
	}
	@RequestMapping(value="/joinHos.do")
	@ResponseBody
	public JSONObject joinHos(@RequestParam("id")String id,@RequestParam("password")String password,
			@RequestParam("name")String name,@RequestParam("phoneNum")String phoneNum) {
		
		JSONObject attr=new JSONObject();
		
		byte j=this.memberService.join(id, password, name,"", phoneNum);
		this.hospitalService.addHospital(new Hospital(name, "", phoneNum, "09:00-09:00", "09:00-09:00"));
		if(j==1) {
			attr.put("msg","ok");
		}else {
			attr.put("msg","no");
		}
		return attr;
	}
	
}
