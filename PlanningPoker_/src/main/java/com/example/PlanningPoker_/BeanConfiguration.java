package com.example.PlanningPoker_;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
	
	 @Bean
	 PlanningPokerService planningPokerService(UserStoryRepository userStoryRepository, PlanningPokerDomainService planningPokerDomainService, MessageQueue messageQueue) {
	        return new PlanningPokerApplicationService(userStoryRepository, planningPokerDomainService, messageQueue);
	 }
	 
	 @Bean
	 PlanningPokerDomainService planningPokerDomainService() {
	        return new PlanningPokerDomainService();
	 }
	 
	 @Bean
	 UserStoryRepository userStoryRepository(JdbcUserStoryEntityRepository jdbcUserStoryEntityRepository) {
	        return new DbUserStoryRepository(jdbcUserStoryEntityRepository);
	 }
	 
	 @Bean
	 MessageQueue messageQueue(AmqpTemplate amqpTemplate) {
		 return new MessageQueueAdapter(amqpTemplate);
	 }
}