package com.example.PlanningPoker;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;


public class PlanningPokerSessionEntity {

	@Id
	int id;
	LocalDateTime sessionStartZeitpunkt;
	boolean active = false;
	
	
	
	
	public PlanningPokerSessionEntity() {
		
	}
	public PlanningPokerSessionEntity(PlanningPokerSessionId planningPokerSessionId, LocalDateTime sessionStartZeitpunkt, boolean active) {
		this.id = planningPokerSessionId.getPlanningPokerSessionId();
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
	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public PlanningPokerSessionEntity(PlanningPokerSession pks) {
		this.id = pks.getPlanningPokerSessionId().getPlanningPokerSessionId();
		this.sessionStartZeitpunkt = pks.getSessionStartZeitpunkt();
		this.active = pks.isRunning();

	}
	
	public PlanningPokerSessionEntity toDomain() {
		return new PlanningPokerSessionEntity (new PlanningPokerSessionId (id), sessionStartZeitpunkt, active);
		
	}
	
	
	
	
}