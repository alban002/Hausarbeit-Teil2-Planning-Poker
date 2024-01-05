package com.example.PlanningPoker_;

import java.util.Random;

public class PlanningPokerDomainService {
	
	//UserStoryRepository userStoryRepository;
	//MessageQueue messageQueue;
	
	public PlanningPokerDomainService (/*UserStoryRepository userStoryRepository, MessageQueue messageQueue*/) {
		//this.userStoryRepository = userStoryRepository;
		//this.messageQueue = messageQueue;
	}
	//HIER ZUFÄLLIG ENTSCHEIDEN, OB DIE FINALESTIMATION COTROOLERAUFRUF BERECHTIGT WAR PO ODER NORMAL
	//Soll eine Simulation sein, um zu prüfen, ob der Anfragende ein ProductOwner ist oder nicht

	public boolean berechtigungPruefen() {
	    Random random = new Random();
	    //Gibt zu 50% true und zu 50% false zurueck
	    return random.nextBoolean();
	}
}

