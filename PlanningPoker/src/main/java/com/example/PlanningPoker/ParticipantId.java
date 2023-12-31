package com.example.PlanningPoker;

public class ParticipantId {
int participantId;

	
	public ParticipantId(int id) {
		this.participantId = id;
	}


	public int getparticipantId() {
		return participantId;
	}
	
	
	public boolean equals (Object o) {
		ParticipantId a = (ParticipantId)o;
		return (this.participantId == a.participantId);
	}
	
	public int hashCode() {
		return this.participantId; 
	}
}
