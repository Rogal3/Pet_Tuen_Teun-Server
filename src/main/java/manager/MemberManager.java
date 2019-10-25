package manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import model.Member;

public class MemberManager {
	private HashMap<String,Member> members;

	
	public MemberManager() {
		super();
		this.members=new HashMap<String,Member>();
	}
	public MemberManager(HashMap<String, Member> members) {
		super();
		this.members = members;
	}
	public HashMap<String, Member> getMembers() {
		return members;
	}
	public void setMembers(HashMap<String, Member> members) {
		this.members = members;
	}
	
	public Member searchMemberByID(String id) {
		return members.get(id);
	}
	public ArrayList<Member> searchMemberByAddress(String address) {
		Iterator<String> keys=members.keySet().iterator();
		ArrayList<Member> member=null;
		if(keys.hasNext())member=new ArrayList<Member>();
		else return null;
		
		while(keys.hasNext()) {
			String key=keys.next();
			Member mem=members.get(key);
			if(mem.getAddress().contains(address)) {
				member.add(mem);
			}
		}
		return member;
	}
	public ArrayList<Member> searchMemberByName(String name){
		Iterator<String> keys=members.keySet().iterator();
		ArrayList<Member> member=null;
		if(keys.hasNext())member=new ArrayList<Member>();
		else return null;
		
		while(keys.hasNext()) {
			String key=keys.next();
			Member mem=members.get(key);
			if(mem.getAddress().contains(name)) {
				member.add(mem);
			}
		}
		return member;
	}
	public byte modifyMember(String id, Member member) {
		if(searchMemberByID(member.getId())==null)return 0;
		
		this.members.put(id, member);
		
		return 1;
	}
	public byte modifyPassword(String id,String password) {
		if(searchMemberByID(id)==null)return 0;
		
	
		return this.members.get(id).modifyPassword(password);
	}
	public byte deleteMember(String id) {
		Member member=searchMemberByID(id);
		if(member==null)return 0;
		else {
			this.members.remove(id);
		}
		return 1;
	}
	public byte addMember(Member member) {
		if(searchMemberByID(member.getId())!=null)return 0;
		
		this.members.put(member.getId(),member);
		return 1;
	}
	
}
