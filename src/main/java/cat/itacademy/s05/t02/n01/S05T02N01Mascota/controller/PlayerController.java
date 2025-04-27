package cat.itacademy.s05.t02.n01.S05T02N01Mascota.controller;

import cat.itacademy.s05.t02.n01.S05T02N01Mascota.dto.UpdateHairDTO;
import cat.itacademy.s05.t02.n01.S05T02N01Mascota.dto.UpdateTeamDTO;
import cat.itacademy.s05.t02.n01.S05T02N01Mascota.enums.HairStyle;
import cat.itacademy.s05.t02.n01.S05T02N01Mascota.enums.PlayerTeam;
import cat.itacademy.s05.t02.n01.S05T02N01Mascota.service.PlayerService;
import cat.itacademy.s05.t02.n01.S05T02N01Mascota.dto.CreatePlayerDTO;
import cat.itacademy.s05.t02.n01.S05T02N01Mascota.dto.ShowPlayerDTO;
import java.util.List;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/player")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createPlayer(@RequestBody CreatePlayerDTO playerDTO, HttpServletRequest request) {
        playerService.createPlayer(playerDTO, request);
        return ResponseEntity.ok("Player created successfully.");
    }

    @GetMapping("/showAll")
    public ResponseEntity<List<ShowPlayerDTO>> showPlayers(HttpServletRequest request) {
        List<ShowPlayerDTO> players = playerService.showPlayers(request);
        return ResponseEntity.ok(players);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowPlayerDTO> showPlayer(@PathVariable Long id, HttpServletRequest request){
        ShowPlayerDTO player = playerService.getPlayer(id, request);
        return ResponseEntity.ok(player);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable Long id, HttpServletRequest request){
        playerService.deletePlayer(id, request);
        return ResponseEntity.ok("Player eliminated successfully.");
    }

    @PutMapping("/training/{id}")
    public ResponseEntity<String> trainingPlayer(@PathVariable Long id, HttpServletRequest request){
        playerService.training(id, request);
        return ResponseEntity.ok("Player training.");
    }

    @PutMapping("/sleeping/{id}")
    public ResponseEntity<String> sleepingPlayer(@PathVariable Long id, HttpServletRequest request){
        playerService.sleeping(id,request);
        return ResponseEntity.ok("Player sleeping");
    }

    @PutMapping("/team/{id}")
    public ResponseEntity<String> updateTeam(@PathVariable Long id, @RequestBody UpdateTeamDTO newTeam, HttpServletRequest request){
        playerService.updateTeam(id, newTeam.getNewTeam(), request);
        return ResponseEntity.ok("Player team updated successfully.");
    }

    @PutMapping("hair/{id}")
    public ResponseEntity<String> updateHair(@PathVariable Long id, @RequestBody UpdateHairDTO newHair, HttpServletRequest request){
        playerService.updateHair(id, newHair.getNewHair(), request);
        return ResponseEntity.ok("Player hair updated successfully.");
    }
}

