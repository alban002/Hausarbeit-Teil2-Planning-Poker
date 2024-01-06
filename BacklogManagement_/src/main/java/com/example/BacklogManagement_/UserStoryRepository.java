package com.example.BacklogManagement_;

public interface UserStoryRepository {
	
	public UserStory findById(UserStoryId userStoryId);
	public void save(UserStory userStory);
	public void deleteById(int userStoryDBId);
	public boolean createUserStoryTable();

}
