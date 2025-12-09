package com.games.tennis.service;

import com.games.tennis.data.PlayerEntity;
import com.games.tennis.web.Player;
import com.games.tennis.web.PlayerList;
import com.games.tennis.web.PlayerToSave;
import com.games.tennis.web.Rank;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RankingCalculator {

    private final List<PlayerEntity> currentPlayersRanking;

    public RankingCalculator(List<PlayerEntity> currentPlayersRanking) {
        this.currentPlayersRanking = currentPlayersRanking;
    }

    public List<PlayerEntity> getNewPlayersRanking() {
     currentPlayersRanking.sort(
             (player1, player2) -> Integer.compare(player1.getPoints(), player2.getPoints()));
          List<PlayerEntity> updatedPlayers = new ArrayList<>();
          for(int i = 0 ; i < currentPlayersRanking.size(); i++){
              PlayerEntity player = currentPlayersRanking.get(i);
              player.setRank(i+1);
              updatedPlayers.add(player);
        }
        return updatedPlayers;
    }
}