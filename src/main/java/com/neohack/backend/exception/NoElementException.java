package com.neohack.backend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NoElementException extends Exception {
    private String message;
}
