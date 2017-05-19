package model;

import java.util.ArrayList;
import java.util.List;

import db.MessageRepoInMemory;

public class Person {
	private String naam;
	private String wachtwoord;
	private String status;
	private List<Person> vrienden;
	private MessageRepoInMemory berichten; //alle berichten die deze persoon heeft verzonden en ontvangen

	public Person(String naam, String wachtwoord, List<Person> vrienden) {
		this.naam = naam;
		this.wachtwoord = wachtwoord;
		this.status = "Online";
		if (vrienden == null) {
			this.vrienden = new ArrayList<Person>();
		} else {
			this.vrienden = vrienden;
		}
		this.berichten = new MessageRepoInMemory();
	}

	public void addBericht(Bericht b){
		this.berichten.addBericht(b);
	}
	
	public void addOudBericht(Bericht b){//TESTING PURPOSES ONLY
		this.berichten.addOudBericht(b);
	}
	
	public String getBerichtenVanPartnerAlsJSON(Person partner){
		return this.berichten.getBerichtenVanPartnerAlsJSON(partner);
	}
	
	public String getNieuweBerichtenVanPartnerAlsJSON(Person partner){
		return this.berichten.getNieuweBerichtenVanPartnerAlsJSON(partner);
	}
	
	public String getAlZijnBerichtenAlsJSON(){
		return this.berichten.getBerichtenAlsJSON();
	}
	
	public String getNaam() {
		return this.naam;
	}

	public boolean isPassCorrect(String poging) {
		return (poging.equals(this.wachtwoord));
	}

	public List<Person> getVrienden() {
		return this.vrienden;
	}

	public void addVriend(Person person) {
		if ((this.equals(person))) {
			throw new DomainException("Dit bent uzelf!");
		}
		if (!this.vrienden.contains(person)) {
			this.vrienden.add(person);
			person.addBijVriendenlijst(this);
		} else {
			throw new DomainException("U bent al vrienden!");
		}
	}

	private void addBijVriendenlijst(Person person) {
		this.vrienden.add(person);
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean equals(Object o) {
		if (!(o instanceof Person)) {
			return false;
		}
		Person andere = (Person) o;
		return (andere.getNaam().equalsIgnoreCase(this.getNaam()));
	}
}
