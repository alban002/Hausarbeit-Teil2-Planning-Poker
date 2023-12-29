package com.example.BacklogManagement;

public interface UserStoryRepository {
	
	public UserStory findById(UserStoryId userStoryId);
	public void save(UserStory userStory);

}
