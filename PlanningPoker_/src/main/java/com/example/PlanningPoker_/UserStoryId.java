package com.example.PlanningPoker_;

public class UserStoryId {
int id;

	
	public UserStoryId(int id) {
		this.id = id;
	}


	public int getUserStoryId() {
		return id;
	}
	
	
	public boolean equals (Object o) {
		UserStoryId a = (UserStoryId)o;
		return (this.id == a.id);
	}
	
	public int hashCode() {
		return this.id; 
	}
}
