package smg.dealership.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import smg.dealership.dto.response.ResponseDTO;

import java.util.Arrays;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ResponseDTO> alreadyExistsException(AlreadyExistsException e) {
        ResponseDTO response = new ResponseDTO(401, HttpStatus.CONFLICT, new Object(), Arrays.asList(e.getMessage()));
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseDTO> notFoundException(NotFoundException e) {
        ResponseDTO response = new ResponseDTO(404, HttpStatus.NOT_FOUND, new Object(), Arrays.asList(e.getMessage()));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO> unexpectedException(Exception e) {
        e.printStackTrace(System.out);
        ResponseDTO response = new ResponseDTO(500, HttpStatus.INTERNAL_SERVER_ERROR, new Object(), Arrays.asList(e.getMessage()));
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
