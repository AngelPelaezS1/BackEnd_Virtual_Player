package cat.itacademy.s05.t02.n01.S05T02N01Mascota.dto;

import cat.itacademy.s05.t02.n01.S05T02N01Mascota.enums.HairStyle;
import cat.itacademy.s05.t02.n01.S05T02N01Mascota.enums.PlayerNationality;
import cat.itacademy.s05.t02.n01.S05T02N01Mascota.enums.PlayerState;
import cat.itacademy.s05.t02.n01.S05T02N01Mascota.enums.PlayerTeam;

public class ShowPlayerDTO {
    private String name;
    private PlayerNationality nationality;
    private PlayerTeam team;
    private int energy;
    private int happiness;
    private HairStyle hairStyle;
    private PlayerState playerState;

    public ShowPlayerDTO(String name, PlayerNationality nationality, PlayerTeam team, int energy, int happiness, HairStyle hairStyle, PlayerState playerState) {
        this.name = name;
        this.nationality = nationality;
        this.team = team;
        this.energy = energy;
        this.happiness = happiness;
        this.hairStyle = hairStyle;
        this.playerState = playerState;
    }
}
