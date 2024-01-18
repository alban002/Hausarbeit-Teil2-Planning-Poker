package com.example.PlanningPoker_.domain;

public class PlanningPokerDomainService {
	
	//UserStoryRepository userStoryRepository;
	//MessageQueue messageQueue;
	
	public PlanningPokerDomainService (/*UserStoryRepository userStoryRepository, MessageQueue messageQueue*/) {
		//this.userStoryRepository = userStoryRepository;
		//this.messageQueue = messageQueue;
	}
	
	//return true wenn die Rolle "ProductOwner" ist ansonten false
	public boolean berechtigungPruefen(String rolle) {
	   return rolle.equals("ProductOwner");
	}
}