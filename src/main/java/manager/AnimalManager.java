package manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.springframework.stereotype.Service;

import model.Animal;
@Service
public class AnimalManager {
	/**
	 * key1=owner key2 name
	 */
	private HashMap<String,HashMap<String,Animal>> animals;
	
	public AnimalManager() {
		super();
this.animals=new HashMap<String,HashMap<String,Animal>>();
		
		this.animals.put("aaa", new HashMap<String,Animal>());
		this.animals.get("aaa").put("콩이", new Animal("콩이","강아지",1,"2018/10/30","2018/10/30"));
		
		this.animals.put("bbb", new HashMap<String,Animal>());
		this.animals.get("bbb").put("쭌이", new Animal("콩이","햄스터",2,"2018/10/31","2017/5/30"));
		
		this.animals.put("ccc", new HashMap<String,Animal>());
		this.animals.get("ccc").put("뭉이", new Animal("콩이","아르마딜로",5,"2018/10/30","2014/10/30"));
		
	}
	public AnimalManager(HashMap<String, HashMap<String, Animal>> animals) {
		super();
		this.animals = animals;
	}
	public HashMap<String, HashMap<String, Animal>> getAnimals() {
		return animals;
	}
	public void setAnimals(HashMap<String, HashMap<String, Animal>> animals) {
		this.animals = animals;
	}
	
	public Animal searchAnimal(String id, String name) {
		return this.animals.get(id).get(name);
	}
	public ArrayList<Animal> searchAnimalByID(String id){
		if(this.animals.get(id)==null)return null;
		HashMap<String,Animal> list=this.animals.get(id);
		ArrayList<Animal> animal=new ArrayList<Animal>();
		
		Iterator<String> keys=this.animals.get(id).keySet().iterator();
		while(keys.hasNext()) {
			animal.add(list.get(keys.next()));
		}
		
		return animal;
	}
	public byte modifyAnimal(String id,Animal animal) {
		if(searchAnimal(id, animal.getName())==null)return 0;
		
		this.animals.get(id).put(animal.getName(), animal);
		return 1;
	}
	public byte deleteAnimal(String id, String name) {
		if(searchAnimal(id, name)==null)return 0;
		this.animals.get(id).remove(name);
		return 1;
	}
	public  byte deleteAnimal(String id) {
		if(this.animals.get(id)==null)return 0;
		this.animals.remove(id);
		return 1;
	}
	public byte addAnimal(String id,Animal animal) {
		if(this.animals.get(id)==null) {
			this.animals.put(id,new HashMap<String,Animal>());
		}
		if(searchAnimal(id, animal.getName())!=null)return 0;
		
		if(this.animals.get(id)==null) {
			this.animals.put(id, new HashMap<String,Animal>());
		}
		this.animals.get(id).put(animal.getName(), animal);
		return 1;
	}
}
