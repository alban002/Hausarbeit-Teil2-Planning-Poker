package com.example.PlanningPoker;

public class EstimationlId {
int estimationId;

	
	public EstimationlId(int estimationId) {
		this.estimationId = estimationId;
	}


	public int getestimationId() {
		return estimationId;
	}
	
	
	public boolean equals (Object o) {
		EstimationlId a = (EstimationlId)o;
		return (this.estimationId == a.estimationId);
	}
	
	public int hashCode() {
		return this.estimationId; 
	}
	

}
