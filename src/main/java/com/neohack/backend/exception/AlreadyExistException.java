package com.neohack.backend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AlreadyExistException extends Exception {
    private String message;
}
