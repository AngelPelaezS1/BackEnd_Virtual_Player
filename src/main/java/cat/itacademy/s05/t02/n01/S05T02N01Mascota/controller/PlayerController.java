package cat.itacademy.s05.t02.n01.S05T02N01Mascota.controller;

import cat.itacademy.s05.t02.n01.S05T02N01Mascota.Service.PlayerService;
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
        return ResponseEntity.ok("User created successfully.");
    }

    @GetMapping("/showAll")
    public ResponseEntity<String> showPlayers(HttpServletRequest request) {
        List<ShowPlayerDTO> players = playerService.showPlayers(request);
        return ResponseEntity.ok("Show all players.");
    }

    @GetMapping("/player/{id}")
    public ResponseEntity<ShowPlayerDTO> showPlayer(@PathVariable Long id, HttpServletRequest request){
        ShowPlayerDTO player = playerService.getPlayer(id, request);
        return ResponseEntity.ok(player);
    }
}

