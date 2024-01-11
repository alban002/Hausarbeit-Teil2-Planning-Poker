package com.example.BacklogManagement_.application;

import com.example.BacklogManagement_.domain.UserStory;
import com.example.BacklogManagement_.domain.UserStoryId;

public class BacklogManagementApplicationService implements BacklogManagementService {

//Genutzte Implementation ist  DbUserStoryRepository
private UserStoryRepository userStoryRepository;
	
	public BacklogManagementApplicationService(UserStoryRepository userStoryRepository) {
        this.userStoryRepository = userStoryRepository;
    }
	
	public boolean userStoryTabelleErstellen() {
		return userStoryRepository.createUserStoryTable();
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
 			System.out.println("H2_INFO: UserStory mit ID " + updatedUserStory.getUserStoryId().getId()+ " wurde veraendert in Datenbank");
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

	@Override
	public void populateUserStoryTable() {
		UserStory userStory1 = new UserStory("ERSTEBeschreibung","ERSTERTitel", 2);
		UserStory userStory2 = new UserStory("ZWEITEBeschreibung","ZWEITERTitel", 8);
		UserStory userStory3 = new UserStory("DRITTEBeschreibung","DRITTERTitel", 9);
		userStoryRepository.save(userStory1);
		userStoryRepository.save(userStory2);
		userStoryRepository.save(userStory3);
	}

}