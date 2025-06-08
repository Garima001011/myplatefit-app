package com.garima.myplatefit.controller;

import com.garima.myplatefit.dto.RecipeDetailDTO;
import com.garima.myplatefit.service.SpoonacularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RecipeController {

    @Autowired
    private SpoonacularService spoonacularService;

    @GetMapping("/recipe/{id}")
    public String getRecipeDetail(@PathVariable int id, Model model) {
        try {
            RecipeDetailDTO recipe = spoonacularService.getRecipeDetails(id);
            model.addAttribute("recipe", recipe);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Unable to fetch recipe details.");
        }
        return "recipe-detail";
    }
}
