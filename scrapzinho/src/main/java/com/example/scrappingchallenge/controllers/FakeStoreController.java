package com.example.scrappingchallenge.controllers;

import com.example.scrappingchallenge.services.FakeStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class FakeStoreController {

    @Autowired
    private FakeStoreService fakeStoreService;

    /**
     * Retorna todos os produtos da API Fake Store.
     */
    @GetMapping("/products")
    public ResponseEntity<?> getProducts() {
        try {
            List<Map<String, Object>> products = fakeStoreService.getProducts();
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(Map.of("error", "Erro ao obter produtos", "details", e.getMessage()));
        }
    }

    /**
     * Retorna um produto específico da API Fake Store pelo ID.
     */
    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id) {
        try {
            Map<String, Object> product = fakeStoreService.getProductById(id);
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(Map.of("error", "Erro ao obter o produto", "details", e.getMessage()));
        }
    }
}