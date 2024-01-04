package com.example.PlanningPoker;

public class PlanningPokerRoundId {
int planningPokerRoundId;

	
	public PlanningPokerRoundId(int planningPokerRoundId) {
		this.planningPokerRoundId = planningPokerRoundId;
	}


	public int getPlanningPokerRoundId() {
		return planningPokerRoundId;
	}
	
	
	public boolean equals (Object o) {
		PlanningPokerRoundId a = (PlanningPokerRoundId)o;
		return (this.planningPokerRoundId == a.planningPokerRoundId);
	}
	
	public int hashCode() {
		return this.planningPokerRoundId; 
	}
}
