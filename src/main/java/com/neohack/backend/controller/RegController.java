package com.neohack.backend.controller;

import com.neohack.backend.dto.RegistrationDto;
import com.neohack.backend.entity.User;
import com.neohack.backend.exception.AlreadyExistException;
import com.neohack.backend.mapper.UserMapper;
import com.neohack.backend.service.UserService;
import com.neohack.backend.service.impl.MailService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RegController {

    private final UserService userService;
    private final MailService mailService;

    @ApiOperation(value = "Страница регистрации нового пользователя")
    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegistrationDto registrationDto) throws AlreadyExistException {
        log.debug("createNewUser(): получен registrationDto: {}", registrationDto);
        User user = UserMapper.INSTANCE.toUser(registrationDto);
        log.debug("createNewUser(): registrationDto замаплен в User: {}", user);
        userService.createUser(user, registrationDto.isStudent());
        mailService.sendOkRegistrationMailToUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
