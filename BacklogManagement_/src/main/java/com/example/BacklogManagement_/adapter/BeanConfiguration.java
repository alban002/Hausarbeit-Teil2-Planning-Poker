package com.example.BacklogManagement_.adapter;

// Import-Anweisungen, um auf benötigte Klassen und Interfaces zuzugreifen
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.BacklogManagement_.application.BacklogManagementApplicationService;
import com.example.BacklogManagement_.application.BacklogManagementService;
import com.example.BacklogManagement_.application.UserStoryRepository;

@Configuration // Markiert die Klasse als Quelle für Bean-Definitionen
public class BeanConfiguration {

	 // Definiert eine Bean für den BacklogManagementService
	 @Bean
	 BacklogManagementService lagerService(UserStoryRepository userStoryRepository) {
	        // Erstellt und gibt eine neue Instanz des BacklogManagementApplicationService zurück
	        // und injiziert das UserStoryRepository in diesen Service
	        return new BacklogManagementApplicationService(userStoryRepository);
	 } 
	 
	 // Definiert eine Bean für den BMEventListener
	 @Bean
	 RabbitEventListener eventListener(BacklogManagementService backlogManagementService) {
	        // Erstellt und gibt eine neue Instanz des BMEventListener zurück
	        // und injiziert den BacklogManagementService in diesen Listener
			return new RabbitEventListener(backlogManagementService);
	 };
	 
	 // Definiert eine Bean für das UserStoryRepository
	 @Bean
	 UserStoryRepository userStoryRepository(JdbcUserStoryEntityRepository jdbcUserStoryEntityRepository) {
	        // Erstellt und gibt eine neue Instanz des DbUserStoryRepository zurück
	        // und injiziert das JdbcUserStoryEntityRepository in dieses Repository
	        return new DbUserStoryRepository(jdbcUserStoryEntityRepository);
	 }
}