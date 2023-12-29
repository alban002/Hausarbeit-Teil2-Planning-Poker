package com.example.BacklogManagement;

public class UserStory {
	
	UserStoryId userStoryId;
	String description;
	String title;
	int estimation;
	

	
	public UserStory(UserStoryId userStoryId, String description, String title, int estimation) {
		this.userStoryId = userStoryId;
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
