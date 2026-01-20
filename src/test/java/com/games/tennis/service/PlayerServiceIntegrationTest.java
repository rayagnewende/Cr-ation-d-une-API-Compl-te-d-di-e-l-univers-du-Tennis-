package com.games.tennis.service;

import com.games.tennis.web.Player;
import com.games.tennis.web.PlayerToSave;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootTest
@Transactional // Très important : annule les modifs en base après chaque test
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PlayerServiceIntegrationTest {
/*
    @Autowired
    private PlayerService playerService;

    @Test
    public void shouldCreatePlayer(){

        PlayerToSave specialPlayer = new PlayerToSave(
                "kabore",
                "eva",
                LocalDate.of(2000, Month.JANUARY, 1),
                10000);

       playerService.create(specialPlayer);
        Player player =  playerService.displayPlayerByLastName("eva");

        Assertions.assertThat(player.firstName()).isEqualTo("kabore");
        Assertions.assertThat(player.lastName()).isEqualTo("eva");
        Assertions.assertThat(player.birthDate()).isEqualTo( LocalDate.of(2000, Month.JANUARY, 1));
        Assertions.assertThat(player.rank().points()).isEqualTo(10000);
      //  Assertions.assertThat(player.rank().position()).isEqualTo(1);

    }

    @Test
    public void shouldUpdatePlayer() {
            Given
     PlayerToSave playerToSave = new PlayerToSave(
                "Rafael",
                "NadalTest",
                LocalDate.of(1986, Month.JUNE, 3),
                1000
        );

        // When
      //  playerService.update(playerToSave);
      //  Player updatedPlayer = playerService.displayPlayerByLastName("NadalTest");

        // Then
     //   Assertions.assertThat(updatedPlayer.rank().position()).isEqualTo(3);
    }


    @Test
    public void shouldDeletePlayer() {
         // Given
     //   String playerToDelete = "DjokovicTest";

        // When
   //     playerService.delete(playerToDelete);
     //   List<Player> allPlayers = playerService.displayPlayersList();

        // Then
      Assertions.assertThat(allPlayers)
                .extracting("lastName", "rank.position")
                .containsExactly(Tuple.tuple("NadalTest", 1), Tuple.tuple("FedererTest", 2));
    }

    @Test
    public void shouldFailToDeletePlayer_WhenPlayerDoesNotExist() {
       // Given
       // String playerToDelete = "DoeTest";

        // When / Then
       Assertions.assertThatThrownBy(() -> playerService.delete(playerToDelete))
                .isInstanceOf(PlayerNotFoundException.class)
                .hasMessage("Player with last name DoeTest could not be found.");
    }   */
}
