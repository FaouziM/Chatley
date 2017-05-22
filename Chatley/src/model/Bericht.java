package model;

import java.util.Date;

public class Bericht {
	private String bericht;
	private Person zender;
	private Person ontvanger;
	private Date datum;
	
	public Bericht(String bericht, Person zender, Person ontvanger){
		this.bericht = bericht;
		this.zender = zender;
		this.ontvanger = ontvanger;
		this.datum = new Date();
	}
	public Person getZender(){
		return this.zender;
	}
	
	public String getBericht(){
		return this.bericht;
	}
	
	public Person getOntvanger(){
		return this.ontvanger;
	}
	
	public Date getDate(){
		return this.datum;
	}
	
	public boolean equals(Object o){
		if (!(o instanceof Bericht)){
			return false;
		}
		Bericht andere = (Bericht) o;
		if (this.getDate().equals(andere.getDate())){
			return true;
		}
		return false;
	}
	
}
