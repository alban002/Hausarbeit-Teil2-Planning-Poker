package com.example.PlanningPoker_.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.PlanningPoker_.domain.UserStory;

@Service
public class KafkaProducerService {

    private static final String TOPIC = "userstories";
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendUserStory(UserStory userStory) {
        try {
            String message = objectMapper.writeValueAsString(userStory);
            kafkaTemplate.send(TOPIC, message);
            System.out.println("KAFKA_PRODUCER_INFO: UserStory mit ID "+ userStory.getUserStoryId().getUserStoryId() + " gesendet an TOPIC " + TOPIC);
        } catch (Exception e) {
            System.out.println("Could not send UserStory to Kafka");
            e.printStackTrace();
        }
    }
}
