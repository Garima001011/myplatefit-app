package com.garima.myplatefit.controller;

import com.garima.myplatefit.model.Meal;
import com.garima.myplatefit.model.User;
import com.garima.myplatefit.model.WeightLog;
import com.garima.myplatefit.repository.MealRepository;
import com.garima.myplatefit.repository.UserRepository;
import com.garima.myplatefit.repository.WeightLogRepository;
import com.garima.myplatefit.service.CalorieRecommendationService;
import com.garima.myplatefit.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DashboardController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeightLogRepository weightLogRepository;

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private CalorieRecommendationService calorieRecommendationService;

    @GetMapping("/dashboard")
    public String showDashboard(Authentication authentication, Model model) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) return "redirect:/login";

        // Fetch weight logs
        List<WeightLog> logs = weightLogRepository.findByUserOrderByDateDesc(user);

        // Fetch today's meals
        List<Meal> mealsToday = mealRepository.findByUserAndDate(user, LocalDate.now());

        // Calculate total calories today
        int totalCalories = mealsToday.stream().mapToInt(Meal::getCalories).sum();

        // Add attributes to model
        model.addAttribute("logs", logs);
        model.addAttribute("meals", mealsToday);
        model.addAttribute("user", user);
        model.addAttribute("bmi", calculateBMI(logs));
        model.addAttribute("weightLog", new WeightLog());
        model.addAttribute("meal", new Meal());
        model.addAttribute("totalCalories", totalCalories);
        double recommendedCalories = calorieRecommendationService.calculateTDEE(user);
        model.addAttribute("recommendedCalories", recommendedCalories);
        return "dashboard";
    }

    @PostMapping("/addWeight")
    public String addWeight(@ModelAttribute WeightLog weightLog, Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) return "redirect:/login";

        weightLog.setDate(LocalDate.now());
        weightLog.setUser(user);
        weightLogRepository.save(weightLog);

        return "redirect:/dashboard";
    }

    @PostMapping("/addMeal")
    public String addMeal(@ModelAttribute Meal meal, Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) return "redirect:/login";

        meal.setDate(LocalDate.now());
        meal.setUser(user);
        mealRepository.save(meal);

        return "redirect:/dashboard";
    }

    private String calculateBMI(List<WeightLog> logs) {
        if (logs.isEmpty()) return "N/A";
        double weight = logs.get(0).getWeight(); // most recent
        double height = 1.65; // Placeholder (you can make this user-specific later)
        double bmi = weight / (height * height);
        return String.format("%.1f", bmi);
    }

    @GetMapping("/chart-data")
    @ResponseBody
    public Map<String, Object> getChartData(Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElse(null);

        Map<String, Object> response = new HashMap<>();
        response.put("weightLogs", dashboardService.getWeightLogs(user));
        response.put("calorieLogs", dashboardService.getCalorieLogs(user));

        return response;
    }
}
