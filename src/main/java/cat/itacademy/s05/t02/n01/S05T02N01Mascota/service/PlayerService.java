package cat.itacademy.s05.t02.n01.S05T02N01Mascota.service;

import cat.itacademy.s05.t02.n01.S05T02N01Mascota.dto.CreatePlayerDTO;
import cat.itacademy.s05.t02.n01.S05T02N01Mascota.dto.ShowPlayerDTO;
import cat.itacademy.s05.t02.n01.S05T02N01Mascota.dto.ShowPlayersDTO;
import cat.itacademy.s05.t02.n01.S05T02N01Mascota.enums.*;
import cat.itacademy.s05.t02.n01.S05T02N01Mascota.exception.UserNotFoundException;
import cat.itacademy.s05.t02.n01.S05T02N01Mascota.model.Player;
import cat.itacademy.s05.t02.n01.S05T02N01Mascota.model.User;
import cat.itacademy.s05.t02.n01.S05T02N01Mascota.repository.PlayerRepository;
import cat.itacademy.s05.t02.n01.S05T02N01Mascota.repository.UserRepository;
import cat.itacademy.s05.t02.n01.S05T02N01Mascota.security.TokenJwt;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalDateTime;
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

    private User findUserByName(String username) {
        return userRepository.findByName(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    private Player findPlayerById(Long id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player not found"));
    }

    private void filterRole(Player player, User user, RoleType role){
        if (role.equals("ROLE_USER") && !player.getUser().equals(user)) {
            throw new RuntimeException("This player doesn't belong to you");
        }
    }

    private Player getAuthorizedPlayer(Long id, HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        String username = tokenJwt.getUsernameFromToken(token);
        RoleType role = RoleType.valueOf(tokenJwt.getRoleFromToken(token));

        User user = findUserByName(username);
        Player player = findPlayerById(id);
        filterRole(player, user, role);

        return player;
    }

    private void updateEnergyAndHappiness(Player player) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime lastUpdated = player.getLastUpdated();

        if (lastUpdated == null) {
            player.setLastUpdated(now);
            return;
        }

        long minutes = Duration.between(lastUpdated, now).toMinutes();

        int energyLoss = (int) (minutes * 20);
        int happinessLoss = (int) (minutes * 20);

        int newEnergy = Math.max(0, player.getEnergy() - energyLoss);
        int newHappiness = Math.max(0, player.getHappiness() - happinessLoss);

        player.setEnergy(newEnergy);
        player.setHappiness(newHappiness);
        player.setLastUpdated(now);

        player.updateMood();
        playerRepository.save(player);
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
        player.setMood(PlayerMood.NEUTRAL);
        player.setUser(user);
        playerRepository.save(player);

        return "Player created successfully";
    }

    public List<ShowPlayersDTO> showPlayers(HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        String username = tokenJwt.getUsernameFromToken(token);
        String role = tokenJwt.getRoleFromToken(token);

        User user = findUserByName(username);
        List<Player> players;
        if (role.equals(RoleType.USER.name())) {
            players = playerRepository.findByUser(user);
        } else {
            players = playerRepository.findAll();
        }
        return players.stream()
                .map(player -> new ShowPlayersDTO(
                        player.getName(),
                        player.getNationality(),
                        player.getTeam(),
                        player.getUser().getName()
                ))
                .collect(Collectors.toList());
    }

    public ShowPlayerDTO getPlayer(Long id, HttpServletRequest request) {
        Player player = getAuthorizedPlayer(id, request);

        updateEnergyAndHappiness(player);
        return new ShowPlayerDTO(
                player.getName(),
                player.getNationality(),
                player.getTeam(),
                player.getEnergy(),
                player.getHappiness(),
                player.getState(),
                player.getMood(),
                player.getUser().getName()
        );
    }

    public String deletePlayer(Long id, HttpServletRequest request) {
        Player player = getAuthorizedPlayer(id, request);

        playerRepository.delete(player);
        return "Player deleted successfully";
    }

    public void training(Long id, HttpServletRequest request) {
        Player player = getAuthorizedPlayer(id, request);
        updateEnergyAndHappiness(player);

        player.setHappiness(player.getHappiness() + 15);
        player.setEnergy(player.getEnergy() - 10);
        player.updateMood();
        playerRepository.save(player);
    }

    public void sleeping(Long id, HttpServletRequest request) {
        Player player = getAuthorizedPlayer(id, request);
        updateEnergyAndHappiness(player);

        player.setHappiness(player.getHappiness() + 20);
        player.setEnergy(100);
        player.updateMood();
        playerRepository.save(player);
    }

    public void updateTeam(Long id, String newTeamDTO, HttpServletRequest request) {
        Player player = getAuthorizedPlayer(id, request);

        PlayerTeam newTeam = PlayerTeam.valueOf(newTeamDTO.toUpperCase());
        player.setTeam(newTeam);
        playerRepository.save(player);
    }
}