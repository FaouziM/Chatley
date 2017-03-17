package model;

import java.util.List;

public class Person {
	private String naam;
	private String wachtwoord;
	private String status;
	private List<Person> vrienden;
	
	public Person(String naam, String wachtwoord, List<Person> vrienden){
		this.naam = naam.toLowerCase();
		this.wachtwoord = wachtwoord;
		this.status = "Online";
	}
	
	public String getNaam(){
		return this.naam;
	}
	
	public boolean isPassCorrect(String poging){
		return(poging.equals(this.wachtwoord));
	}
	
	public List<Person> getVrienden(){
		return this.vrienden;
	}
	
	public void addVriend(Person person){
		if(!this.vrienden.contains(person)){
		this.vrienden.add(person);
		person.addBijVriendenlijst(this);
		}else {
			throw new DomainException("U bent al vrienden!");
		}
	}
	
	private void addBijVriendenlijst(Person person){
		this.vrienden.add(person);
	}
	
	public String getStatus(){
		return this.status;
	}
	
	public void setStatus(String status){
		this.status = status;
	}
	
	public boolean equals(Object o){
		if (!(o instanceof Person)){
			return false;
		}
		Person andere = (Person) o;
		return (andere.getNaam().equalsIgnoreCase(this.getNaam()));
	}
}
