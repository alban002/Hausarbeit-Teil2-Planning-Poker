package com.example.BacklogManagement_;

public interface BacklogManagementService {

	public boolean userStoryUpdaten(UserStory updatedUserStory);
	public boolean userStoryErstellen(UserStory newUserStory);
	public UserStory getUserStoryById(int id);
	public boolean deleteUserStoryById(int id);
	public boolean userStoryTabelleErstellen();
}
