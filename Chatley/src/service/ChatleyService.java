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
		
		Person fao = new Person("M", "Fao", "fao@gmail.com", 'm', 21, "t", "t");
		Person bob = new Person("Q", "Bob", "bob@gmail.com", 'm', 22, "t", "t");
		Person brampi = new Person("C", "Brampi", "brampi@gmail.com", 'm', 21, "t", "t");
		Person yarno = new Person("C", "Yarno", "yarno@gmail.com", 'm', 20, "t", "t");
		
		this.addPerson(yarno);
		this.addPerson(brampi);
		this.addPerson(fao);
		this.addPerson(bob);
		
		fao.addVriend(bob);
		
		fao.addOudBericht(new Bericht("Well I'm finding it harder to be a gentleman every day.", bob, fao));
		fao.addOudBericht(new Bericht("Have all the manners that you've been taught slowly died away?", fao, bob));
		
		bob.addOudBericht(new Bericht("Well I'm finding it harder to be a gentleman every day.", bob, fao));
		bob.addOudBericht(new Bericht("Have all the manners that you've been taught slowly died away?", fao, bob));
		
		fao.addOudBericht(new Bericht("But if I'd held the door open for you it wouldn't make your day!", bob, fao));
		bob.addOudBericht(new Bericht("But if I'd held the door open for you it wouldn't make your day!",  bob, fao));
		
	}
	
	public Person getPerson(String email){
		return personen.getPerson(email.toLowerCase());
	}
	
	public List<Person> getAllePersonenBehalve(Person person){
		return personen.getAllePersonenBehalve(person);
	}
	
	public void addPerson(Person person){
		personen.addPerson(person);
	}
	
	public Person getPersonAlsWachtwoordCorrect(String email, String wachtwoord){
		return personen.getPersonAlsWachtwoordCorrect(email, wachtwoord);
	}
	
	public String getVriendenAlsJSON(Person person){
		StringBuffer json = new StringBuffer();
		List<Person> vrienden = this.getPerson(person.getEmail()).getVrienden();
		Iterator it = vrienden.iterator();
		json.append("{ \"Person\":[");
		
		while (it.hasNext()) {
			Person vriend = (Person) it.next();
			
			json.append("{");
			json.append("\"naam\": \"" + vriend.getVolledigeNaam() + "\",");
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

	public void addPerson(String naam, String voornaam, String email, char geslacht, int leeftijd, String passwoord,
			String passwoord2) {
		Person p = new Person(naam, voornaam, email.toLowerCase(), geslacht, leeftijd, passwoord, passwoord2);
		this.personen.addPerson(p);
		
	}

	public Person getPersonMetVolledigeNaam(String volNaam) {
		return this.personen.getPersonMetVolledigeNaam(volNaam);
	}
	
}
