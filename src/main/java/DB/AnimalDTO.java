package DB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AnimalDTO implements Serializable {
	private String id;
	private String owner;
	private String name;
	private String species;
	private int age;
	private String adopt;
	private String birth;
	
	public AnimalDTO() {
		super();
	}
	
	public AnimalDTO(String id, String owner, String name, String species, int age, String adopt, String birth) {
		super();
		this.id = id;
		this.owner = owner;
		this.name = name;
		this.species = species;
		this.age = age;
		this.adopt = adopt;
		this.birth = birth;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAdopt() {
		return adopt;
	}
	public void setAdopt(String adopt) {
		this.adopt = adopt;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}

	@Override
	public String toString() {
		return "AnimalDTO [id=" + id + ", owner=" + owner + ", name=" + name + ", species=" + species + ", age=" + age
				+ ", adopt=" + adopt + ", birth=" + birth + "]";
	}
	
//	public List<String> getAttributes() {
//		ArrayList<String> list = new ArrayList<String>();
//		list.add("id");
//		list.add("owner");
//		list.add("name");
//		list.add("species");
//		list.add("age");
//		list.add("adopt");
//		list.add("birth");
//		return list;
//	}
//	public String getAttribute(String attribute) {
//		switch (attribute) {
//		case "id":
//			return getId();
//		case "owner":
//			return getOwner();
//		case "name":
//			return getName();
//		case "species":
//			return getSpecies();
//		case "age":
//			return Integer.toString(getAge());
//		case "adopt":
//			return getAdopt();
//		case "birth":
//			return getBirth();
//		default:
//			return null;
//		}
//	}
}
