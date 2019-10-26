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

}