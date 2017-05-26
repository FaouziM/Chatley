package model;

import java.util.ArrayList;
import java.util.List;

import db.MessageRepoInMemory;

public class Person {
	private String naam;
	private String voornaam;
	private String email;
	private char geslacht;
	private int leeftijd;
	
	private String wachtwoord;
	private String status;
	private List<Person> vrienden;
	private MessageRepoInMemory berichten; //alle berichten die deze persoon heeft verzonden en ontvangen

	public Person(String naam, String voornaam, String email, char geslacht, int leeftijd, String passwoord, String pass2) {
		setNaam(naam);
		setVoornaam(voornaam);
		setEmail(email);
		setGeslacht(geslacht);
		setLeeftijd(leeftijd);
		setWachtwoord(passwoord, pass2);
		this.berichten = new MessageRepoInMemory();
		this.status = "Online";
		this.vrienden = new ArrayList<Person>();
	}

	public String getVoornaam() {
		return voornaam;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public char getGeslacht() {
		return geslacht;
	}

	public void setGeslacht(char geslacht) {
		this.geslacht = geslacht;
	}

	public int getLeeftijd() {
		return leeftijd;
	}

	public void setLeeftijd(int leeftijd) {
		this.leeftijd = leeftijd;
	}

	private String getWachtwoord() {
		return wachtwoord;
	}

	public void setWachtwoord(String wachtwoord , String pass2) {
		if (!wachtwoord.equals(pass2)){
			throw new IllegalArgumentException("Passwoorden niet gelijk");
		}
		this.wachtwoord = wachtwoord;
	}

	public void setNaam(String naam) {
		if (naam == null || naam.trim().isEmpty()){
			throw new IllegalArgumentException("Naam is leeg");
		}
		this.naam = naam;
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
	
	public String getNieuweBerichtenVanPartnerAlsJSON(Person partner, Person ik){
		String output = this.berichten.getNieuweBerichtenVanPartnerAlsJSON(partner, ik);
		System.out.println("Get Nbericht van : " + partner.getVolledigeNaam() + " " + output);
		return output; 
	}
	
	public String getAlZijnBerichtenAlsJSON(){
		return this.berichten.getBerichtenAlsJSON();
	}
	
	public String getVolledigeNaam() {
		return this.voornaam + " " + this.naam;
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
		return (andere.getEmail().equalsIgnoreCase(this.getEmail()));
	}
}
