package com.example.PlanningPoker_;

public interface UserStoryRepository {
	public UserStory findById(UserStoryId userStoryID);
	public void save(UserStory userStory);
}