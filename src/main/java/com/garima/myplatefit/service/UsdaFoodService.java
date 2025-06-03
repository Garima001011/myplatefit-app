package com.garima.myplatefit.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Service
public class UsdaFoodService {

    @Value("${usda.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public Map<String, Object> searchFood(String query) {
        String url = "https://api.nal.usda.gov/fdc/v1/foods/search";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("api_key", apiKey)
                .queryParam("query", query)
                .queryParam("pageSize", 1);

        ResponseEntity<Map> response = restTemplate.getForEntity(builder.toUriString(), Map.class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            List<Map<String, Object>> foods = (List<Map<String, Object>>) response.getBody().get("foods");
            if (foods != null && !foods.isEmpty()) {
                return foods.get(0); // return the first match
            }
        }
        return Collections.emptyMap();
    }

    public List<Map<String, Object>> searchFoods(String query) {
        String url = "https://api.nal.usda.gov/fdc/v1/foods/search";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("api_key", apiKey)
                .queryParam("query", query)
                .queryParam("pageSize", 10);

        ResponseEntity<Map> response = restTemplate.getForEntity(builder.toUriString(), Map.class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return (List<Map<String, Object>>) response.getBody().get("foods");
        }

        return Collections.emptyList();
    }
}
