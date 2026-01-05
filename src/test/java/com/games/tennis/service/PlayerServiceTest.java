package com.games.tennis.service;

import com.games.tennis.data.PlayerEntity;
import com.games.tennis.data.PlayerRepository;
import com.games.tennis.service.PlayerService;
import com.games.tennis.web.Player;
import com.games.tennis.web.PlayerList;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerService playerService;

    @Test
    public void shouldReturnPlayersRanking() {
        // Given
        Mockito.when(playerRepository.findAll()).thenReturn(PlayerList.ALL);

        // When
        List<Player> allPlayers = playerService.displayPlayersList();

        // Then
        Assertions.assertThat(allPlayers)
                .extracting("lastName")
                .containsExactly("Nadal", "Djokovic", "Federer", "Murray");
    }

    @Test
    public void shouldReturnPlayerByLastName(){
        String playerToRetrieve = "Nadal";

        Mockito.when(playerRepository.findByLastNameIgnoreCase(playerToRetrieve))
                 .thenReturn(Optional.ofNullable(PlayerList.RAFAEL_NADAL));

        Player playerRetrieve = playerService.displayPlayerByLastName(playerToRetrieve);

        Assertions.assertThat(playerRetrieve.lastName()).isEqualTo("Nadal");
    }



    @Test
    public void shouldFailToRetrievePlayer_WhenPlayerDoesNotExist() {
        // Given
        String unknownPlayer = "doe";
        Mockito.when(playerRepository.findByLastNameIgnoreCase(unknownPlayer)).thenReturn(Optional.empty());

        // When / Then
        Exception exception = assertThrows(PlayerNotFoundException.class, () -> {
            playerService.displayPlayerByLastName(unknownPlayer);
        });
        Assertions.assertThat(exception.getMessage()).isEqualTo("Player with last name doe could not be found.");
    }

}