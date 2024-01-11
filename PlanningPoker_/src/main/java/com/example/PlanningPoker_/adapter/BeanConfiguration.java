package com.example.PlanningPoker_.adapter;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.PlanningPoker_.application.MessageQueue;
import com.example.PlanningPoker_.application.PlanningPokerApplicationService;
import com.example.PlanningPoker_.application.PlanningPokerService;
import com.example.PlanningPoker_.application.UserStoryRepository;
import com.example.PlanningPoker_.domain.PlanningPokerDomainService;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

@Configuration
public class BeanConfiguration {
	
	 @Bean
	 PlanningPokerService planningPokerService(UserStoryRepository userStoryRepository, PlanningPokerDomainService planningPokerDomainService, MessageQueue messageQueue, KafkaProducerService kafkaProducerService) {
	        return new PlanningPokerApplicationService(userStoryRepository, planningPokerDomainService, messageQueue, kafkaProducerService);
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
	 
	 @Bean
	 public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9093");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return new DefaultKafkaProducerFactory<>(configProps);
	 }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}