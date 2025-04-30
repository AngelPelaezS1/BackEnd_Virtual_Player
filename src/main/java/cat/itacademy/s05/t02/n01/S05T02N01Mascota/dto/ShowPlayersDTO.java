package cat.itacademy.s05.t02.n01.S05T02N01Mascota.dto;

import cat.itacademy.s05.t02.n01.S05T02N01Mascota.enums.*;

public class ShowPlayersDTO {
    private Long id;
    private String name;
    private PlayerNationality nationality;
    private PlayerTeam team;
    private String userName;

    public ShowPlayersDTO(Long id, String name, PlayerNationality nationality, PlayerTeam team, String userName) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
        this.team = team;
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public PlayerNationality getNationality() {
        return nationality;
    }
    public PlayerTeam getTeam() {
        return team;
    }
    public String getUserName() {
        return userName;
    }
}
