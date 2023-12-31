package com.example.PlanningPoker;

public class Participant {
	
	ParticipantId participantId;
	String alias;
	//Umentscheidung nicht als value object, sondern als String-Attribut
	String role;
	

	
	public Participant(ParticipantId participantId, String alias, String role) {
		this.participantId = participantId;
		this.alias = alias;
		this.role = role;
	}
	
	
	public ParticipantId getParticipantId () {
		return participantId;
	}
	
	public void aliasAngeben (String alias) {
		this.alias = alias;
	}
	
	public void rolleZuweisen (String role) {
		this.role = role;
	}
		
	public String getAlias () {
		return alias;
	}
	
	public String getRole () {
		return role;
	}
	
	public boolean equals (Object o) {
		Participant a = (Participant)o;
		return (this.participantId == a.participantId);
	}
	
	public int hashCode() {
		return participantId.getparticipantId(); 
	}
}
