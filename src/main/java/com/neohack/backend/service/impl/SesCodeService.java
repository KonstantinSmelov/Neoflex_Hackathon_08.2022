package com.neohack.backend.service.impl;

import com.neohack.backend.dao.UserRepository;
import com.neohack.backend.entity.User;
import com.neohack.backend.exception.NoElementException;
import com.neohack.backend.exception.SesCodeException;
import com.neohack.backend.model.StaticSes;
import com.neohack.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class SesCodeService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    public void sendSesToEmail(String email) throws NoElementException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NoElementException(String.format("Пользователя с email %s не существует!", email)));
        StaticSes.userEmailAndSesCode.put(email, generateSesCode());
        mailService.sendSesCodeMail(user.getEmail(), StaticSes.userEmailAndSesCode.get(user.getEmail()));
        log.debug("sendSesToEmail(): sesCode отправлен на email {}", user.getEmail());
    }

    public Integer generateSesCode() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int x = 0; x < 4; x++) {
            stringBuilder.append((int) (Math.random() * 9) + 1);
        }
        log.debug("generateSesCode(): сгенерирован SES код {}", stringBuilder);
        return Integer.parseInt(stringBuilder.toString());
    }

    public void checkSesCode(Integer sesFromUser, String email) throws SesCodeException {

        log.debug("checkSesCode(): ожидаемый SES код: {}", StaticSes.userEmailAndSesCode.get(email));
        log.debug("checkSesCode(): введённый SES код: {}", sesFromUser);

        if (!Objects.equals(StaticSes.userEmailAndSesCode.get(email), sesFromUser)) {
            log.debug("checkSesCode(): неверный SES-код");
            throw new SesCodeException("Неверный SES-код!!!");
        }

        StaticSes.userEmailAndSesCode.put(email, 0);
    }
}