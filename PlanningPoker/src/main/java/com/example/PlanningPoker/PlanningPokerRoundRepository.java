package com.example.PlanningPoker;

public interface PlanningPokerRoundRepository {
	public PlanningPokerRound findById(PlanningPokerRoundId planningPokerRoundId);
	public void save(PlanningPokerRound planningPokerRound);
}
