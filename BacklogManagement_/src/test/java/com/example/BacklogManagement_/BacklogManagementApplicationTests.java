package com.example.BacklogManagement_;

// Importiert notwendige Klassen für das Testen
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.BacklogManagement_.domain.UserStory;
import com.example.BacklogManagement_.domain.UserStoryId;

// Testklasse für Spring Boot-Anwendungen
@SpringBootTest
class BacklogManagementApplicationTests {
	
	@Test
	void contextLoads() {
	}
	
    // Prüfen, ob die gesamte Schätzung von UserStories berechnet wird
	@Test
    void berechneGesamteEstimation_CalculatesCorrectTotalWithRandomValues() {
        // Initialisieren eines Zufallsgenerators mit einem festen Seed für reproduzierbare Tests
        Random random = new Random(42); 

        // Erstellen einer Liste, um UserStory-Objekte zu speichern
        List<UserStory> userStories = new ArrayList<>();
        int gesamteEstimation = 0; // Variable für die Summe der Schätzungen

        // Hinzufügen von fünf UserStories mit zufälligen Schätzungen
        for (int i = 0; i < 5; i++) {
            int estimation = 1 + random.nextInt(30); // Generiert eine Zufallszahl zwischen 1 und 30
            userStories.add(new UserStory("Beschreibung " + (i+1), "Titel " + (i+1), estimation));
            gesamteEstimation += estimation;
        }

        // Berechnet die Gesamtschätzung mit der Methode aus der UserStory-Klasse
        int berechneteGesamteEstimation = UserStory.berechneGesamteEstimation(userStories);

        // Vergleicht die berechnete Gesamtschätzung mit der aufsummierten Schätzung
        Assertions.assertEquals(gesamteEstimation, berechneteGesamteEstimation);
    }
	
    // Prüfung des Erstellens und Abrufen von Werten eines UserStory-Objekts
	@Test
    void createAndGetUserStory() {
        UserStoryId id = new UserStoryId(1);
        UserStory story = new UserStory(id, "Beschreibung", "Titel", 5);
        story.beschreibungAnpassen("Neue Beschreibung");
        story.titelAnpassen("Neuer Titel");
        story.estimate(6);
        
        // Prüfen, ob die Änderungen korrekt durchgeführt wurden
        Assertions.assertEquals(id, story.getUserStoryId());
        Assertions.assertEquals("Neue Beschreibung", story.getDescription());
        Assertions.assertEquals("Neuer Titel", story.getTitle());
        Assertions.assertEquals(6, story.getEstimation());
    }

}