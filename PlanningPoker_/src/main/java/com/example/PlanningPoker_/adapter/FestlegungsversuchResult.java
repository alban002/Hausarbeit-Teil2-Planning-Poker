package com.example.PlanningPoker_.adapter;

public enum FestlegungsversuchResult {
    // Die verschiedenen Enum-Konstanten repräsentieren verschiedene Rückgabewerte für Festlegungsversuche.
    SUCCESS,               // Erfolgreich festgelegt
    PERMISSION_DENIED,     // Keine Berechtigung zum Festlegen
    USER_STORY_NOT_FOUND,  // UserStory nicht gefunden
    OTHER_ERROR            // Anderer Fehler
}