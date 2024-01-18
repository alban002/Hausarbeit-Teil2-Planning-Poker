package com.example.PlanningPoker_.adapter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.PlanningPoker_.application.UserStoryRepository;
import com.example.PlanningPoker_.domain.UserStory;
import com.example.PlanningPoker_.domain.UserStoryId;
import com.example.PlanningPoker_.adapter.UserStoryEntity;

@Component
public class DbUserStoryRepository implements UserStoryRepository {
    
    private final JdbcUserStoryEntityRepository jdbcUserStoryEntityRepository;
    
    @Autowired
    public DbUserStoryRepository(JdbcUserStoryEntityRepository jdbcUserStoryEntityRepository) {
        this.jdbcUserStoryEntityRepository = jdbcUserStoryEntityRepository;
    }

    // Diese Methode sucht nach einer UserStory in der Datenbank anhand ihrer ID.
    @Override
    public UserStory findById(UserStoryId userStoryId) {
        // Hier wird die UserStoryEntity in der Datenbank über die JdbcUserStoryEntityRepository gesucht.
        Optional<UserStoryEntity> userStoryEntity = jdbcUserStoryEntityRepository.findById(userStoryId.getUserStoryId());
        if (userStoryEntity.isPresent()) {
            // Wenn die UserStoryEntity gefunden wurde, wird sie in ein UserStory-Domain-Objekt umgewandelt und zurückgegeben.
            return userStoryEntity.get().toDomain();
        } else {
            // Wenn die UserStoryEntity nicht gefunden wurde, wird null zurückgegeben.
            return null;
        }
    }

    // Diese Methode speichert eine UserStory in der Datenbank.
    @Override
    public void save(UserStory userStory) {
        // Hier wird die UserStory als UserStoryEntity in der Datenbank gespeichert.
        jdbcUserStoryEntityRepository.save(new UserStoryEntity(userStory));
    }

    // Diese Methode erstellt eine User-Story-Tabelle in der Datenbank
    @Override
    public boolean createUserStoryTable() {
        // Hier wird die Methode zum Erstellen der Tabelle über das JdbcUserStoryEntityRepository aufgerufen.
        return jdbcUserStoryEntityRepository.createTable();
    }

    // Diese Methode löscht eine UserStory anhand ihrer ID aus der Datenbank.
    @Override
    public void deleteById(int userStoryDBid) {
        // Hier wird die UserStoryEntity mit der angegebenen ID aus der Datenbank gelöscht.
        jdbcUserStoryEntityRepository.deleteById(userStoryDBid);
    }
}