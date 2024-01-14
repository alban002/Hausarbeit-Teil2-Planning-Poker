package com.example.PlanningPoker_;

//Importiert notwendige Klassen für das Testen
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.PlanningPoker_.domain.UserStoryId;
import com.example.PlanningPoker_.domain.PlanningPokerDomainService;
import com.example.PlanningPoker_.domain.UserStory;

// Testklasse mit Spring Boot-Funktionen 
@SpringBootTest
class PlanningPokerApplicationTests {

    // Diese Testmethode überprüft, ob der Spring-Anwendungskontext ordnungsgemäß geladen wird
	@Test
	void contextLoads() {
	}
	
	// Prüfen, ob die Berechtigungsprüfung true zurückgibt, wenn die Rolle "ProductOwner" ist
    @Test
    void berechtigungPruefen_ReturnsTrueForProductOwner() {
        PlanningPokerDomainService service = new PlanningPokerDomainService();
        boolean isProductOwner = service.berechtigungPruefen("ProductOwner");
        Assertions.assertTrue(isProductOwner);
    }

    // Prüfen, ob die Berechtigungsprüfung false zurückgibt, wenn die Rolle nicht "ProductOwner" ist
    @Test
    void berechtigungPruefen_ReturnsFalseForOtherRoles() {
        PlanningPokerDomainService service = new PlanningPokerDomainService();
        boolean isProductOwner = service.berechtigungPruefen("RegularParticipant");
        Assertions.assertFalse(isProductOwner);
    }

    // Prüfen, ob das Erstellen einer UserStory und die Funktionalität der Methoden funktioniert
    @Test
    void createAndGetUserStory() {
        UserStoryId id = new UserStoryId(1);
        UserStory story = new UserStory(id, "Beschreibung", "Titel", 6);
        story.beschreibungAnpassen("Neue Beschreibung");
        story.titelAnpassen("Neuer Titel");
        story.setFinalEstimation(7);
        // Prüfen, ob die Änderungen korrekt durchgeführt wurden
        Assertions.assertEquals(id, story.getUserStoryId());
        Assertions.assertEquals("Neue Beschreibung", story.getDescription());
        Assertions.assertEquals("Neuer Titel", story.getTitle());
        Assertions.assertEquals(7, story.getFinalEstimation());
    }
}
