package com.garima.myplatefit.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.garima.myplatefit.dto.RecipeDetailDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SpoonacularService {

    @Value("${spoonacular.api.key}")
    private String spoonacularApiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public RecipeDetailDTO getRecipeDetails(int recipeId) throws Exception {
        String url = "https://api.spoonacular.com/recipes/" + recipeId + "/information?includeNutrition=true&apiKey=" + spoonacularApiKey;

        String response = restTemplate.getForObject(url, String.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(response, RecipeDetailDTO.class);
    }
}
