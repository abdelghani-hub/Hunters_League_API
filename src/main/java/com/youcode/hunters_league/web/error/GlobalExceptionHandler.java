package com.youcode.hunters_league.web.error;

import com.youcode.hunters_league.exception.*;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String , List<String>>> handleValidationExceptions(MethodArgumentNotValidException exception){
        Map<String , List<String>> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            if(!errors.containsKey(error.getField())){
                List<String> values = new ArrayList<>();
                values.add(error.getDefaultMessage());
                errors.put(error.getField() , values);
            }else
                errors.get(error.getField()).add(error.getDefaultMessage());
        });
        return new ResponseEntity<>(errors , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<Map<String, String>> handleAlreadyExistException(AlreadyExistException exception) {
        Map<String, String> error = new HashMap<>();
        error.put("error", exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEntityNotFoundException(EntityNotFoundException exception) {
        Map<String, String> error = new HashMap<>();
        error.put("error", exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MismatchException.class)
    public ResponseEntity<Map<String, List<String>>> handleMismatchException(MismatchException exception) {
        Map<String, List<String>> errors = new HashMap<>();
        List<String> values = new ArrayList<>();
        values.add(exception.getMessage());
        errors.put(exception.getField(), values);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Map<String, String>> handleInvalidCredentialsException(InvalidCredentialsException exception) {
        Map<String, String> error = new HashMap<>();
        error.put("error", exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NullOrBlankArgException.class)
    public ResponseEntity<Map<String, String>> handleNullOrBlankArgException(NullOrBlankArgException exception) {
        Map<String, String> error = new HashMap<>();
        error.put("error", exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotValidConstraintException.class)
    public ResponseEntity<Map<String, String>> handleNotValidConstraintException(NotValidConstraintException exception) {
        Map<String, String> error = new HashMap<>();
        error.put("error", exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<Map<String, String>> handleDateTimeParseException(DateTimeParseException exception) {
        Map<String, String> error = new HashMap<>();
        error.put("error", exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // JWT Exceptions
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Map<String, String>> handleExpiredJwtException(ExpiredJwtException exception) {
        Map<String, String> error = new HashMap<>();
        error.put("error", exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }
}
