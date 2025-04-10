package cat.itacademy.s05.t02.n01.S05T02N01Mascota.dto;

import cat.itacademy.s05.t02.n01.S05T02N01Mascota.enums.PlayerNationality;

public class CreatePlayerDTO {

    private String name;
    private PlayerNationality nationality;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerNationality getNationality() {
        return nationality;
    }

    public void setNationality(PlayerNationality nationality) {
        this.nationality = nationality;
    }
}
