package com.example.BacklogManagement_.adapter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.BacklogManagement_.application.BacklogManagementApplicationService;
import com.example.BacklogManagement_.application.BacklogManagementService;
import com.example.BacklogManagement_.application.UserStoryRepository;


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

