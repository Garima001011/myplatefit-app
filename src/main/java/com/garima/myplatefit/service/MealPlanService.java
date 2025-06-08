package com.garima.myplatefit.service;

import com.garima.myplatefit.dto.MealPlanDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MealPlanService {

    @Value("${spoonacular.api.key}")
    private String apiKey;

    public MealPlanDTO generateMealPlan(int targetCalories) {
        String url = "https://api.spoonacular.com/mealplanner/generate?timeFrame=day&targetCalories="
                + targetCalories + "&apiKey=" + apiKey;

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, MealPlanDTO.class);
    }
}
