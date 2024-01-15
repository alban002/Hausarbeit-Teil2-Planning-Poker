package com.example.BacklogManagement_.domain;

public class UserStoryId {
    
    private int id;
    
    // Konstruktor, um eine User Story-ID mit einer gegebenen ID zu erstellen.
    public UserStoryId(int id) {
        this.id = id;
    }
    
    // Getter-Methode, um die ID abzurufen.
    public int getId() {
        return id;
    }
    
    // Überschreiben der equals-Methode für die Vergleichbarkeit von UserStoryId-Objekten.
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserStoryId userStoryId = (UserStoryId) o;
        return id == userStoryId.id;
    }
    
    // Überschreiben der hashCode-Methode für die Verwendung von UserStoryId-Objekten in Hash-Tabellen.
    public int hashCode() {
        return id; 
    }
}