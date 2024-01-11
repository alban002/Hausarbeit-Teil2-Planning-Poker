package com.example.PlanningPoker_.application;

import com.example.PlanningPoker_.domain.UserStory;
import com.example.PlanningPoker_.domain.UserStoryId;

public interface UserStoryRepository {
	public UserStory findById(UserStoryId userStoryID);
	public void save(UserStory userStory);
	public boolean createUserStoryTable();
	public void deleteById(int userStoryDBid);
}