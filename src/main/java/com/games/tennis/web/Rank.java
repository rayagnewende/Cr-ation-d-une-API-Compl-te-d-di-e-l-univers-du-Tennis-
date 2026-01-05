package com.games.tennis.web;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record Rank(
        @Positive  Integer position,
        @PositiveOrZero Integer points) {
}
