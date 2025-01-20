package org.example.cinema.handler;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.JDBCException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorMessage> handleResponseStatusException(ResponseStatusException ex) {
        log.info("Exception occurred:", ex);
        ErrorMessage errorMessage = new ErrorMessage(ex.getStatusCode().value(), ex.getReason());
        return ResponseEntity.badRequest().body(errorMessage);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorMessage> handleConstraintViolationException(ConstraintViolationException ex) {
        log.info("Exception occurred:", ex);
        String errorText = "Verilənlər bazası tərəfdə təyin edilmiş məhdudiyyətlər pozulub.";
        ErrorMessage errorMessage = new ErrorMessage(INTERNAL_SERVER_ERROR.value(), errorText);
        return ResponseEntity.badRequest().body(errorMessage);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorMessage> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        log.info("Exception occurred:", ex);
        String errorText = "Daxili server xətası.";
        ErrorMessage errorMessage = new ErrorMessage(INTERNAL_SERVER_ERROR.value(), errorText);
        return ResponseEntity.badRequest().body(errorMessage);
    }

    @ExceptionHandler({JDBCException.class})
    public ResponseEntity<ErrorMessage> handleJDBCException(JDBCException ex) {
        log.info("Exception occurred:", ex);
        String errorText = "Daxili server xətası.";
        ErrorMessage errorMessage = new ErrorMessage(INTERNAL_SERVER_ERROR.value(), errorText);
        return ResponseEntity.badRequest().body(errorMessage);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessage> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.info("Exception occurred:", ex);
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Naməlum xəta");
        return ResponseEntity.badRequest().body(errorMessage);
    }

}
