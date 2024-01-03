package com.example.PlanningPoker;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class PlanningPokerSession {
	
	PlanningPokerSessionId planningPokerSessionId;
	LocalDateTime sessionStartZeitpunkt;
	boolean active;
	
	public PlanningPokerSession(PlanningPokerSessionId planningPokerSessionId, LocalDateTime sessionStartZeitpunkt, boolean active) {
		this.planningPokerSessionId =planningPokerSessionId;
		this.sessionStartZeitpunkt = sessionStartZeitpunkt;
		this.active = active;
	}
	

	public PlanningPokerSessionId getPlanningPokerSessionId(){
		return planningPokerSessionId;
	}
	
	public void setPlanningPokerSessionId(PlanningPokerSessionId planningPokerSessionId) {
		this.planningPokerSessionId = planningPokerSessionId;
	}

	public LocalDateTime getSessionStartZeitpunkt() {
		return sessionStartZeitpunkt;
	}

	public void setSessionStartZeitpunkt(LocalDateTime sessionStartZeitpunkt) {
		this.sessionStartZeitpunkt = sessionStartZeitpunkt;
	}

	public boolean isActive() {
		return active;
	}

	public void startSession() {
		this.active = true;
		this.sessionStartZeitpunkt= LocalDateTime.now();
	}
	
	public void stopSession() {
		this.active = false;
	}

	public boolean equals (Object o) {
		PlanningPokerSession a = (PlanningPokerSession)o;
		return (this.sessionStartZeitpunkt == a.sessionStartZeitpunkt);
	}
	
	public int hashCode() {
		return (int) sessionStartZeitpunkt.toEpochSecond(ZoneOffset.UTC); 
	}

}
