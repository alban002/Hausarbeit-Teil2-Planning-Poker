package com.example.BacklogManagement_;

import java.util.Collection;

public class BacklogManagementApplicationService implements BacklogManagementService {

public UserStoryRepository userStoryRepository;
	
	public BacklogManagementApplicationService(UserStoryRepository userStoryRepository) {
        this.userStoryRepository = userStoryRepository;
    }
	/*
	@Override
	public boolean artikelEinlagern(int id, int menge) {
		
		Artikel artikel = artikelRepository.findById(new ArtikelId(id));
		
 		if (artikel == null)
 			return false;
 		else {
 			artikel.einlagern(menge);
 			artikelRepository.save(artikel);
 			 		
 			return true;
 		}
	}
		
	@Override
	public boolean artikelAuslagern(int id, int menge) {
		
		Artikel artikel = artikelRepository.findById(new ArtikelId(id));
		
 		if (artikel == null)
 			return false;
 		
 		if (artikel.auslagern(menge)) {
 			artikelRepository.save(artikel);
 			return true;
 		}
 		
 		return false;
		
	}
	@Override
	public int bestandErmitteln(int id) {
	
		Artikel artikel = artikelRepository.findById(new ArtikelId(id));
		
 		if (artikel == null)
 			return -1;
 		else {
 			return artikel.getBestand();
 		}
	}
	
	@Override
	public boolean bestellungVerarbeiten (Collection<BestellItemTO> bestelllisteTO) {
		
		// Hier muss viel passieren, z.B. eine Bestellung anlegen, prüfen und speichern.
		// Hier nur das auslagern
		// Bemerkung: Wenn man hier eine Bestellung anlegt, dann gehört der folgende Code besser in einen DomainService
		
		boolean alleArtikelGefunden = true;
		boolean alleArtikelVerfuegbar = true;
		
		for (BestellItemTO item: bestelllisteTO) {
				Artikel artikel = artikelRepository.findById(new ArtikelId(item.getId()));
 			
 	 		if (artikel == null) {
 	 			alleArtikelGefunden = false;
 	 		}
 	 		else if (!artikel.auslagern(item.getMenge()))
 	 			alleArtikelVerfuegbar = false;
		}
		
		if (alleArtikelGefunden && alleArtikelVerfuegbar){
			for (BestellItemTO item: bestelllisteTO) {
				Artikel artikel = artikelRepository.findById(new ArtikelId(item.getId()));
				artikel.auslagern(item.getMenge());
				artikelRepository.save(artikel);
			}
		}		
 	 		
		return 	alleArtikelGefunden && alleArtikelVerfuegbar;
			
	}*/
	
	
}
