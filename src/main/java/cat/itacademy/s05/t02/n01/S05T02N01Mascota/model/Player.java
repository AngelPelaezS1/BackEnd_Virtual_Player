package cat.itacademy.s05.t02.n01.S05T02N01Mascota.model;

import cat.itacademy.s05.t02.n01.S05T02N01Mascota.enums.HairStyle;
import cat.itacademy.s05.t02.n01.S05T02N01Mascota.enums.PlayerNationality;
import cat.itacademy.s05.t02.n01.S05T02N01Mascota.enums.PlayerState;
import cat.itacademy.s05.t02.n01.S05T02N01Mascota.enums.PlayerTeam;
import jakarta.persistence.*;

@Entity
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PlayerNationality nationality;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PlayerTeam team;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PlayerState state;

    private int energy;
    private int happiness;

    @Enumerated(EnumType.STRING)
    private HairStyle hairStyle;

    
    public Player() {
    }

    public Player(Long id, String name, PlayerNationality nationality, PlayerTeam team, User user, PlayerState state, int energy, int happiness, HairStyle hairStyle) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
        this.team = team;
        this.user = user;
        this.state = state;
        this.energy = energy;
        this.happiness = happiness;
        this.hairStyle = hairStyle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public PlayerTeam getTeam() {
        return team;
    }

    public void setTeam(PlayerTeam team) {
        this.team = team;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PlayerState getState() {
        return state;
    }

    public void setState(PlayerState state) {
        this.state = state;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getHappiness() {
        return happiness;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public HairStyle getHairStyle() {
        return hairStyle;
    }

    public void setHairStyle(HairStyle hairStyle) {
        this.hairStyle = hairStyle;
    }
}
