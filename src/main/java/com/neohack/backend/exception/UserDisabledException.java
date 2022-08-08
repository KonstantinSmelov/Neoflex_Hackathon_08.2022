package com.neohack.backend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserDisabledException extends Exception {
    private String message;
}