package com.example.PlanningPoker;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;


public class PlanningPokerSessionEntity {

	@Id
	int planningPokerSessionId;
	LocalDateTime sessionStartZeitpunkt;
	boolean active = false;
	
	
	
	
	public PlanningPokerSessionEntity() {
		
	}
	public PlanningPokerSessionEntity(PlanningPokerSessionId planningPokerSessionId, LocalDateTime sessionStartZeitpunkt, boolean active) {
		this.planningPokerSessionId = planningPokerSessionId.getPlanningPokerSessionId();
		this.sessionStartZeitpunkt = sessionStartZeitpunkt;
		this.active = active;
		
	}
	
	public LocalDateTime getSessionStartZeitpunkt() {
		return sessionStartZeitpunkt;
	}



	public void setSessionStartZeitpunkt(LocalDateTime sessionStartZeitpunkt) {
		this.sessionStartZeitpunkt = sessionStartZeitpunkt;
	}
	public boolean getActive() {
		return active;
	}



	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	public int getPlanningPokerSessionIdId() {
		return planningPokerSessionId;
	}



	public void setPlanningPokerSessionId(int id) {
		this.planningPokerSessionId = id;
	}



	public PlanningPokerSessionEntity(PlanningPokerSession pks) {
		this.planningPokerSessionId = pks.getPlanningPokerSessionId().getPlanningPokerSessionId();
		this.sessionStartZeitpunkt = pks.getSessionStartZeitpunkt();
		this.active = pks.isActive();

	}
	
	public PlanningPokerSession toDomain() {
		return new PlanningPokerSession (new PlanningPokerSessionId (planningPokerSessionId), sessionStartZeitpunkt, active);
		
	}
	
	
	
	
}