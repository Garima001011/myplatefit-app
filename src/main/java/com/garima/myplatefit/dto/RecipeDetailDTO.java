package com.garima.myplatefit.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecipeDetailDTO {
    private String title;
    private String image;
    private int readyInMinutes;
    private int servings;
    private List<Ingredient> extendedIngredients;
    private Nutrition nutrition;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Ingredient {
        private String name;
        private double amount;
        private String unit;

        // getters & setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public double getAmount() { return amount; }
        public void setAmount(double amount) { this.amount = amount; }

        public String getUnit() { return unit; }
        public void setUnit(String unit) { this.unit = unit; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Nutrition {
        private List<Nutrient> nutrients;

        public List<Nutrient> getNutrients() { return nutrients; }
        public void setNutrients(List<Nutrient> nutrients) { this.nutrients = nutrients; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Nutrient {
        private String name;
        private double amount;
        private String unit;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public double getAmount() { return amount; }
        public void setAmount(double amount) { this.amount = amount; }

        public String getUnit() { return unit; }
        public void setUnit(String unit) { this.unit = unit; }
    }

    // top level getters/setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public int getReadyInMinutes() { return readyInMinutes; }
    public void setReadyInMinutes(int readyInMinutes) { this.readyInMinutes = readyInMinutes; }

    public int getServings() { return servings; }
    public void setServings(int servings) { this.servings = servings; }

    public List<Ingredient> getExtendedIngredients() { return extendedIngredients; }
    public void setExtendedIngredients(List<Ingredient> extendedIngredients) { this.extendedIngredients = extendedIngredients; }

    public Nutrition getNutrition() { return nutrition; }
    public void setNutrition(Nutrition nutrition) { this.nutrition = nutrition; }
}
