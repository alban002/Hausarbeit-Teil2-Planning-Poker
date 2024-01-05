package com.example.PlanningPoker_;

public interface MessageQueue {
	
	public boolean send(DomainEvent domainEvent);

}
