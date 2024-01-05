package com.example.BacklogManagement_;

import java.util.Collection;

public class BacklogManagementApplicationService implements BacklogManagementService {

//Genutzte Implementation ist  DbUserStoryRepository
public UserStoryRepository userStoryRepository;
	
	public BacklogManagementApplicationService(UserStoryRepository userStoryRepository) {
        this.userStoryRepository = userStoryRepository;
    }
	
	@Override
	public boolean userStoryUpdaten(UserStory updatedUserStory) {
		
		UserStory userStoryDB = userStoryRepository.findById(updatedUserStory.getUserStoryId());
		
 		if (userStoryDB == null)
 		{
 			return false;
 			
 		}
 		else {
 			userStoryRepository.save(updatedUserStory);
 			System.out.println("USER STORY mit ID" + updatedUserStory.getUserStoryId().getId()+ "wurde veraentert in Datenbank");
			return true; 		
			}
	}
	
	@Override
	public boolean userStoryErstellen(UserStory newUserStory) {
			
	 		userStoryRepository.save(newUserStory);
	 		
			return true; 		
		}

	@Override
	public UserStory getUserStoryById(int id) {
		UserStory userStory = userStoryRepository.findById(new UserStoryId(id));
 		return userStory;
	}

	@Override
	public boolean deleteUserStoryById(int id) {
		UserStory userStoryDB = userStoryRepository.findById(new UserStoryId(id));
		
 		if (userStoryDB == null)
 		{
 			return false;
 			
 		}
 		else {
 			userStoryRepository.deleteById(id);
			return true;
			}
	}
	
	/*
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
