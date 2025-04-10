package cat.itacademy.s05.t02.n01.S05T02N01Mascota.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){
        super(message);
    }
}
