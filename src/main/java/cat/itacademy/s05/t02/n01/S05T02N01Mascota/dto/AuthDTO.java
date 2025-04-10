package cat.itacademy.s05.t02.n01.S05T02N01Mascota.dto;

public class AuthDTO {
    private String name;
    private String password;

    public AuthDTO() {
    }

    public AuthDTO(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
