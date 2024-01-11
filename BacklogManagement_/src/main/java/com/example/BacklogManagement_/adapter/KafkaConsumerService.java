package com.example.BacklogManagement_.adapter;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.example.BacklogManagement_.application.BacklogManagementService;
import com.example.BacklogManagement_.domain.UserStory;
import com.example.BacklogManagement_.domain.UserStoryId;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class KafkaConsumerService {

    private final BacklogManagementService backlogManagementService;
    private final ObjectMapper objectMapper;

    public KafkaConsumerService(BacklogManagementService backlogManagementService, ObjectMapper objectMapper) {
        this.backlogManagementService = backlogManagementService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "userstories", groupId = "backlog-management-group")
    public void listen(String message) {
        try {
            JsonNode jsonNode = objectMapper.readTree(message);
            UserStoryId userStoryId = new UserStoryId(jsonNode.get("userStoryId").get("userStoryId").asInt());
            String title = jsonNode.get("title").asText();
            String description = jsonNode.get("description").asText();
            int finalEstimation = jsonNode.get("estimation").asInt();
            UserStory userStory = new UserStory(userStoryId, title, description, finalEstimation);
            System.out.println("KAFKA_CONSUMER_INFO: UserStory mit ID " + userStoryId.getId() + " angekommen");
            backlogManagementService.userStoryUpdaten(userStory);
        } catch (Exception e) {
        	System.out.println("KAFKA_CONSUMER_INFO: UserStory konnte nicht deserialisiert werden");
        }
    }
}
