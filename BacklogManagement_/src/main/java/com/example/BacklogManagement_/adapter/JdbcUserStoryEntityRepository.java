package com.example.BacklogManagement_.adapter;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JdbcUserStoryEntityRepository extends CrudRepository<UserStoryEntity, Integer> {
	
	@Modifying
    @Query("CREATE TABLE IF NOT EXISTS USERSTORY (USER_STORY_ID INT AUTO_INCREMENT PRIMARY KEY,DESCRIPTION VARCHAR(255),TITLE VARCHAR(255),ESTIMATION INT);")
    boolean createTable();
}