package com.games.tennis.service;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(String lastname) {
        super("Le joueur du nom de " +  lastname + " n'existe pas!");
    }
}
