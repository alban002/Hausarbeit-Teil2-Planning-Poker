package com.example.BacklogManagement_;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeanConfiguration {

	
	 @Bean
	 BacklogManagementService lagerService(UserStoryRepository userStoryRepository) {
	        return new BacklogManagementApplicationService(userStoryRepository);
	 } 
	 
	 @Bean
	 BMEventListener eventListener(BacklogManagementService backlogManagementService) {
			return new BMEventListener(backlogManagementService);
	 };
	 
	 
	 @Bean
	 UserStoryRepository userStoryRepository(JdbcUserStoryEntityRepository jdbcUserStoryEntityRepository) {
	        return new DbUserStoryRepository(jdbcUserStoryEntityRepository);
	 }
	
	 	
}

