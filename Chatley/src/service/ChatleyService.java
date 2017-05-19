package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import db.MessageRepoInMemory;
import db.PersonRepositoryInMemory;
import model.Bericht;
import model.Person;

public class ChatleyService {
	private PersonRepositoryInMemory personen;
	
	public ChatleyService(){
		personen = new PersonRepositoryInMemory();
		
		Person test1 = new Person("Fao", "t", new ArrayList<Person>());
		
		Person bob = new Person("Bob", "t", new ArrayList<Person>());
		test1.addVriend(bob);
		this.addPerson(test1);
		this.addPerson(bob);
		
		test1.addOudBericht(new Bericht("hoi", bob, test1));
		test1.addOudBericht(new Bericht("Hallo, hoe gaat ie?", test1, bob));
		
		bob.addOudBericht(new Bericht("hoi", bob, test1));
		bob.addOudBericht(new Bericht("Hallo, hoe gaat ie?", test1, bob));
		
		Person brampi = new Person("Brampi", "t", new ArrayList<Person>());
		Person yarno = new Person("Yarno", "t", new ArrayList<Person>());
		
		this.addPerson(yarno);
		this.addPerson(brampi);
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
	
	
	
	public String getVriendenAlsJSON(Person person){
		StringBuffer json = new StringBuffer();
		List<Person> vrienden = this.getPerson(person.getNaam()).getVrienden();
		Iterator it = vrienden.iterator();
		json.append("{ \"Person\":[");
		
		while (it.hasNext()) {
			Person vriend = (Person) it.next();
			
			json.append("{");
			json.append("\"naam\": \"" + vriend.getNaam() + "\",");
			json.append("\"status\": \"" + vriend.getStatus() + "\"" );
			json.append("}");
			if (it.hasNext()){
				json.append(",");
			}
		}
		json.append("]");
		json.append("}");
		System.out.println(json.toString());
		return json.toString();
	}
	
}
