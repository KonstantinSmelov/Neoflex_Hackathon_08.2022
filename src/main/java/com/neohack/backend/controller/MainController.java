package com.neohack.backend.controller;

import com.neohack.backend.dto.CourseDto;
import com.neohack.backend.dto.LoginDto;
import com.neohack.backend.dto.RegistrationDto;
import com.neohack.backend.entity.Course;
import com.neohack.backend.exception.SesCodeException;
import com.neohack.backend.mapper.CourseMapper;
import com.neohack.backend.service.CourseService;
import com.neohack.backend.service.impl.SesCodeService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.neohack.backend.entity.User;
import com.neohack.backend.exception.AlreadyExistException;
import com.neohack.backend.exception.NoElementException;
import com.neohack.backend.mapper.UserMapper;
import com.neohack.backend.security.UserDetailsImpl;
import com.neohack.backend.service.UserService;
import com.neohack.backend.service.impl.MailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@Slf4j
//@CrossOrigin
public class MainController {

    private final UserService userService;
    private final MailService mailService;
    private final SesCodeService sesCodeService;
    private final CourseService courseService;

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

    @ApiOperation(value = "Страница восстановления доступа к аккаунту № 1")
    @GetMapping("/restore/{email}")
    public void sendSesCodeToEmail(@PathVariable String email) throws NoElementException {
        sesCodeService.sendSesToEmail(email);
    }

    @ApiOperation(value = "Страница восстановления доступа к аккаунту № 2")
    @PostMapping("/restore/{email}/ses")
    public void resetPassBySesCode(@RequestBody Integer sesCode, @PathVariable String email) throws SesCodeException {
        sesCodeService.checkSesCode(sesCode, email);
    }

    @ApiOperation(value = "Страница для создания нового пароля")
    @PostMapping("/restore/{email}/new_pass")
    public void setNewPass(@RequestBody String newPassword, @PathVariable String email) throws NoElementException {
        userService.setNewPassByEmail(email, newPassword);
    }

    @ApiOperation(value = "Создание нового курса преподавателем")
    @PostMapping("/course")
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseDto courseDto, Principal principal) throws NoElementException {
        log.debug("createCourse(): получен courseDto: {}", courseDto);
        Course course = CourseMapper.INSTANCE.toCourse(courseDto);
        courseService.createCourse(course, principal.getName());
        log.debug("createCourse(): courseDto замаплен в course: {}", course);


        return new ResponseEntity<>(courseDto, HttpStatus.OK);
    }
}
