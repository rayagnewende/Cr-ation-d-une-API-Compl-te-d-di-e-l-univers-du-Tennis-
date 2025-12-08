package com.games.tennis.service;

import com.games.tennis.data.PlayerRepository;
import com.games.tennis.web.Player;
import com.games.tennis.web.PlayerList;
import com.games.tennis.web.PlayerToSave;
import com.games.tennis.web.Rank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    public List<Player> displayPlayersList(){
        return playerRepository.findAll().stream()
                .map(playerEntity -> new Player(
                        playerEntity.getFirstName(),
                        playerEntity.getLastName(),
                        playerEntity.getBirthDate(),
                        new Rank(playerEntity.getRank(), playerEntity.getPoints())))
                .sorted(Comparator.comparing(player -> player.rank().position()))
                .collect(Collectors.toList());
    }

    public Player displayPlayerByLastName(String lastname){
        return PlayerList.ALL.stream().filter( player -> player.lastName().equals(lastname))
                .findFirst()
                .orElseThrow( () -> new PlayerNotFoundException(lastname));
    }

    public Player create(PlayerToSave playerToSave){
       return  getPlayerNewRanking(PlayerList.ALL, playerToSave);
    }

    public Player update(PlayerToSave playerToSave){
        displayPlayerByLastName(playerToSave.lastName());
      List<Player> playersWithoutPlayerToSave  =   PlayerList.ALL.stream()
                               .filter( player -> !player.lastName().equals(playerToSave.lastName()))
                                .toList();
      RankingCalculator rankingCalculator = new RankingCalculator(playersWithoutPlayerToSave, playerToSave);
      List<Player> newPlayersList = rankingCalculator.getNewPlayersRanking();

      return newPlayersList.stream().filter(player -> player.lastName().equals(playerToSave.lastName()))
              .findFirst().get();
    }
    private Player getPlayerNewRanking(List<Player> existingPlayers, PlayerToSave playerToSave){
        RankingCalculator rankingCalculator = new RankingCalculator(PlayerList.ALL, playerToSave);

        List<Player> players = rankingCalculator.getNewPlayersRanking();
        return players.stream()
                .filter(player -> player.lastName().equals(playerToSave.lastName()))
                .findFirst().get();
    }

    public void delete(String lastName) {
        Player playerToDelete = displayPlayerByLastName(lastName);

        PlayerList.ALL = PlayerList.ALL.stream()
                .filter(player -> !player.lastName().equals(lastName))
                .toList();

        RankingCalculator rankingCalculator = new RankingCalculator(PlayerList.ALL);
        rankingCalculator.getNewPlayersRanking();
    }

}
