package com.example.PlanningPoker_.domain;

public class UserStoryId {
	
	private int id;

	// Konstruktor, um eine User Story-ID mit einer gegebenen ID zu erstellen.
	public UserStoryId(int id) {
		this.id = id;
	}

	// Getter-Methode, um die ID abzurufen.
	public int getUserStoryId() {
		return id;
	}
	
	// Überschreiben der equals-Methode für die Vergleichbarkeit von UserStoryId-Objekten.
	public boolean equals (Object o) {
		UserStoryId a = (UserStoryId)o;
		return (this.id == a.id);
	}
	
	// Überschreiben der hashCode-Methode für die Verwendung von UserStoryId-Objekten in Hash-Tabellen.
	public int hashCode() {
		return this.id; 
	}
}
