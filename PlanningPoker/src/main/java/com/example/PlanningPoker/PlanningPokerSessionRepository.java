package com.example.PlanningPoker;

public interface PlanningPokerSessionRepository {
	public PlanningPokerSession findById(PlanningPokerSessionId planningPokerSessionId);
	public void save(PlanningPokerSession planningPokerSession);
}
