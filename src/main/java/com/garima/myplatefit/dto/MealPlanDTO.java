package com.garima.myplatefit.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MealPlanDTO {
    private List<Meal> meals;
    private Nutrients nutrients;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Meal {
        private int id;
        private String title;
        private String imageType;
        private String image;
        private int readyInMinutes;
        private int servings;
        private String sourceUrl;
        private double calories; // ✅ Added this field

        // Getters & Setters
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }

        public String getImageType() { return imageType; }
        public void setImageType(String imageType) { this.imageType = imageType; }

        public int getReadyInMinutes() { return readyInMinutes; }
        public void setReadyInMinutes(int readyInMinutes) { this.readyInMinutes = readyInMinutes; }

        public int getServings() { return servings; }
        public void setServings(int servings) { this.servings = servings; }

        public String getSourceUrl() { return sourceUrl; }
        public void setSourceUrl(String sourceUrl) { this.sourceUrl = sourceUrl; }

        public String getImage() { return image; }
        public void setImage(String image) { this.image = image; }

        public double getCalories() { return calories; }
        public void setCalories(double calories) { this.calories = calories; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Nutrients {
        private double calories;
        private double protein;
        private double fat;
        private double carbohydrates;

        // Getters & Setters
        public double getCalories() { return calories; }
        public void setCalories(double calories) { this.calories = calories; }

        public double getProtein() { return protein; }
        public void setProtein(double protein) { this.protein = protein; }

        public double getFat() { return fat; }
        public void setFat(double fat) { this.fat = fat; }

        public double getCarbohydrates() { return carbohydrates; }
        public void setCarbohydrates(double carbohydrates) { this.carbohydrates = carbohydrates; }
    }

    // Main DTO getters
    public List<Meal> getMeals() { return meals; }
    public void setMeals(List<Meal> meals) { this.meals = meals; }

    public Nutrients getNutrients() { return nutrients; }
    public void setNutrients(Nutrients nutrients) { this.nutrients = nutrients; }
}
