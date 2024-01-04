package com.example.BacklogManagement_;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DbUserStoryRepository implements UserStoryRepository {
	
	private final JdbcUserStoryEntityRepository jdbcUserStoryEntityRepository;
	
	@Autowired
	public DbUserStoryRepository(JdbcUserStoryEntityRepository jdbcUserStoryEntityRepository) {
		this.jdbcUserStoryEntityRepository = jdbcUserStoryEntityRepository;
}
//	@Autowired
//	public DbArtikelRepository() {
//	}
	
	
//	public Artikel findById(ArtikelId artikelId) {
//		if (artikelId.getId() == 1) {
//			ArtikelEntity artikelEntity = new ArtikelEntity();
//			artikelEntity.setId(1);
//			artikelEntity.setBestand(4713);
//			return artikelEntity.toDomain();
//		}
//		else return null;
//		
//	}

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

	

}