package com.garima.myplatefit.controller;

import com.garima.myplatefit.service.UsdaFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class FoodController {

    @Autowired
    private UsdaFoodService usdaFoodService;

    @GetMapping("/api/searchFood")
    public Map<String, Object> searchFood(@RequestParam String query) {
        return usdaFoodService.searchFood(query);
    }

    @GetMapping("/api/suggestions")
    public List<Map<String, Object>> getSuggestions(@RequestParam String query) {
        return usdaFoodService.searchFoods(query);
    }

}
