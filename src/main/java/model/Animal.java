package model;

public class Animal {
	private String name;
	private String species;
	private int age;
	private String adopt;
	private String birth;
	
	public Animal() {
		super();
	}
	public Animal(String name, String species, int age, String adopt, String birth) {
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
	public byte plusAge() {
		this.age++;
		return 1;
	}
	public byte modifyName(String name) {
		this.name=name;
		return 1;
	}
	public byte modifySpecies(String species) {
		this.species=species;
		return 1;
	}
	
}
