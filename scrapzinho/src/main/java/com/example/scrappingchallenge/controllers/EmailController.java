package com.example.scrappingchallenge.controllers;

import com.example.scrappingchallenge.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/send-email")
    public String sendEmail(@RequestParam String to) {
        String subject = "Scrapping Challenge - Resultados";
        String content = "Este é um teste de envio de e-mail usando o servidor SMTP configurado.";

        try {
            emailService.sendEmail(to, subject, content);
            return "E-mail enviado com sucesso para: " + to;
        } catch (Exception e) {
            return "Erro ao enviar e-mail: " + e.getMessage();
        }
    }
}