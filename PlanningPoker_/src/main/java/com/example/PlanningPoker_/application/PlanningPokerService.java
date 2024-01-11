package com.example.PlanningPoker_.application;

import com.example.PlanningPoker_.adapter.FestlegungsversuchResult;

public interface PlanningPokerService {
	
	public FestlegungsversuchResult endgueltigeEstimationFestlegenKafka(int userStoryId, int finalEstimation);
	public FestlegungsversuchResult endgueltigeEstimationFestlegenRabbitMQ(int userStoryId, int finalEstimation);
	public boolean userStoryTabelleErstellen();
	public void populateUserStoryTable();
	public boolean deleteUserStoryById(int id);	
}
