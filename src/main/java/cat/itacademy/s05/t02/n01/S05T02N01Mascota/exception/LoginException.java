package cat.itacademy.s05.t02.n01.S05T02N01Mascota.exception;

public class LoginException extends RuntimeException {
    public LoginException(String message){
        super(message);
    }
}
