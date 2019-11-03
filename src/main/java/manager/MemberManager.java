package manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.springframework.stereotype.Service;

import model.Member;

@Service
public class MemberManager {
	private HashMap<String,Member> members;


	public MemberManager() {
		super();
		this.members=new HashMap<String,Member>();
		
		//하드데이터 일단 박아넣기 나중에 지어
				this.members.put("aaa", new Member("aaa","1234", "박성일", "서울중랑구", "010-1111-1111"));
				this.members.put("bbb", new Member("bbb","1234", "이은호", "서울중랑구", "010-1111-1111"));
				this.members.put("ccc", new Member("ccc","1234", "최우석", "서울중랑구", "010-1111-1111"));
				this.members.put("ddd", new Member("ddd","1234", "xx병원", "서울중랑구", "010-1111-1111"));this.members.put("ddd", new Member("ddd","1234", "xx병원", "서울중랑구", "010-1111-1111"));
				this.members.put("xxx", new Member("xxx","1234", "N동물의료센터 노원점", "서울특별시 노원구 노원로 456 백암빌딩", "02-919-0075"));
	}
	public MemberManager(HashMap<String, Member> members) {
		super();
		this.members = members;
	}
	
	public ArrayList<Member> search(String attribute, String data) {
		ArrayList<Member> ret = new ArrayList<Member>();
		if (attribute == "id") {		// if primaryKey
			Member ent = members.get(data);
			if (ent != null) {
				ret.add(ent);
			}
			return ret;
		}
		Iterator<String> keys=members.keySet().iterator();
		while (keys.hasNext()) {
			String key = keys.next();
			Member ent = members.get(key);
			if (ent != null) {
				String src = ent.get(attribute, data);
				if (src != null) {
					if (src.contains(data)) {
						ret.add(ent);
					}
				}
			}
		}
		return ret;
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
			if(mem.getName().equals(name)) {
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