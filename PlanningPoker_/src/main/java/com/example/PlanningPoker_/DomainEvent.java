package com.example.PlanningPoker_;

public class DomainEvent {

	String event;
	Object object;
	
	public DomainEvent(String event, Object object) {
		super();
		this.event = event;
		this.object = object;
	}

	public String getEvent() {
		return event;
	}

	public Object getObject() {
		return object;
	}
	
	
	
	
	

}
