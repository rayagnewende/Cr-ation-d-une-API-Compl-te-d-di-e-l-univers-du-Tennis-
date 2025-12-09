package com.games.tennis.service;


public class PlayerAlreadyExist extends  RuntimeException{
    public PlayerAlreadyExist( String lastname) {
        super("Le joueur du nom de " +  lastname + " existe déjà!");
    }
}
