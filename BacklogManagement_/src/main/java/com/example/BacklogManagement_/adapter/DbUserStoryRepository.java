package com.example.BacklogManagement_.adapter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.BacklogManagement_.application.UserStoryRepository;
import com.example.BacklogManagement_.domain.UserStory;
import com.example.BacklogManagement_.domain.UserStoryId;


@Component
public class DbUserStoryRepository implements UserStoryRepository {
	
	private final JdbcUserStoryEntityRepository jdbcUserStoryEntityRepository;
	
	@Autowired
	public DbUserStoryRepository(JdbcUserStoryEntityRepository jdbcUserStoryEntityRepository) {
		this.jdbcUserStoryEntityRepository = jdbcUserStoryEntityRepository;
}

	@Override
	public UserStory findById(UserStoryId userStoryId) {
		  Optional<UserStoryEntity> userStoryEntity = jdbcUserStoryEntityRepository.findById(userStoryId.getId());
	        if (userStoryEntity.isPresent()) {
	            return userStoryEntity.get().toDomain();
	        } else {
	            return null;
	        }
	}

	@Override
	public void save(UserStory userStory) {
		jdbcUserStoryEntityRepository.save(new UserStoryEntity(userStory));

	}


	@Override
	public void deleteById(int userStoryDBid) {
		jdbcUserStoryEntityRepository.deleteById(userStoryDBid);
	}


	@Override
	public boolean createUserStoryTable() {
		return jdbcUserStoryEntityRepository.createTable();
	}
}