package com.example.PlanningPoker;

public class UserStory {
	
	UserStoryId userStoryId;
	String description;
	String title;
	int finalEstimation;
	

	
	public UserStory(UserStoryId userStoryId, String description, String title, int finalEstimation) {
		this.userStoryId = userStoryId;
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
	
	public void setFinalSstimation (int finalEstimation) {
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
