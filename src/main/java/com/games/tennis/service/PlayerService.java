package com.games.tennis.service;

import com.games.tennis.data.PlayerEntity;
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

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

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
        Optional<PlayerEntity> player = playerRepository.findByLastNameIgnoreCase(lastname);
        if(player.isEmpty())
        {
            throw  new PlayerNotFoundException(lastname);
        }

        PlayerEntity playerEntity = player.get();
        return new Player(
                playerEntity.getFirstName(),
                playerEntity.getLastName(),
                playerEntity.getBirthDate(),
                new Rank(playerEntity.getRank(), playerEntity.getPoints()));
    }

    public Player create(PlayerToSave playerToSave){
        Optional<PlayerEntity> playerEntity = playerRepository.findByLastNameIgnoreCase(playerToSave.lastName());
        if(playerEntity.isPresent()){
            throw new PlayerAlreadyExist(playerToSave.lastName());
        }
        playerRepository.save(new PlayerEntity(
                playerToSave.lastName(),
                playerToSave.firstName(),
                playerToSave.birthDate(),
                playerToSave.points(),9999999));

        RankingCalculator rankingCalculator = new RankingCalculator(playerRepository.findAll());
        List<PlayerEntity> players = rankingCalculator.getNewPlayersRanking();

        playerRepository.saveAll(players);

       return displayPlayerByLastName(playerToSave.lastName());
    }

    public Player update(PlayerToSave playerToSave){
        Optional<PlayerEntity> playerEntity = playerRepository.findByLastNameIgnoreCase(playerToSave.lastName());
        if(playerEntity.isEmpty()){
            throw new PlayerNotFoundException(playerToSave.lastName());
        }

        playerEntity.get().setLastName(playerToSave.lastName());
        playerEntity.get().setFirstName(playerToSave.firstName());
        playerEntity.get().setBirthDate(playerToSave.birthDate());
        playerEntity.get().setPoints(playerToSave.points());
        playerRepository.save(playerEntity.get());

        RankingCalculator rankingCalculator = new RankingCalculator(playerRepository.findAll());
        List<PlayerEntity> players = rankingCalculator.getNewPlayersRanking();

        playerRepository.saveAll(players);

        return displayPlayerByLastName(playerToSave.lastName());
    }

    private List<PlayerEntity> getPlayerNewRanking(List<Player> existingPlayers){
        RankingCalculator rankingCalculator = new RankingCalculator(playerRepository.findAll());

        return rankingCalculator.getNewPlayersRanking();
    }

    public void delete(String lastName) {
        Optional<PlayerEntity> playerEntity = playerRepository.findByLastNameIgnoreCase(lastName);
        if(playerEntity.isEmpty()){
            throw new PlayerNotFoundException(lastName);
        }

        playerRepository.delete(playerEntity.get());
    }

}
