package com.example.PlanningPoker;

public interface UserStoryRepository {
	public UserStory findById(UserStoryId userStoryID);
	public void save(UserStory userStory);
}
