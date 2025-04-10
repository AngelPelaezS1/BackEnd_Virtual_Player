package cat.itacademy.s05.t02.n01.S05T02N01Mascota.exception;

public class NameExistsException extends RuntimeException{
    public NameExistsException(String message){
        super(message);
    }
}
