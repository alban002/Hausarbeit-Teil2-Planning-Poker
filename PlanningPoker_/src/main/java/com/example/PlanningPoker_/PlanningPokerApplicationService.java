package com.example.PlanningPoker_;

import java.util.Collection;
import java.util.Random;



public class PlanningPokerApplicationService implements PlanningPokerService{

	
	private UserStoryRepository userStoryRepository;
	private PlanningPokerDomainService planningPokerDomainService;
	
	public PlanningPokerApplicationService (UserStoryRepository userStoryRepository, PlanningPokerDomainService planningPokerDomainService) {
		this.userStoryRepository = userStoryRepository;
		this.planningPokerDomainService = planningPokerDomainService;
	}

	@Override
	public boolean endgueltigeEstimationFestlegen(int userStoryId, int finalEstimation) {
		boolean istBerechtigt= planningPokerDomainService.berechtigungPruefen();
		
		if(istBerechtigt) {
			//Prüfen ob eine solche Story existiert
			//lediglich den wert von finalestimation ändern
			//asynchroneKommunikation zum anderen Service
			
			return true;
		}else {
			return false;
		}
		
	}


	
	
	// In dieser Klasse müsste viel mehr passieren:
	// Zahlungsabwicklung anstoßen
	// Lieferungsweg klären
	// ...
	
	
	/*public String zeigeBestand(int id) {
		
		Artikel artikel = artikelRepository.findById(new ArtikelId(id));
			
	 	if (artikel == null)
	 		return "-1";
	 	
	 	return artikel.bewerteBestand();
	}
	
	
	@Override
	public boolean bestellen(Collection<BestellItemTO> itemliste) {
		// Es folgt ein Dummy Implementierung
		// insb. ist 4711 eine Dummy-Bestellnummer, hier müsste die nächste Bestellnummer ermittelt werden
			Bestellung bestellung = new Bestellung (4711);
			for (BestellItemTO item: itemliste)
				bestellung.addItem(item.toDomain());

		// Hier wird der Bestand im Webshop entsprechend reduziert
			
			return shopDomainService.bestandArtikelAktualisieren(bestellung);
			
		
	}*/
	
	


}
