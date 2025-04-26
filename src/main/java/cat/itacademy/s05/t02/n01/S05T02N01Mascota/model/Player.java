package cat.itacademy.s05.t02.n01.S05T02N01Mascota.model;
import cat.itacademy.s05.t02.n01.S05T02N01Mascota.enums.*;
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PlayerMood mood;

    private int energy;
    private int happiness;

    @Enumerated(EnumType.STRING)
    private HairStyle hairStyle;

    
    public Player() {
    }

    public Player(Long id, String name, PlayerNationality nationality, PlayerTeam team, User user, PlayerState state, PlayerMood mood, int energy, int happiness, HairStyle hairStyle) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
        this.team = team;
        this.user = user;
        this.state = state;
        this.mood = mood;
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
        if(energy > 100){
            energy = 100;
        }else if(energy < 0){
            energy = 0;
        }
        this.energy = energy;
    }

    public int getHappiness() {
        return happiness;
    }

    public void setHappiness(int happiness) {
        if(happiness > 100){
            happiness = 100;
        }else if(happiness < 0){
            happiness = 0;
        }
        this.happiness = happiness;
    }

    public HairStyle getHairStyle() {
        return hairStyle;
    }

    public void setHairStyle(HairStyle hairStyle) {
        this.hairStyle = hairStyle;
    }

    public PlayerMood getMood() {
        return mood;
    }

    public void setMood(PlayerMood mood) {
        this.mood = mood;
    }

    public void updateMood(){
        if(energy < 35 && happiness < 35){
            setMood(PlayerMood.SAD);
        }else if(energy > 65 && happiness > 65){
            setMood(PlayerMood.HAPPY);
        }else{
            setMood(PlayerMood.NEUTRAL);
        }
    }
}
