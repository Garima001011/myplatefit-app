package com.garima.myplatefit.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecipeSearchResult {

    private List<Recipe> results;

    public List<Recipe> getResults() {
        return results;
    }

    public void setResults(List<Recipe> results) {
        this.results = results;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Recipe {
        private int id;
        private String title;
        private String image;
        private double calories;

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }

        public String getImage() { return image; }
        public void setImage(String image) { this.image = image; }

        public double getCalories() { return calories; }
        public void setCalories(double calories) { this.calories = calories; }
    }
}
