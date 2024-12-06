package com.example.scrappingchallenge.controllers;

import com.example.scrappingchallenge.services.EmailService;
import com.example.scrappingchallenge.services.FakeStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
class ScrappingController {

    @Autowired
    private FakeStoreService fakeStoreService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/send-products-email")
    public String sendProductsEmail(@RequestParam String email) {
        try {
            // Obter os produtos da API
            List<String> productDescriptions = fakeStoreService.getProducts().stream()
                    .map(product -> product.get("title") + " - $" + product.get("price"))
                    .collect(Collectors.toList());

            // Enviar o e-mail
            emailService.sendSimpleEmail(email, productDescriptions);

            return "E-mail enviado com sucesso para: " + email;
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar o e-mail: " + e.getMessage();
        }
    }
}