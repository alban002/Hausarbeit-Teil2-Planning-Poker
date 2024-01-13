package com.example.PlanningPoker_.application;

import com.example.PlanningPoker_.adapter.FestlegungsversuchResult;
import com.example.PlanningPoker_.adapter.KafkaProducerService;
import com.example.PlanningPoker_.domain.PlanningPokerDomainService;
import com.example.PlanningPoker_.domain.UserStory;
import com.example.PlanningPoker_.domain.UserStoryId;

public class PlanningPokerApplicationService implements PlanningPokerService{

	private UserStoryRepository userStoryRepository;
	private PlanningPokerDomainService planningPokerDomainService;
	private KafkaProducerService kafkaProducerService;
	
	MessageQueue messageQueue;
	
	public PlanningPokerApplicationService (UserStoryRepository userStoryRepository, PlanningPokerDomainService planningPokerDomainService, MessageQueue messageQueue, KafkaProducerService kafkaProducerService) {
		this.userStoryRepository = userStoryRepository;
		this.planningPokerDomainService = planningPokerDomainService;
		this.messageQueue = messageQueue;
		this.kafkaProducerService=kafkaProducerService;
	}
	
	//Natuerlich waere es auch moeglich einen weiteren Parameter aufzunehmen, jedoch wollten wir beide MessageBroker ueber zwei separate Controller ansprechen
	@Override
	public FestlegungsversuchResult endgueltigeEstimationFestlegenKafka(int userStoryId, int finalEstimation, String rolle) {
	    boolean istBerechtigt = planningPokerDomainService.berechtigungPruefen(rolle);

	    if (!istBerechtigt) {
	        return FestlegungsversuchResult.PERMISSION_DENIED;
	    }

	    UserStory userStory = userStoryRepository.findById(new UserStoryId(userStoryId));

	    if (userStory == null) {
	        return FestlegungsversuchResult.USER_STORY_NOT_FOUND;
	    }

	    userStory.setFinalEstimation(finalEstimation);
	    userStoryRepository.save(userStory);

	    // asynchrone Kommunikation zum anderen Service über Kafka
	    kafkaProducerService.sendUserStory(userStory);


	    return FestlegungsversuchResult.SUCCESS;
	}
	
	@Override
	public FestlegungsversuchResult endgueltigeEstimationFestlegenRabbitMQ(int userStoryId, int finalEstimation, String Rolle) {
		boolean istBerechtigt = planningPokerDomainService.berechtigungPruefen(Rolle);

	    if (!istBerechtigt) {
	        return FestlegungsversuchResult.PERMISSION_DENIED;
	    }

	    UserStory userStory = userStoryRepository.findById(new UserStoryId(userStoryId));

	    if (userStory == null) {
	        return FestlegungsversuchResult.USER_STORY_NOT_FOUND;
	    }

	    userStory.setFinalEstimation(finalEstimation);
	    userStoryRepository.save(userStory);

	    //asynchroneKommunikation zum anderen Service ueber Rabbit MQ
	    messageQueue.sendenRabbitMQ(userStory);
	    System.out.println("");

	    return FestlegungsversuchResult.SUCCESS;
	}

	@Override
	public boolean userStoryTabelleErstellen() {
		return userStoryRepository.createUserStoryTable();
	}
	
	public void populateUserStoryTable() {
		UserStory userStory1 = new UserStory("ERSTEBeschreibung", "ERSTEBeschreibung", 2);
		UserStory userStory2 = new UserStory("ZWEITEBeschreibung", "ZWEITEBeschreibung", 8);
		UserStory userStory3 = new UserStory("DRITTEBeschreibung", "DRITTEBeschreibung", 9);
		userStoryRepository.save(userStory1);
		userStoryRepository.save(userStory2);
		userStoryRepository.save(userStory3);
	}
	
	@Override
	public boolean deleteUserStoryById(int id) {
		UserStory userStoryDB = userStoryRepository.findById(new UserStoryId(id));
		
 		if (userStoryDB == null)
 		{
 			return false;
 			
 		}
 		else {
 			userStoryRepository.deleteById(id);
			return true;
		}
	}
}