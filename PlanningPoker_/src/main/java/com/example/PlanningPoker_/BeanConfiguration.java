package com.example.PlanningPoker_;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
	
	 @Bean
	 PlanningPokerService planningPokerService(UserStoryRepository userStoryRepository, PlanningPokerDomainService planningPokerDomainService) {
	        return new PlanningPokerApplicationService(userStoryRepository, planningPokerDomainService);
	 }
	 
	 /*@Bean
	 PlanningPokerDomainService planningPokerDomainService(ArtikelRepository artikelRepository, MessageQueue messageQueue) {
	        return new ShopDomainService(artikelRepository, messageQueue);
	 }*/
	 
	 @Bean
	 PlanningPokerDomainService planningPokerDomainService() {
	        return new PlanningPokerDomainService();
	 }
	 
	 @Bean
	 UserStoryRepository userStoryRepository(JdbcUserStoryEntityRepository jdbcUserStoryEntityRepository) {
	        return new DbUserStoryRepository(jdbcUserStoryEntityRepository);
	 }
	 
	 /*@Bean
	 MessageQueue messageQueue(AmqpTemplate amqpTemplate) {
		 return new QueueAdapter(amqpTemplate);
	 }*/
}
