package com.garima.myplatefit.controller;

import com.garima.myplatefit.dto.MealPlanDTO;
import com.garima.myplatefit.model.User;
import com.garima.myplatefit.service.MealPlanService;
import com.garima.myplatefit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MealPlanController {

    @Autowired
    private MealPlanService mealPlanService;

    @Autowired
    private UserService userService;

    @GetMapping("/meal-plan")
    public String generateMealPlan(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        // Get logged-in user's email from Spring Security
        String email = userDetails.getUsername();

        // Fetch full User object from DB
        User user = userService.getByEmail(email);

        int calories = user.getRecommendedCalories() != null ? user.getRecommendedCalories().intValue() : 2000;

        try {
            MealPlanDTO mealPlan = mealPlanService.generateMealPlan(calories);
            model.addAttribute("mealPlan", mealPlan);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Unable to generate meal plan at this time.");
        }

        return "meal-plan";
    }
}
