package com.games.tennis.web;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record Player(
       @NotBlank(message = "Firstname is required") String firstName,
       @NotBlank(message = "Lastname is required") String lastName,
       @NotNull(message = "birthdate is required ") @PastOrPresent(message = "birthdate  must be past or present") LocalDate birthDate,
       @Valid Rank rank) {
}
