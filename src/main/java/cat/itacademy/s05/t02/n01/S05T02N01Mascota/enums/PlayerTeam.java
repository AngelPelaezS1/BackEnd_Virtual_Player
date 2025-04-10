package cat.itacademy.s05.t02.n01.S05T02N01Mascota.enums;

import java.util.Random;

public enum PlayerTeam {

    BARCELONA, CORINTHIANS, FLAMENGO;

    public static PlayerTeam getRandom(){
        PlayerTeam[] values = values();
        return values[new Random().nextInt(values().length)];
    }
}
