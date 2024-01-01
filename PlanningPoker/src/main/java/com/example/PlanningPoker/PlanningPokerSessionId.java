package com.example.PlanningPoker;

public class PlanningPokerSessionId {
int planningPokerSessionId;

	
	public PlanningPokerSessionId(int planningPokerSessionId) {
		this.planningPokerSessionId = planningPokerSessionId;
	}


	public int getPlanningPokerSessionId() {
		return planningPokerSessionId;
	}
	
	
	public boolean equals (Object o) {
		PlanningPokerSessionId a = (PlanningPokerSessionId)o;
		return (this.planningPokerSessionId == a.planningPokerSessionId);
	}
	
	public int hashCode() {
		return this.planningPokerSessionId; 
	}
}
