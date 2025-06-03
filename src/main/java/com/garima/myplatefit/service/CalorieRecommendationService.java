package com.garima.myplatefit.service;

import com.garima.myplatefit.model.User;
import org.springframework.stereotype.Service;

@Service
public class CalorieRecommendationService {

    public double calculateTDEE(User user) {
        if (user.getWeight() == null ||
                user.getHeight() == null ||
                user.getAge() == null ||
                user.getGender() == null) {
            return 0;
        }

        // Calculate BMR (Basal Metabolic Rate)
        double bmr = calculateBMR(user);

        // Apply activity factor
        double activityFactor = getActivityFactor(user.getExerciseLevel());
        double tdee = bmr * activityFactor;

        // Adjust for weight goal
        return Math.round(adjustForGoal(tdee, user.getGoal()));
    }

    private double calculateBMR(User user) {
        if (user.getGender().equalsIgnoreCase("Male")) {
            return 10 * user.getWeight() + 6.25 * user.getHeight() - 5 * user.getAge() + 5;
        } else {
            return 10 * user.getWeight() + 6.25 * user.getHeight() - 5 * user.getAge() - 161;
        }
    }

    private double getActivityFactor(String exerciseLevel) {
        if (exerciseLevel == null) return 1.2;

        return switch (exerciseLevel) {
            case "Active" -> 1.725;
            case "Moderate" -> 1.55;
            case "None" -> 1.2;
            default -> 1.2;
        };
    }

    private double adjustForGoal(double tdee, String goal) {
        if (goal == null) return tdee;

        return switch (goal) {
            case "Lose" -> tdee - 500;  // 500 calorie deficit for weight loss
            case "Gain" -> tdee + 300;  // 300 calorie surplus for weight gain
            default -> tdee;            // Maintain current weight
        };
    }
}