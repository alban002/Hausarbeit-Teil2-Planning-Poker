package com.example.PlanningPoker_.application;

import com.example.PlanningPoker_.adapter.FestlegungsversuchResult;

public interface PlanningPokerService {
	
	public FestlegungsversuchResult endgueltigeEstimationFestlegenKafka(int userStoryId, int finalEstimation, String Rolle);
	public FestlegungsversuchResult endgueltigeEstimationFestlegenRabbitMQ(int userStoryId, int finalEstimation, String Rolle);
	public boolean userStoryTabelleErstellen();
	public void populateUserStoryTable();
	public boolean deleteUserStoryById(int id);	
}
