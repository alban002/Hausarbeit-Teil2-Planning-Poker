package com.example.PlanningPoker_.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserStory implements Serializable{
	
	//Kompatibilität der Serialisierung festhalten
	private static final long serialVersionUID = 1L;
	
	UserStoryId userStoryId;
	String title;
	String description;
	int finalEstimation;
	
	/*public UserStory(UserStoryId userStoryId, String description, String title, int finalEstimation) {
		this.userStoryId = userStoryId;
		this.description = description;
		this.title = title;
		this.finalEstimation = finalEstimation;
	}*/
	
	//Jackson-Annotationen verwenden, um dem Deserialisierer mitzuteilen, welchen Konstruktor er verwenden soll
	@JsonCreator
	public UserStory(@JsonProperty("userStoryId") UserStoryId userStoryId, 
			@JsonProperty("description") String description,
			@JsonProperty("title") String title,
			@JsonProperty("estimation") int finalEstimation) {
		this.userStoryId = userStoryId;
		this.description = description;
		this.title = title;
		this.finalEstimation = finalEstimation;
	}
	
	public UserStory( String description, String title, int finalEstimation) {
		this.userStoryId = null;
		this.description = description;
		this.title = title;
		this.finalEstimation = finalEstimation;
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
		return description;
	}
	
	public void titelAnpassen (String title) {
		this.title = title;
	}
	
	public int getFinalEstimation () {
		return finalEstimation;
	}
	
	public void setFinalEstimation (int finalEstimation) {
		this.finalEstimation = finalEstimation;
	}
	
	public boolean equals (Object o) {
		UserStory a = (UserStory)o;
		return (this.userStoryId == a.userStoryId);
	}
	
	public int hashCode() {
		return userStoryId.getUserStoryId(); 
	}
}