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
	 ShopService shopService(ArtikelRepository artikelRepository, ShopDomainService shopDomainService) {
	        return new ShopApplicationService(artikelRepository, shopDomainService);
	 }
	 
	 @Bean
	 ShopDomainService bestandDomainService(ArtikelRepository artikelRepository, MessageQueue messageQueue) {
	        return new ShopDomainService(artikelRepository, messageQueue);
	 }
	 
	 @Bean
	 ArtikelRepository artikelRepository(JdbcArtikelEntityRepository jdbcArtikelEntityRepository) {
	        return new DbArtikelRepository(jdbcArtikelEntityRepository);
	 }
	 
	 @Bean
	 MessageQueue messageQueue(AmqpTemplate amqpTemplate) {
		 return new QueueAdapter(amqpTemplate);
	 }
}
