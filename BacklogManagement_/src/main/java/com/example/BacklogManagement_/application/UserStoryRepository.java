package com.example.BacklogManagement_.application;

import com.example.BacklogManagement_.domain.UserStory;
import com.example.BacklogManagement_.domain.UserStoryId;

public interface UserStoryRepository {
	
	public UserStory findById(UserStoryId userStoryId);
	public void save(UserStory userStory);
	public void deleteById(int userStoryDBId);
	public boolean createUserStoryTable();

}
