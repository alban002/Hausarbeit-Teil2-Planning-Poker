package com.example.PlanningPoker;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class PlanningPokerSession {
	
	LocalDateTime sessionStartZeitpunkt;
	boolean active;
	
	public PlanningPokerSession(LocalDateTime sessionStartZeitpunkt, boolean active) {
		this.sessionStartZeitpunkt = sessionStartZeitpunkt;
		this.active = active;
	}

	public LocalDateTime getSessionStartZeitpunkt() {
		return sessionStartZeitpunkt;
	}

	public void setSessionStartZeitpunkt(LocalDateTime sessionStartZeitpunkt) {
		this.sessionStartZeitpunkt = sessionStartZeitpunkt;
	}

	public boolean isRunning() {
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
