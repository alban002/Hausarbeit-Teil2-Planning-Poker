package com.example.BacklogManagement_.adapter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.BacklogManagement_.application.UserStoryRepository;
import com.example.BacklogManagement_.domain.UserStory;
import com.example.BacklogManagement_.domain.UserStoryId;

@Component
public class DbUserStoryRepository implements UserStoryRepository {
    
    // Hier wird die JdbcUserStoryEntityRepository-Abhängigkeit mithilfe von Spring Dependency Injection injiziert.
    private final JdbcUserStoryEntityRepository jdbcUserStoryEntityRepository;

    @Autowired
    public DbUserStoryRepository(JdbcUserStoryEntityRepository jdbcUserStoryEntityRepository) {
        this.jdbcUserStoryEntityRepository = jdbcUserStoryEntityRepository;
    }

    // Die Methode findById sucht nach einer User Story in der Datenbank anhand ihrer ID.
    @Override
    public UserStory findById(UserStoryId userStoryId) {
        // Hier wird versucht, die UserStoryEntity aus der Datenbank anhand der ID zu finden.
        Optional<UserStoryEntity> userStoryEntity = jdbcUserStoryEntityRepository.findById(userStoryId.getId());
        if (userStoryEntity.isPresent()) {
            // Wenn die Entity gefunden wurde, wird sie in ein UserStory-Domain-Objekt umgewandelt und zurückgegeben.
            return userStoryEntity.get().toDomain();
        } else {
            // Wenn die Entity nicht gefunden wurde, wird null zurückgegeben.
            return null;
        }
    }

    // Die Methode save speichert eine User Story in der Datenbank.
    @Override
    public void save(UserStory userStory) {
        // Hier wird die UserStory als UserStoryEntity in die Datenbank gespeichert.
        jdbcUserStoryEntityRepository.save(new UserStoryEntity(userStory));
    }

    // Die Methode deleteById löscht eine User Story aus der Datenbank anhand ihrer ID.
    @Override
    public void deleteById(int userStoryDBid) {
        // Hier wird die UserStoryEntity aus der Datenbank gelöscht.
        jdbcUserStoryEntityRepository.deleteById(userStoryDBid);
    }

    // Die Methode createUserStoryTable erstellt eine Tabelle in der Datenbank für User Stories.
    @Override
    public boolean createUserStoryTable() {
        // Hier wird versucht, die Tabelle in der Datenbank zu erstellen.
        return jdbcUserStoryEntityRepository.createTable();
    }
}