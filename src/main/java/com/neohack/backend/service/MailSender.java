package com.neohack.backend.service;

public interface MailSender {
    void sendEmail(String to, String subject, String text);
}
