package com.example.BacklogManagement;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JdbcUserStoryEntityRepository 
	extends CrudRepository<UserStoryEntity, Integer> {
}