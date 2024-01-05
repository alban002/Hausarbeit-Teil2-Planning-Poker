package com.example.PlanningPoker_;

import java.util.Random;

public class PlanningPokerDomainService {
	
	//UserStoryRepository userStoryRepository;
	//MessageQueue messageQueue;
	
	public PlanningPokerDomainService (/*UserStoryRepository userStoryRepository, MessageQueue messageQueue*/) {
		//this.userStoryRepository = userStoryRepository;
		//this.messageQueue = messageQueue;
	}
	//HIER ZUFÄLLIG ENTSCHEIDEN, OB DIE FINALESTIMATION COTROOLERAUFRUF BERECHTIGT WAR PO ODER NORMAL

	public boolean berechtigungPruefen() {
	    Random random = new Random();
	    //Gibt zu 50% true und zu 50% false zurueck
	    return random.nextBoolean();
	}
	
	
	
	/*public boolean bestandArtikelAktualisieren (Bestellung bestellung) {
		
		
		boolean alleArtikelGefunden = true;
		boolean alleArtikelVerfuegbar = true;
		
		for (BestellItem item: bestellung.getItems()) {
				Artikel artikel = artikelRepository.findById(item.getArtikelId());
 			
 	 		if (artikel == null) {
 	 			alleArtikelGefunden = false;
 	 		}
 	 		else if (!artikel.auslagern(item.getMenge()))
 	 			alleArtikelVerfuegbar = false;
		}
		
		if (alleArtikelGefunden && alleArtikelVerfuegbar){
			
			for (BestellItem item: bestellung.getItems()) {
				Artikel artikel = artikelRepository.findById(item.getArtikelId());
				artikel.auslagern(item.getMenge());
				artikelRepository.save(artikel);
			}
			
 	 		
			DomainEvent bestellungAbgeschlossen = new DomainEvent ("bestellungAbgeschlossen", bestellung); 
			messageQueue.send(bestellungAbgeschlossen);
			
			return true;
		}
		return false;
	}*/
}

