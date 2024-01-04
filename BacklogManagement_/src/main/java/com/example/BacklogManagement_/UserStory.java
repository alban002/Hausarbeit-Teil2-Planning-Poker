package com.example.BacklogManagement_;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserStory {
	
	UserStoryId userStoryId;
	String description;
	String title;
	int estimation;
	
	//Jackson-Annotationen verwenden, um dem Deserialisierer mitzuteilen, welchen Konstruktor er verwenden soll
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
		return description;
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
	
	
	public boolean equals (Object o) {
		UserStory a = (UserStory)o;
		return (this.userStoryId == a.userStoryId);
	}
	
	public int hashCode() {
		return userStoryId.getId(); 
	}
}
