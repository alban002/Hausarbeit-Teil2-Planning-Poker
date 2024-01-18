package com.example.BacklogManagement_.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserStory {
    
    private UserStoryId userStoryId;
    private String description;
    private String title;
    private int estimation;
    
    // Eine Methode zur Berechnung der Gesamt-Schätzung aller User Stories in einer Liste.
    public static int berechneGesamteEstimation(List<UserStory> userStories) {
        int gesamteEstimation = 0;
        for (UserStory userStory : userStories) {
            gesamteEstimation += userStory.getEstimation();
        }
        return gesamteEstimation;
    }
    
    // Konstruktor mit Jackson-Annotationen, um die Deserialisierung aus JSON zu ermöglichen.
    @JsonCreator
    public UserStory(@JsonProperty("userStoryId") UserStoryId userStoryId, 
            @JsonProperty("description") String description,
            @JsonProperty("title") String title,
            @JsonProperty("estimation") int estimation) {
        this.userStoryId = userStoryId;
        this.description = description;
        this.title = title;
        this.estimation = estimation;
    }
    
    // Konstruktor zum Erstellen einer neuen User Story ohne vorherige ID.
    public UserStory( String description, String title, int estimation) {
        this.userStoryId = null;
        this.description = description;
        this.title = title;
        this.estimation = estimation;
    }

    public UserStoryId getUserStoryId () {
        return userStoryId;
    }
    
    public void beschreibungAnpassen (String description) {
        this.description = description;
    }
    
    public String getDescription(){
        return description;
    }
    
    public String getTitle(){
        return title;
    }
    
    public void titelAnpassen (String title) {
        this.title = title;
    }
    
    public int getEstimation () {
        return estimation;
    }
    
    public void estimate (int estimation) {
        this.estimation = estimation;
    }
    
    // Überschreiben der equals-Methode für die Vergleichbarkeit von User Stories.
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserStory userStory = (UserStory) o;
        return userStoryId.equals(userStory.userStoryId);
    }
    
    // Überschreiben der hashCode-Methode für die Verwendung bei User Stories
    public int hashCode() {
        return userStoryId.hashCode(); 
    }
}
