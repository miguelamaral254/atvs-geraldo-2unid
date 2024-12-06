package com.example.scrappingchallenge.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    /**
     * Envia um e-mail com uma lista de notícias.
     */
    public void sendSimpleEmail(String to, List<String> newsList) {
        StringBuilder content = new StringBuilder("Confira as últimas notícias:\n\n");
        for (String news : newsList) {
            content.append("- ").append(news).append("\n");
        }

        sendEmail(to, "Últimas Notícias", content.toString());
    }

    /**
     * Envia um e-mail com o conteúdo especificado.
     */
    public void sendEmail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        message.setFrom("no-reply@qascriptcase.myscriptcase.com");

        mailSender.send(message);
    }
}