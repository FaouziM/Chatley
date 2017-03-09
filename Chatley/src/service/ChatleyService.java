package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.PersonRepositoryInMemory;
import model.Person;

public class ChatleyService {
	private PersonRepositoryInMemory personen;
	
	public ChatleyService(){
		personen = new PersonRepositoryInMemory();
		List<Person> vrienden = new ArrayList<>();
		vrienden.add(new Person("Bob", "t", new ArrayList<Person>()));
		
		Person test1 = new Person("Fao", "t", vrienden);
		this.addPerson(test1);
	}
	
	public Person getPerson(String naam){
		return personen.getPerson(naam);
	}
	
	public List<Person> getAllePersonenBehalve(Person person){
		return personen.getAllePersonenBehalve(person);
	}
	
	public void addPerson(Person person){
		personen.addPerson(person);
	}
	
	public Person getPersonAlsWachtwoordCorrect(String username, String wachtwoord){
		return personen.getPersonAlsWachtwoordCorrect(username, wachtwoord);
	}
	
}
