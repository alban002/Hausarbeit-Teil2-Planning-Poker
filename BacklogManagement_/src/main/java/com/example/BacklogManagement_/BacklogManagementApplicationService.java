package com.example.BacklogManagement_;

public class BacklogManagementApplicationService implements BacklogManagementService {

//Genutzte Implementation ist  DbUserStoryRepository
public UserStoryRepository userStoryRepository;
	
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

}
