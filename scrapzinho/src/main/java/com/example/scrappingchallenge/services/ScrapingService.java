package com.example.scrappingchallenge.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScrapingService {

    /**
     * Realiza scraping de uma página de notícias e retorna os títulos.
     */
    public List<String> scrapeLatestNews() throws IOException {
        List<String> newsList = new ArrayList<>();
        String url = "https://example.com/news"; // Substitua pela URL correta

        // Faz a requisição HTTP e obtém o HTML
        Document document = Jsoup.connect(url).get();

        // Seleciona os elementos desejados (exemplo: títulos de notícias)
        for (Element element : document.select(".news-title")) {
            newsList.add(element.text());
        }

        return newsList;
    }
}