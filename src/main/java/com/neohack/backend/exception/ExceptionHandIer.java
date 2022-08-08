package com.neohack.backend.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandIer {

    @ExceptionHandler(NoElementException.class)
    public ResponseEntity<String> NoElementFound(NoElementException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserDisabledException.class)
    public ResponseEntity<String> UserIsDisabled(UserDisabledException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.LOCKED);
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<String> UserAlreadyExist(AlreadyExistException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.LOCKED);
    }

    @ExceptionHandler(SesCodeException.class)
    public ResponseEntity<String> SesCodeException(SesCodeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
