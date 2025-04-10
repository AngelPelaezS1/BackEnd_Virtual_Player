package cat.itacademy.s05.t02.n01.S05T02N01Mascota.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception e){
        return new ResponseEntity<>("Error, " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NameExistsException.class)
    public ResponseEntity<String> nameDoesExists(NameExistsException e){
        return new ResponseEntity<>("Error, " +e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EmptyException.class)
    public ResponseEntity<String> emptyException(EmptyException e){
        return new ResponseEntity<>("Error, " +e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<String> loginException(LoginException e){
        return new ResponseEntity<>("Error, " +e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> userNotFoundException(UserNotFoundException e){
        return new ResponseEntity<>("Error, " +e.getMessage(),HttpStatus.NOT_FOUND);
    }
}
