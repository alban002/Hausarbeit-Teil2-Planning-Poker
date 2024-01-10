package com.example.PlanningPoker_;

public interface UserStoryRepository {
	public UserStory findById(UserStoryId userStoryID);
	public void save(UserStory userStory);
	public boolean createUserStoryTable();
	void deleteById(int userStoryDBid);
}