package com.example.BacklogManagement_;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.BacklogManagement_.domain.UserStory;
import com.example.BacklogManagement_.domain.UserStoryId;

@SpringBootTest
class BacklogManagementApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
    void berechneGesamteEstimation_CalculatesCorrectTotalWithRandomValues() {
        // Erstelle ein Random-Objekt mit einem festen Seed, um reproduzierbare Ergebnisse zu erhalten
        Random random = new Random(42); // Der Seed kann eine beliebige Zahl sein

        // Erstelle eine leere Liste von UserStories
        List<UserStory> userStories = new ArrayList<UserStory>();
        int gesamteEstimation = 0;

        // Füge fünf UserStories mit zufälligen Schätzungen hinzu
        for (int i = 0; i < 5; i++) {
            int estimation = 1 + random.nextInt(30); // Zufällige Schätzung von 1 bis 30
            userStories.add(new UserStory("Beschreibung " + (i+1), "Titel " + (i+1), estimation));
            gesamteEstimation += estimation; // Addiere die Schätzung zur Gesamtestimation
        }

        // Berechne die gesamte Schätzung der UserStories
        int berechneteGesamteEstimation = UserStory.berechneGesamteEstimation(userStories);

        // Vergleiche die berechnete Gesamtestimation mit der aufsummierten Schätzung
        Assertions.assertEquals(gesamteEstimation, berechneteGesamteEstimation);
    }
	
	@Test
    void createAndGetUserStory() {
        UserStoryId id = new UserStoryId(1);
        UserStory story = new UserStory(id, "Beschreibung", "Titel", 5);
        story.beschreibungAnpassen("Neue Beschreibung");
        story.titelAnpassen("Neuer Titel");
        story.estimate(6);
        
        Assertions.assertEquals(id, story.getUserStoryId());
        Assertions.assertEquals("Neue Beschreibung", story.getDescription());
        Assertions.assertEquals("Neuer Titel", story.getTitle());
        Assertions.assertEquals(6, story.getEstimation());
    }

}
