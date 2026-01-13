package com.games.tennis.web;

import com.games.tennis.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import static org.mockito.Mockito.when;

@WebMvcTest(controllers = PlayerController.class)
public class PlayerControllerTest {

    @Autowired
    private MockMvcTester mockMvc;

    @MockitoBean
    private PlayerService playerService;

 /*   @Test
    public void shouldListAllPlayers() {
        // Given
    //    when(playerService.displayPlayersList()).thenReturn(PlayerList.ALL);

        // When
        var response = mockMvc.get().uri("/players")
                .accept(MediaType.APPLICATION_JSON)
                .exchange();

        // Then
        var json = response.assertThat().hasStatus(HttpStatus.OK).bodyJson();
        json.extractingPath("$.length()").isEqualTo(4);
        json.extractingPath("$[0].lastName").isEqualTo("Nadal");
        json.extractingPath("$[1].lastName").isEqualTo("Djokovic");
        json.extractingPath("$[2].lastName").isEqualTo("Federer");
        json.extractingPath("$[3].lastName").isEqualTo("Murray");
    }


    @Test
    public void shouldRetrievePlayer() {
        // Given
        String playerToRetrieve = "nadal";
      //  when(playerService.displayPlayerByLastName(playerToRetrieve)).thenReturn(PlayerList.RAFAEL_NADAL);

        // When
        var response = mockMvc.get().uri("/players/nadal")
                .accept(MediaType.APPLICATION_JSON)
                .exchange();

        // Then
        var json = response.assertThat().hasStatus(HttpStatus.OK).bodyJson();
        json.extractingPath("$.lastName").isEqualTo("Nadal");
        json.extractingPath("$.rank.position").isEqualTo(1);
    }    */
}