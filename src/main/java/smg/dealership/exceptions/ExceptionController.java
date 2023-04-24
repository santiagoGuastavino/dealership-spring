package smg.dealership.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import smg.dealership.dto.response.ErrorDTO;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<?> alreadyExistsException(AlreadyExistsException e) {
        ErrorDTO error = new ErrorDTO(401, HttpStatus.CONFLICT);
    }
}
