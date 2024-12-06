package com.example.scrappingchallenge.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class FakeStoreService {

    @Value("${fake.store.api.url}")
    private String apiUrl;

    private final WebClient.Builder webClientBuilder;

    public FakeStoreService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    /**
     * Obtém a lista de produtos da Fake Store API.
     */
    public List<Map<String, Object>> getProducts() {
        return webClientBuilder.build()
                .get()
                .uri(apiUrl + "/products")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Map<String, Object>>>() {})
                .block();
    }

    /**
     * Obtém os detalhes de um produto específico pelo ID.
     */
    public Map<String, Object> getProductById(int id) {
        return webClientBuilder.build()
                .get()
                .uri(apiUrl + "/products/" + id)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                .block();
    }
}