package com.example.PlanningPoker_.domain;

public class PlanningPokerDomainService {
	
	//UserStoryRepository userStoryRepository;
	//MessageQueue messageQueue;
	
	public PlanningPokerDomainService (/*UserStoryRepository userStoryRepository, MessageQueue messageQueue*/) {
		//this.userStoryRepository = userStoryRepository;
		//this.messageQueue = messageQueue;
	}
	//HIER ZUFÄLLIG ENTSCHEIDEN, OB DIE FINALESTIMATION COTROOLERAUFRUF BERECHTIGT WAR PO ODER NORMAL
	//Soll eine Simulation sein, um zu prüfen, ob der Anfragende ein ProductOwner ist oder nicht

	public boolean berechtigungPruefen(String rolle) {
	   return rolle.equals("ProductOwner");
	}
}