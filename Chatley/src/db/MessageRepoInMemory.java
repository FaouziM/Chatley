package db;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Bericht;
import model.Person;

public class MessageRepoInMemory {
	List<Bericht> berichten;
	List<Bericht> nieuweBerichten;
	
	public MessageRepoInMemory(){
		this.berichten = new ArrayList<Bericht>();
		this.nieuweBerichten = new ArrayList<Bericht>();
	}
	
	public void addOudBericht(Bericht b){//TESTING PURPOSES ONLY
		this.berichten.add(b);
	}
	
	public void addBericht(Bericht b){
		this.nieuweBerichten.add(b);
	}
	
	public String getNieuweBerichtenVanPartnerAlsJSON(Person partner, Person ik){
		if (nieuweBerichten.size() == 0){
			return getBerichtenAlsJSON(new ArrayList<Bericht>());
		}
		Iterator it = nieuweBerichten.iterator();
		List<Bericht> gematchteBerichten = new ArrayList<Bericht>();
		
		while (it.hasNext()){
			Bericht b = (Bericht) it.next();
			if (b.getZender().equals(partner) || b.getOntvanger().equals(partner)){
				berichten.add(b);
				gematchteBerichten.add(b);
			}
		}
		nieuweBerichten.removeAll(gematchteBerichten);
		
		return getBerichtenAlsJSON(gematchteBerichten);
	}
	
	public String getBerichtenVanPartnerAlsJSON(Person partner){
		Iterator it = berichten.iterator();
		List<Bericht> gematchteBerichten = new ArrayList<Bericht>();
		
		while (it.hasNext()){
			Bericht b = (Bericht) it.next();
			if (b.getZender().equals(partner) || b.getOntvanger().equals(partner)){
				gematchteBerichten.add(b);
			}
		}
		return getBerichtenAlsJSON(gematchteBerichten);
	}
	
	public String getBerichtenAlsJSON(){
		return getBerichtenAlsJSON(this.berichten);
	}
	
	public String getNieuweBerichtenAlsJSON(){
		List<Bericht> teZendenNieuweBerichten = this.nieuweBerichten;
		this.nieuweBerichten = new ArrayList<Bericht>();
		return getBerichtenAlsJSON(teZendenNieuweBerichten);
	}
	
	private String getBerichtenAlsJSON(List<Bericht> berichten){
		StringBuffer json = new StringBuffer();
		
		Iterator it = berichten.iterator();
		json.append("{ \"Bericht\":[");
		
		while (it.hasNext()) {
			Bericht b = (Bericht) it.next();
			
			json.append("{");
			json.append("\"zender\": \"" + b.getZender().getVolledigeNaam() + "\",");
			json.append("\"ontvanger\": \"" + b.getOntvanger().getVolledigeNaam() + "\"," );
			json.append("\"bericht\": \"" + b.getBericht() + "\"" );
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
