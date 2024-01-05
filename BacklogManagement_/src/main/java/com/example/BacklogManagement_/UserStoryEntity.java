package com.example.BacklogManagement_;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("USERSTORY")
public class UserStoryEntity {

	@Id
	int userStoryId; //Spalte in Datenbank muss USER_STORY_ID genannt werden und AUTO_INCREMENT aktiviert sein
	String description;
	String title;
	int estimation;
	
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


	public int getEstimation() {
		return estimation;
	}



	public void setEstimation(int estimation) {
		this.estimation = estimation;
	}


	public UserStoryEntity (UserStory userStory) {
		if (userStory.getUserStoryId() != null) {
			this.userStoryId = userStory.getUserStoryId().getId();
		}
		this.description = userStory.getDescription();
		this.title = userStory.getTitle();
		this.estimation = userStory.getEstimation();
	}
	
	public UserStory toDomain() {
		return new UserStory (new UserStoryId (userStoryId), description, title, estimation);
		
	}
	
	
	
	
}