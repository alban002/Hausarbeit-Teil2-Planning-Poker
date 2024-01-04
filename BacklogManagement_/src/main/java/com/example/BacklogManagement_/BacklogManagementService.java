package com.example.BacklogManagement_;

import java.util.Collection;

public interface BacklogManagementService {

	public boolean userStoryUpdaten(UserStory updatedUserStory);
	public boolean userStoryErstellen(UserStory newUserStory);
	public UserStory getUserStoryById(int id);
	public boolean deleteUserStoryById(int id);

	/*public boolean artikelEinlagern(int id, int menge);
	public boolean artikelAuslagern(int id, int menge);
	public int bestandErmitteln(int Id);
	public boolean bestellungVerarbeiten (Collection<BestellItemTO> bestelllisteTO);*/
	
}
