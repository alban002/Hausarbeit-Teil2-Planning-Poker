package com.example.PlanningPoker_;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

import com.example.PlanningPoker_.domain.UserStoryId;
import com.example.PlanningPoker_.domain.PlanningPokerDomainService;
import com.example.PlanningPoker_.domain.UserStory;

@SpringBootTest
class PlanningPokerApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
    void berechtigungPruefen_ReturnsTrueForProductOwner() {
        PlanningPokerDomainService service = new PlanningPokerDomainService();
        boolean isProductOwner = service.berechtigungPruefen("ProductOwner");
        Assertions.assertTrue(isProductOwner);
    }

    @Test
    void berechtigungPruefen_ReturnsFalseForOtherRoles() {
        PlanningPokerDomainService service = new PlanningPokerDomainService();
        boolean isProductOwner = service.berechtigungPruefen("Developer");
        Assertions.assertFalse(isProductOwner);
    }
    @Test
    void createAndGetUserStory() {
        UserStoryId id = new UserStoryId(1);
        UserStory story = new UserStory(id, "Beschreibung", "Titel", 6);
        story.beschreibungAnpassen("Neue Beschreibung");
        story.titelAnpassen("Neuer Titel");
        story.setFinalEstimation(7);
        
        Assertions.assertEquals(id, story.getUserStoryId());
        Assertions.assertEquals("Neue Beschreibung", story.getDescription());
        Assertions.assertEquals("Neuer Titel", story.getTitle());
        Assertions.assertEquals(7, story.getFinalEstimation());
    }
    
}
