package service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;

import manager.AnimalManager;
import manager.MemberManager;
import model.Animal;
import model.Member;

public class MemberService {

	private MemberManager memberManager;
	private AnimalManager animalManager;
	
	
	public MemberService() {
		super();
		this.memberManager=new MemberManager();
		this.animalManager=new AnimalManager();
	}


	public MemberService(MemberManager memberManager, AnimalManager animalManager) {
		super();
		this.memberManager = memberManager;
		this.animalManager = animalManager;
	}


	public MemberManager getMemberManager() {
		return memberManager;
	}
	public void setMemberManager(MemberManager memberManager) {
		this.memberManager = memberManager;
	}
	public AnimalManager getAnimalManager() {
		return animalManager;
	}
	public void setAnimalManager(AnimalManager animalManager) {
		this.animalManager = animalManager;
	}
	
	public byte join(String id, String password,String name,String address,String phoneNum) {
		if(this.memberManager.searchMemberByID(id)!=null)return 0;
		
		this.memberManager.addMember(new Member(id,password,name,address,phoneNum));
		return 1;
	}
	public byte login(String id, String password) {
		Member member=this.memberManager.searchMemberByID(id);
		if(member==null)return 0;
		else if(!member.getPassword().equals(password))return 0;
		
		return 1;
	}
	public Member searchByID(String id) {
		return this.memberManager.searchMemberByID(id);
	}
	public ArrayList<Member> searchByName(String name){
		return this.memberManager.searchMemberByName(name);
	}
	public ArrayList<Member> searchByAddress(String address){
		return this.memberManager.searchMemberByAddress(address);
	}
	public byte withdrawMember(String id) {
		return this.memberManager.deleteMember(id);
	}
	public byte modifyMember(String id,String password,String name,String address,String phoneNum) {
		if(searchByID(id)==null)return 0;
		return this.memberManager.modifyMember(id,new Member(id,password,name,address,phoneNum));
	}
	public Animal searchAnimal(String id,String name) {
		return this.animalManager.searchAnimal(id, name);
	}
	public ArrayList<Animal> searchAnimal(String id){
		return this.animalManager.searchAnimalByID(id);
	}
	public byte modifyAnimal(String id,Animal animal) {
		return this.animalManager.modifyAnimal(id, animal);
	}
	public int calcuDday(String id, String name) {
		String birth=searchAnimal(id,name).getBirth();
		StringTokenizer tokens=new StringTokenizer(birth,"/");
		String[] list=new String[tokens.countTokens()];
		int i=0;
		while(tokens.hasMoreElements()) {
			list[i++]=tokens.nextToken();
		}
		
		Calendar cal=Calendar.getInstance();
		Calendar dayCal=Calendar.getInstance();
		
		int year=cal.get(cal.YEAR);
		int month=cal.get(cal.MONTH)+1;
		int birthMonth=0;
		int birthDate=0;
		
		try {
			birthMonth=Integer.parseInt(list[1]);
			birthDate=Integer.parseInt(list[2]);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(birthMonth<month) {
			year++;
		}
		
		dayCal.set(year,birthMonth,birthDate);
		
		long birthday=dayCal.getTimeInMillis()/(24*60*60*1000);
		long curday=cal.getTimeInMillis()/(24*60*60*1000);
		return Math.abs((int)(birthday-curday));
	}
	public byte deleteAnimal(String id,String name) {
		return this.deleteAnimal(id, name);
	}
	public byte addAnimal(String id, String name,String birth,String adoptDay,String species,String age) {
		return this.animalManager.addAnimal(id, new Animal(name, species, Integer.parseInt(age), adoptDay, birth));
	}
}
