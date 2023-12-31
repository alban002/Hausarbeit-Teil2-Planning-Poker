package com.example.PlanningPoker;

public class Estimation {
	
	UserStoryId userStoryId;
	ParticipantId participantId;
	int estimationValue;
	
	public static int concatenateNumbers(int a, int b) {
        // Ziffern von a extrahieren
        int aDigit = 0;
        int aMultiplier = 1;
        while (a > 0) {
            aDigit = a % 10;
            a /= 10;
            aMultiplier *= 10;
        }

        // Ziffern von b extrahieren
        int bDigit = b % 10;

        // Zahlen zusammenfügen und zurückgeben
        return aMultiplier * bDigit + aDigit;
    }

	
	public Estimation(UserStoryId userStoryId, ParticipantId participantId, int estimationValue) {
		this.userStoryId = userStoryId;
		this.participantId = participantId;
		this.estimationValue = estimationValue;
	}

	
	
	public UserStoryId getStoryId() {
		return userStoryId;
	}



	public void setStoryId(UserStoryId userStoryId) {
		this.userStoryId = userStoryId;
	}



	public ParticipantId getParticipantId() {
		return participantId;
	}



	public void setParticipantId(ParticipantId participantId) {
		this.participantId = participantId;
	}



	public int getEstimationValue() {
		return estimationValue;
	}



	public void setEstimationValue(int estimationValue) {
		this.estimationValue = estimationValue;
	}



	public boolean equals (Object o) {
		Estimation a = (Estimation)o;
		return (this.userStoryId == a.userStoryId &&  this.participantId == a.participantId);
	}
	
	public int hashCode() {
		//einfach Ids hintereinander verketten
		return concatenateNumbers(userStoryId.getUserStoryId(), participantId.getparticipantId()) ; 
	}
}
