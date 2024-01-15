package com.example.PlanningPoker_.adapter;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import com.example.PlanningPoker_.domain.UserStory;
import com.example.PlanningPoker_.domain.UserStoryId;

@Table("USERSTORYPP")
public class UserStoryEntity {
	
	/*CREATE TABLE USERSTORYPP (
		    USER_STORY_ID INT AUTO_INCREMENT PRIMARY KEY,
		    DESCRIPTION VARCHAR(255),
		    TITLE VARCHAR(255),
		    FINAL_ESTIMATION INT
		);*/

	@Id
	private int userStoryId; //Spalte in Datenbank muss USER_STORY_ID genannt werden und AUTO_INKREMENT aktiviert werden
	private String description;
	private String title;
	private int finalEstimation; //Hier ebenfalls mit Unterstrich FINAL_ESTIMATION 
	
	public UserStoryEntity() {}
	
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
		if(userStory.getUserStoryId()!=null) {
			this.userStoryId = userStory.getUserStoryId().getUserStoryId();	
		}
		this.description = userStory.getDescription();
		this.title = userStory.getTitle();
		this.finalEstimation = userStory.getFinalEstimation();
	}
	
	public UserStory toDomain() {
		return new UserStory (new UserStoryId (userStoryId), description, title, finalEstimation);
	}	
}