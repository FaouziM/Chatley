package db;

import java.util.ArrayList;
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
		if (this.personen.containsKey(person.getNaam())){
			throw new DBException("Deze user bestaat al!");
		}
		this.personen.put(person.getNaam(), person);
	}
	
	public Person getPerson(String naam){
		Person output = null;
		output =  this.personen.get(naam);
		if (output == null){
			throw new DBException("User niet gevonden!");
		}
		return output;
	}
	
	public List<Person> getAllePersonenBehalve(Person person){
		List<Person> output = new ArrayList<Person>();
		for (String p : personen.keySet()) {
			if (!(person.getNaam().equals(p))){
				output.add(this.personen.get(p));
			}
		}
		return output;
	}
	
	public Person getPersonAlsWachtwoordCorrect(String username, String wachtwoord){
		Person p = null;
		try{
		p = this.getPerson(username);
		}catch (DBException e){
			throw new DBException("Usernaam of wachtwoord is verkeerd!");
		}
		if (p.isPassCorrect(wachtwoord)){
			return p;
		}
		else {
			throw new DBException("Usernaam of wachtwoord is verkeerd!");
		}
	}
}
