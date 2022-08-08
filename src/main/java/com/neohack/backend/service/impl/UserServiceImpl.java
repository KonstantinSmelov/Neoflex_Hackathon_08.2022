package com.neohack.backend.service.impl;

import com.neohack.backend.dao.StudentRepository;
import com.neohack.backend.entity.Student;
import com.neohack.backend.entity.Teacher;
import com.neohack.backend.entity.User;
import com.neohack.backend.model.StaticSes;
import com.neohack.backend.service.StudentService;
import com.neohack.backend.service.TeacherService;
import lombok.RequiredArgsConstructor;
import com.neohack.backend.dao.UserRepository;
import com.neohack.backend.exception.AlreadyExistException;
import com.neohack.backend.exception.NoElementException;
import com.neohack.backend.model.Role;
import com.neohack.backend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final StudentService studentService;
    private final TeacherService teacherService;

    @Override
    public void createUser(User user, boolean isStudent) throws AlreadyExistException {
        log.debug("createUser(): получен user: {}", user);
        if (canCreateThisUser(user)) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setActive(true);
            userRepository.save(user);
            log.debug("createUser(): пользователь {} создан!", user.getUsername());
            log.debug("{}", user);
            if (isStudent) {
                user.setRoles(Collections.singleton(Role.ROLE_USER));
                Student student = Student.builder().user(user).build();
                studentService.createStudent(student);
            } else {
                user.setRoles(Collections.singleton(Role.ROLE_ADMIN));
                Teacher teacher = Teacher.builder().user(user).build();
                teacherService.createTeacher(teacher);
            }
        } else {
            log.debug("createUser(): username [{}] или email [{}] уже используются другими пользователями!", user.getUsername(), user.getEmail());
            throw new AlreadyExistException(String.format("Username [%s] или email [%s] уже используются другими пользователями!", user.getUsername(), user.getEmail()));
        }
    }

    @Override
    public User getUserByName(String username) throws NoElementException {
        log.debug("getUserByName(): пытаемся найти пользователя по username {}", username);
        User user = userRepository.findByUsername(username).orElseThrow(() -> new NoElementException(String.format("Пользователя с именем [%s] не существует!", username)));
        log.debug("getUserByName(): пользователь {} найден", user.getUsername());
        return user;
    }

    @Override
    public User getUserByEmail(String email) throws NoElementException {
        log.debug("getUserByEmail(): пытаемся найти пользователя по email {}", email);
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NoElementException(String.format("Пользователя с email %s не существует!", email)));
        log.debug("getUserByEmail(): пользователь c email {} найден", user.getEmail());
        return user;
    }

    @Override
    public void setNewPassByEmail(String email, String password) throws NoElementException {
        User user = getUserByEmail(email);
        log.debug("createUser(): взят из БД user: {}", user);
        //TODO тут должен быть код, которы не позволяет менять пароль, если SES код ранее не был подтверждён могу не успеть реализовать
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        log.debug("createUser(): Пользователю user установлен новый пароль: {}", user);
    }

    private boolean canCreateThisUser(User checking) {
        log.debug("canCreateThisUser(): сверяем с БД username [{}] и email [{}] на уникальность", checking.getUsername(), checking.getEmail());
        boolean flag = (userRepository.findUserWithUsernameOrEmail(checking.getUsername(), checking.getEmail()).size() == 0);
        log.debug("canCreateThisUser(): уникальность username [{}] и email [{}] = {}", checking.getUsername(), checking.getEmail(), flag);
        return flag;
    }


}
