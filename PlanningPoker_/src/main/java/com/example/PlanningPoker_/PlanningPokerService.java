package com.example.PlanningPoker_;

public interface PlanningPokerService {
	
	public FestlegungsversuchResult endgueltigeEstimationFestlegen(int userStoryId, int finalEstimation);
	public boolean userStoryTabelleErstellen();
	public void populateUserStoryTable();
	public boolean deleteUserStoryById(int id);
	
}
