package db;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Person;

public class PersonRepositoryInMemory {
	private Map<String, Person> personen;
	
	public PersonRepositoryInMemory(){
		this.personen = new HashMap<String, Person>();
	}
	
	public void addPerson(Person person){
		if (this.personen.containsKey(person.getEmail())){
			throw new DBException("Deze user bestaat al!");
		}
		this.personen.put(person.getEmail().toLowerCase(), person);
	}
	
	public Person getPersonMetVolledigeNaam(String volNaam){
		Person output = null;
		Collection<Person> personenTemp = this.personen.values();
		for (Person person : personenTemp) {
			if (person.getVolledigeNaam().equals(volNaam)){
				output = person;
			}
		}
		if (output == null){
			throw new DBException("User " + volNaam +  " niet gevonden!");
		}
		return output;
	}
	
	public Person getPerson(String email){
		Person output = null;
		output =  this.personen.get(email.toLowerCase());
		if (output == null){
			throw new DBException("User " + email +  " niet gevonden!");
		}
		return output;
	}
	
	public List<Person> getAllePersonenBehalve(Person person){
		List<Person> output = new ArrayList<Person>();
		for (String p : personen.keySet()) {
			if (!(person.getEmail().equals(p))){
				output.add(this.personen.get(p));
			}
		}
		return output;
	}
	
	public Person getPersonAlsWachtwoordCorrect(String email, String wachtwoord){
		Person p = null;
		try{
		p = this.getPerson(email.toLowerCase());
		}catch (DBException e){
			throw new DBException("Email of wachtwoord is verkeerd!");
		}
		if (p.isPassCorrect(wachtwoord)){
			return p;
		}
		else {
			throw new DBException("Email of wachtwoord is verkeerd!");
		}
	}
}
