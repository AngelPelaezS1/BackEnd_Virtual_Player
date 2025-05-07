package cat.itacademy.s05.t02.n01.S05T02N01Mascota.controller;

import cat.itacademy.s05.t02.n01.S05T02N01Mascota.dto.*;
import cat.itacademy.s05.t02.n01.S05T02N01Mascota.service.PlayerService;
import java.util.List;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/player")
public class PlayerController {
    private final PlayerService playerService;
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

@Operation(summary = "Create new player.")
@ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Player successfully created."),
        @ApiResponse(responseCode = "403", description = "You don't have access.")
})
    @PostMapping("/create")
    public ResponseEntity<String> createPlayer(@RequestBody CreatePlayerDTO playerDTO, HttpServletRequest request) {
        playerService.createPlayer(playerDTO, request);
        return ResponseEntity.ok("Player successfully created.");
    }

@Operation(summary = "Get all players.")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Show all players."),
        @ApiResponse(responseCode = "403", description = "You don't have access")
})
    @GetMapping("/showAll")
    public ResponseEntity<List<ShowPlayersDTO>> showPlayers(HttpServletRequest request) {
        List<ShowPlayersDTO> players = playerService.showPlayers(request);
        return ResponseEntity.ok(players);
    }

@Operation(summary = "Get player by ID.")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Show player from ID."),
        @ApiResponse(responseCode = "404", description = "Player not found.")
})
    @GetMapping("/show/{id}")
    public ResponseEntity<ShowPlayerDTO> showPlayer(@PathVariable Long id, HttpServletRequest request){
        ShowPlayerDTO player = playerService.getPlayer(id, request);
        return ResponseEntity.ok(player);
    }

@Operation(summary = "Refresh player stats every minute while selected.")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Player's energy and happiness updated based on time spent selected."),
        @ApiResponse(responseCode = "404", description = "Player not found.")
})
    @GetMapping("/refresh/{id}")
    public ShowPlayerDTO refreshPlayerStats(@PathVariable Long id, HttpServletRequest request) {
        return playerService.refreshStats(id, request);
    }

@Operation(summary = "Delete player.")
@ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Player successfully deleted."),
        @ApiResponse(responseCode = "404", description = "Player not found.")
})
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable Long id, HttpServletRequest request){
        playerService.deletePlayer(id, request);
        return ResponseEntity.ok("Player successfully deleted.");
    }

@Operation(summary = "Train player to boost happiness and reduce energy.")
@ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Player training."),
        @ApiResponse(responseCode = "404", description = "Player not found.")
})
    @PutMapping("/training/{id}")
    public ResponseEntity<String> trainingPlayer(@PathVariable Long id, HttpServletRequest request){
        playerService.training(id, request);
        return ResponseEntity.ok("Player training.");
    }

@Operation(summary = "Sleep player to boost happiness and energy.")
@ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Player sleeping."),
        @ApiResponse(responseCode = "404", description = "Player not found.")
})
    @PutMapping("/sleeping/{id}")
    public ResponseEntity<String> sleepingPlayer(@PathVariable Long id, HttpServletRequest request){
        playerService.sleeping(id,request);
        return ResponseEntity.ok("Player sleeping.");
    }

@Operation(summary = "Update player team.")
@ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Player team updated successfully."),
        @ApiResponse(responseCode = "404", description = "Player not found.")
})
    @PutMapping("/team/{id}")
    public ResponseEntity<String> updateTeam(@PathVariable Long id, @RequestBody UpdateTeamDTO newTeam, HttpServletRequest request){
        playerService.updateTeam(id, newTeam.getNewTeam(), request);
        return ResponseEntity.ok("Player team updated successfully.");
    }
}
