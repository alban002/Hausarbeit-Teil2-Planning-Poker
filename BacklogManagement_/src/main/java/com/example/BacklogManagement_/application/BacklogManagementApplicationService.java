package com.example.BacklogManagement_.application;

import com.example.BacklogManagement_.domain.UserStory;
import com.example.BacklogManagement_.domain.UserStoryId;

public class BacklogManagementApplicationService implements BacklogManagementService {

    // Die genutzte Implementierung des UserStoryRepository-Interfaces wird hier verwendet.
    private UserStoryRepository userStoryRepository;

    public BacklogManagementApplicationService(UserStoryRepository userStoryRepository) {
        // Konstruktor, um die Abhängigkeiten zu injizieren.
        this.userStoryRepository = userStoryRepository;
    }

    public boolean userStoryTabelleErstellen() {
        // Ruft die Methode createUserStoryTable auf der UserStoryRepository-Implementierung auf.
        return userStoryRepository.createUserStoryTable();
    }

    @Override
    public boolean userStoryUpdaten(UserStory updatedUserStory) {
        // Sucht die bestehende User Story in der Datenbank anhand ihrer ID.
        UserStory userStoryDB = userStoryRepository.findById(updatedUserStory.getUserStoryId());

        if (userStoryDB == null) {
            // Wenn die User Story nicht gefunden wurde, wird false zurückgegeben.
            return false;
        } else {
            // Wenn die User Story gefunden wurde, wird die aktualisierte User Story in der Datenbank gespeichert.
            userStoryRepository.save(updatedUserStory);
            System.out.println("H2_INFO: UserStory mit ID " + updatedUserStory.getUserStoryId().getId() + " wurde veraendert in Datenbank");
            return true;
        }
    }

    @Override
    public boolean userStoryErstellen(UserStory newUserStory) {
        // Speichert eine neue User Story in der Datenbank.
        userStoryRepository.save(newUserStory);
        return true;
    }

    @Override
    public UserStory getUserStoryById(int id) {
        // Sucht eine User Story in der Datenbank anhand ihrer ID und gibt sie zurück.
        UserStory userStory = userStoryRepository.findById(new UserStoryId(id));
        return userStory;
    }

    @Override
    public boolean deleteUserStoryById(int id) {
        // Sucht die User Story in der Datenbank anhand ihrer ID.
        UserStory userStoryDB = userStoryRepository.findById(new UserStoryId(id));

        if (userStoryDB == null) {
            // Wenn die User Story nicht gefunden wurde, wird false zurückgegeben.
            return false;
        } else {
            // Wenn die User Story gefunden wurde, wird sie aus der Datenbank gelöscht.
            userStoryRepository.deleteById(id);
            return true;
        }
    }

    @Override
    public void populateUserStoryTable() {
        // Erstellt und speichert einige Beispiel-User Stories in der Datenbank.
        UserStory userStory1 = new UserStory("ERSTEBeschreibung", "ERSTERTitel", 2);
        UserStory userStory2 = new UserStory("ZWEITEBeschreibung", "ZWEITERTitel", 8);
        UserStory userStory3 = new UserStory("DRITTEBeschreibung", "DRITTERTitel", 9);
        userStoryRepository.save(userStory1);
        userStoryRepository.save(userStory2);
        userStoryRepository.save(userStory3);
    }
}