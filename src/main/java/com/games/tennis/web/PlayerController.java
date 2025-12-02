package com.games.tennis.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag( name = "API complète pour la gestion des joueurs de tennis")
@RestController
@RequestMapping("players")
public class PlayerController {

   @Operation(summary = "Affiche la listye des joueurs", description = "Affiche la listye des joueurs")
   @ApiResponses(value = {
           @ApiResponse(responseCode = "200", description = "Affiche la listye des joueurs",content = {
                   @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Player.class)))
           } )
   })
    @GetMapping("/")
    public List<Player> list(){
        return PlayerList.ALL ;
    }


    @Operation(summary = "recupérer les données d'un joueeur en fonction de son nom", description = "recupérer les données d'un joueeur en fonction de son nom")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "recupérer les données d'un joueeur en fonction de son nom",content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Player.class))
            } )
    })

    @GetMapping("/{lastname}")
    public Player getPlayerByLastName(@PathVariable("lastname") String lastname){
       return PlayerList.ALL.stream().filter( player -> player.lastName().equals(lastname))
               .findFirst()
               .orElseThrow();

    }

    @Operation(summary = "Créer un joueur de Teniis", description = "Créer un joueur de Teniis")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "recupérer les données d'un joueeur en fonction de son nom",content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Player.class))
            } )
    })

    @PostMapping("/")
    public Player createPlayer(@Valid @RequestBody Player player ){
       return player;
    }


    @Operation(summary = "Modifiez les infos d'un joueur de Teniis", description = "Modifiez les infos d'un joueur de Teniis")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Modifiez les infos d'un joueur de Teniis",content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Player.class))
            } )
    })

    @PutMapping("/")
    public Player updatePlayer(@Valid @RequestBody Player player ){
        return player;
    }


    @Operation(summary = "Supprimer un joueur de Teniis", description = "Supprimer un joueur de Teniis")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Supprimer un joueur de Teniis",content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Player.class))
            } )
    })

    @DeleteMapping("/")
    public Player deletePlayer(@RequestBody Player player ){
        return player;
    }


}
