package DB;

import java.io.Serializable;

public class AnimalDTO implements Serializable {
	private String name;
	private String species;
	private int age;
	private String adopt;
	private String birth;
	
	public AnimalDTO() {
		super();
	}
	public AnimalDTO(String name, String species, int age, String adopt, String birth) {
		super();
		this.name = name;
		this.species = species;
		this.age = age;
		this.adopt = adopt;
		this.birth = birth;
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
		return "AnimalDTO [name=" + name + ", species=" + species + ", age=" + age + ", adopt=" + adopt + ", birth=" + birth + "]";
	}
}
