package cat.itacademy.s05.t02.n01.S05T02N01Mascota.Service;

import cat.itacademy.s05.t02.n01.S05T02N01Mascota.dto.CreatePlayerDTO;
import cat.itacademy.s05.t02.n01.S05T02N01Mascota.dto.ShowPlayerDTO;
import cat.itacademy.s05.t02.n01.S05T02N01Mascota.enums.*;
import cat.itacademy.s05.t02.n01.S05T02N01Mascota.exception.UserNotFoundException;
import cat.itacademy.s05.t02.n01.S05T02N01Mascota.model.Player;
import cat.itacademy.s05.t02.n01.S05T02N01Mascota.model.User;
import cat.itacademy.s05.t02.n01.S05T02N01Mascota.repository.PlayerRepository;
import cat.itacademy.s05.t02.n01.S05T02N01Mascota.repository.UserRepository;
import cat.itacademy.s05.t02.n01.S05T02N01Mascota.security.TokenJwt;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {
    private final UserRepository userRepository;
    private final PlayerRepository playerRepository;
    private final TokenJwt tokenJwt;

    public PlayerService(UserRepository userRepository, PlayerRepository playerRepository, TokenJwt tokenJwt) {
        this.userRepository = userRepository;
        this.playerRepository = playerRepository;
        this.tokenJwt = tokenJwt;
    }

    public String createPlayer(CreatePlayerDTO playerDTO, HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        String username = tokenJwt.getUsernameFromToken(token);

        User user = userRepository.findByName(username)
                .orElseThrow(() -> new UserNotFoundException("player not found."));

        Player player = new Player();
        player.setName(playerDTO.getName());
        player.setNationality(playerDTO.getNationality());
        player.setTeam(PlayerTeam.getRandom());
        player.setEnergy(50);
        player.setHappiness(50);
        player.setState(PlayerState.NEUTRAL);
        player.setHairStyle(HairStyle.DEFAULT);
        player.setUser(user);
        playerRepository.save(player);

        return "Player created successfully";
    }

    public List<ShowPlayerDTO> showPlayers(HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        String username = tokenJwt.getUsernameFromToken(token);
        String role = tokenJwt.getRoleFromToken(token);

        User user = userRepository.findByName(username)
                .orElseThrow(() -> new UserNotFoundException("player not found"));
        List<Player> players;
        if (role.equals(RoleType.USER.name())) {
            players = playerRepository.findByUser(user);
        } else {
            players = playerRepository.findAll();
        }
        return players.stream()
                .map(player -> new ShowPlayerDTO(
                        player.getName(),
                        player.getNationality(),
                        player.getTeam(),
                        player.getEnergy(),
                        player.getHappiness(),
                        player.getHairStyle(),
                        player.getState(),
                        player.getUser().getName()
                ))
                .collect(Collectors.toList());
    }

    public ShowPlayerDTO getPlayer(Long id, HttpServletRequest request){
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        String username = tokenJwt.getUsernameFromToken(token);
        String role = tokenJwt.getRoleFromToken(token);

        User user = userRepository.findByName(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player not found"));
        if (role.equals("ROLE_USER") && !player.getUser().equals(user)) {
            throw new RuntimeException("This player doesn't belong to you");
        }
        return new ShowPlayerDTO(
                player.getName(),
                player.getNationality(),
                player.getTeam(),
                player.getEnergy(),
                player.getHappiness(),
                player.getHairStyle(),
                player.getState(),
                player.getUser().getName()
        );
    }
}