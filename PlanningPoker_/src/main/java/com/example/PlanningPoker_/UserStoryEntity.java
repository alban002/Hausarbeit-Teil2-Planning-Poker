package com.example.PlanningPoker_;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("USERSTORY")
public class UserStoryEntity {

	@Id
	int userStoryId;
	String description;
	String title;
	int finalEstimation;
	
	public UserStoryEntity() {
		
	}
	
	
	
	public int getUserStoryId() {
		return userStoryId;
	}



	public void setUserStoryId(int userStoryId) {
		this.userStoryId = userStoryId;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return description;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public int getFinalEstimation() {
		return finalEstimation;
	}



	public void setFinalEstimation(int estimation) {
		this.finalEstimation = estimation;
	}


	public UserStoryEntity (UserStory userStory) {
		this.userStoryId = userStory.getUserStoryId().getUserStoryId();
		this.description = userStory.getDescription();
		this.title = userStory.getTitle();
		this.finalEstimation = userStory.getFinalEstimation();
	}
	
	public UserStory toDomain() {
		return new UserStory (new UserStoryId (userStoryId), description, title, finalEstimation);
		
	}
	
	
	
	
}