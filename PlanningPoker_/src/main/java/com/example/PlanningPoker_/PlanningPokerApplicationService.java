package com.example.PlanningPoker_;

public class PlanningPokerApplicationService implements PlanningPokerService{

	private UserStoryRepository userStoryRepository;
	private PlanningPokerDomainService planningPokerDomainService;
	
	public PlanningPokerApplicationService (UserStoryRepository userStoryRepository, PlanningPokerDomainService planningPokerDomainService) {
		this.userStoryRepository = userStoryRepository;
		this.planningPokerDomainService = planningPokerDomainService;
	}
	
	@Override
	public FestlegungsversuchResult endgueltigeEstimationFestlegen(int userStoryId, int finalEstimation) {
	    boolean istBerechtigt = planningPokerDomainService.berechtigungPruefen();

	    if (!istBerechtigt) {
	        return FestlegungsversuchResult.PERMISSION_DENIED;
	    }

	    UserStory userStory = userStoryRepository.findById(new UserStoryId(userStoryId));

	    if (userStory == null) {
	        return FestlegungsversuchResult.USER_STORY_NOT_FOUND;
	    }

	    userStory.setFinalEstimation(finalEstimation);
	    userStoryRepository.save(userStory);

	    //asynchroneKommunikation zum anderen Service

	    return FestlegungsversuchResult.SUCCESS;
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
