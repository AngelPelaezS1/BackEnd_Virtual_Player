package cat.itacademy.s05.t02.n01.S05T02N01Mascota.dto;

import cat.itacademy.s05.t02.n01.S05T02N01Mascota.enums.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.ALWAYS)
public class ShowPlayerDTO {
    private String name;
    private PlayerNationality nationality;
    private PlayerTeam team;
    private int energy;
    private int happiness;
    private String userName;

    @JsonProperty("mood")
    private PlayerMood mood;

    public ShowPlayerDTO(String name, PlayerNationality nationality, PlayerTeam team, int energy, int happiness, String userName, PlayerMood mood) {
        this.name = name;
        this.nationality = nationality;
        this.team = team;
        this.energy = energy;
        this.happiness = happiness;
        this.userName = userName;
        this.mood = mood;
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

    public int getEnergy() {
        return energy;
    }

    public int getHappiness() {
        return happiness;
    }

    public String getUserName() {
        return userName;
    }

    public PlayerMood getMood() {
        return mood;
    }
}
