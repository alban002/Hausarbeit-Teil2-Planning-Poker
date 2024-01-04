package com.example.PlanningPoker;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;


public class PlanningPokerRoundEntity {

	@Id
	int planningPokerRoundId;
	LocalDateTime roundStartZeitpunkt;
	boolean active = false;
	
	
	
	
	public PlanningPokerRoundEntity() {
		
	}
	public PlanningPokerRoundEntity(PlanningPokerRoundId planningPokerRoundId, LocalDateTime roundStartZeitpunkt, boolean active) {
		this.planningPokerRoundId = planningPokerRoundId.getPlanningPokerRoundId();
		this.roundStartZeitpunkt = roundStartZeitpunkt;
		this.active = active;
		
	}
	
	public LocalDateTime getRoundStartZeitpunkt() {
		return roundStartZeitpunkt;
	}



	public void setRoundStartZeitpunkt(LocalDateTime roundStartZeitpunkt) {
		this.roundStartZeitpunkt = roundStartZeitpunkt;
	}
	public boolean getActive() {
		return active;
	}



	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	public int getPlanningPokerRoundIdId() {
		return planningPokerRoundId;
	}



	public void setPlanningPokerRoundId(int id) {
		this.planningPokerRoundId = id;
	}



	public PlanningPokerRoundEntity(PlanningPokerRound pks) {
		this.planningPokerRoundId = pks.getPlanningPokerRoundId().getPlanningPokerRoundId();
		this.roundStartZeitpunkt = pks.getRoundStartZeitpunkt();
		this.active = pks.isActive();

	}
	
	public PlanningPokerRound toDomain() {
		return new PlanningPokerRound (new PlanningPokerRoundId (planningPokerRoundId), roundStartZeitpunkt, active);
		
	}
	
	
	
	
}