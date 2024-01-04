package com.example.PlanningPoker;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class PlanningPokerRound {
	
	PlanningPokerRoundId planningPokerRoundId;
	LocalDateTime roundStartZeitpunkt;
	boolean active;
	
	public PlanningPokerRound(PlanningPokerRoundId planningPokerRoundId, LocalDateTime roundStartZeitpunkt, boolean active) {
		this.planningPokerRoundId =planningPokerRoundId;
		this.roundStartZeitpunkt = roundStartZeitpunkt;
		this.active = active;
	}
	

	public PlanningPokerRoundId getPlanningPokerRoundId(){
		return planningPokerRoundId;
	}
	
	public void setPlanningPokerRoundId(PlanningPokerRoundId planningPokerRoundId) {
		this.planningPokerRoundId = planningPokerRoundId;
	}

	public LocalDateTime getRoundStartZeitpunkt() {
		return roundStartZeitpunkt;
	}

	public void setRoundStartZeitpunkt(LocalDateTime roundStartZeitpunkt) {
		this.roundStartZeitpunkt = roundStartZeitpunkt;
	}

	public boolean isActive() {
		return active;
	}

	public void startRound() {
		this.active = true;
		this.roundStartZeitpunkt= LocalDateTime.now();
	}
	
	public void stopRound() {
		this.active = false;
	}

	public boolean equals (Object o) {
		PlanningPokerRound a = (PlanningPokerRound)o;
		return (this.roundStartZeitpunkt == a.roundStartZeitpunkt);
	}
	
	public int hashCode() {
		return (int) roundStartZeitpunkt.toEpochSecond(ZoneOffset.UTC); 
	}

}
