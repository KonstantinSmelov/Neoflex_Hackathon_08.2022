package com.neohack.backend.controller;

import com.neohack.backend.exception.NoElementException;
import com.neohack.backend.security.UserDetailsImpl;
import com.neohack.backend.service.CourseService;
import com.neohack.backend.service.UserService;
import com.neohack.backend.service.impl.MailService;
import com.neohack.backend.service.impl.SesCodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TestSecureController {

    @GetMapping("/secured_page")
    public ResponseEntity<?> securedPage() throws NoElementException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        System.out.println(user.getUsername() + " " + user.getEmail());
        if (user.getEmail() == null) {
            throw new NoElementException("Пользователя нет в контексте");
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/check_user")
    public ResponseEntity<?> check(Principal principal) throws NoElementException {
        if (principal == null) {
            throw new NoElementException("Пользователя нет в Principal");
        }
        return new ResponseEntity<>(principal, HttpStatus.OK);
    }

}
