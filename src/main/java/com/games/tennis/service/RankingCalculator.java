package com.games.tennis.service;

import com.games.tennis.web.Player;
import com.games.tennis.web.PlayerList;
import com.games.tennis.web.PlayerToSave;
import com.games.tennis.web.Rank;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RankingCalculator {

    private final List<Player> currentPlayersRanking;
    private final PlayerToSave playerToSave;

    public RankingCalculator(List<Player> currentPlayersRanking, PlayerToSave playerToSave) {
        this.currentPlayersRanking = currentPlayersRanking;
        this.playerToSave = playerToSave;
    }
    public RankingCalculator(List<Player> currentPlayersRanking) {
        this.currentPlayersRanking = currentPlayersRanking;
        this.playerToSave = null;
    }

    public List<Player> getNewPlayersRanking() {
        List<Player> newRankingList = new ArrayList<>(currentPlayersRanking);
        if(playerToSave != null){
            newRankingList.add(new Player(
                    playerToSave.firstName(),
                    playerToSave.lastName(),
                    playerToSave.birthDate(),
                    new Rank(999999999, playerToSave.points())
            ));
        }

        List<Player> sortedPlayers = newRankingList.stream()
                .sorted(Comparator.comparing(player -> player.rank().points()))
                .toList();

        List<Player> updatedPlayers = new ArrayList<>();

        for (int i = 0; i < sortedPlayers.size(); i++) {
            Player player = sortedPlayers.get(i);
            Player updatedPlayer = new Player(
                    player.firstName(),
                    player.lastName(),
                    player.birthDate(),
                    new Rank(i+1, player.rank().points())
            );
            updatedPlayers.add(updatedPlayer);
        }
        PlayerList.ALL = updatedPlayers;
        return updatedPlayers;
    }
}