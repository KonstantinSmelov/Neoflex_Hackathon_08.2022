package com.neohack.backend.service.impl;

import lombok.RequiredArgsConstructor;
import com.neohack.backend.entity.User;
import com.neohack.backend.service.MailSender;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;

@Service
@RequiredArgsConstructor
public class MailService {

    private final MailSender mailSender;
    private final ExecutorService executorService;

    public void sendOkRegistrationMailToUser(User user) {
        executorService.execute(() -> mailSender.sendEmail(
                user.getEmail(),
                "Образовательный портал (успешная регистрация)",
                String.format("Вы успешно зарегистрировались на образовательном портале с username %s", user.getUsername())));
    }

    public void sendSesCodeMail(String email, Integer sesCode) {
        executorService.execute(() -> mailSender.sendEmail(
                email,
                "Образовательный портал (SES-код)",
                String.format("Введите код %s на странице восстановления доступа к аккаунту", sesCode)));
    }
}
