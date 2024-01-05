package com.example.PlanningPoker_;

public class PlanningPokerApplicationService implements PlanningPokerService{

	private UserStoryRepository userStoryRepository;
	private PlanningPokerDomainService planningPokerDomainService;
	MessageQueue messageQueue;
	
	public PlanningPokerApplicationService (UserStoryRepository userStoryRepository, PlanningPokerDomainService planningPokerDomainService, MessageQueue messageQueue) {
		this.userStoryRepository = userStoryRepository;
		this.planningPokerDomainService = planningPokerDomainService;
		this.messageQueue = messageQueue;
	}
	
	@Override
	public FestlegungsversuchResult endgueltigeEstimationFestlegen(int userStoryId, int finalEstimation) {
	    boolean istBerechtigt = planningPokerDomainService.berechtigungPruefen();

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

	    return FestlegungsversuchResult.SUCCESS;
	}

}
