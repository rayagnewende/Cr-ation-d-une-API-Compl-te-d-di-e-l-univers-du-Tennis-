package com.games.tennis.web;

import com.games.tennis.Error;
import com.games.tennis.service.PlayerAlreadyExist;
import com.games.tennis.service.PlayerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class PlayerControllerErrorHandler {

    @ExceptionHandler(PlayerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error playerNameNotExisting(PlayerNotFoundException ex){
      return new Error(ex.getMessage());
    }

    @ExceptionHandler(PlayerAlreadyExist.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error playerNameNotExisting(PlayerAlreadyExist ex){
        return new Error(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> playerArgumentMissing(MethodArgumentNotValidException ex ){
        var errors = new HashMap<String, String>();
        ex.getBindingResult().getAllErrors().forEach( error -> {
            String fieldName = ((FieldError)error).getField();
            String message =  ((FieldError)error).getDefaultMessage();
            errors.put(fieldName, message);
        });
     return errors ;
    }
}
