package com.garima.myplatefit.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    private Double weight;         // in kg
    private Double height;         // in cm
    private Integer age;
    private String gender;
    private String exerciseLevel;
    private String profilePicturePath;
    private String goal;
    private Integer recommendedCalories;


    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }

    public Double getHeight() { return height; }
    public void setHeight(Double height) { this.height = height; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getExerciseLevel() { return exerciseLevel; }
    public void setExerciseLevel(String exerciseLevel) { this.exerciseLevel = exerciseLevel; }

    public String getProfilePicturePath() { return profilePicturePath; }
    public void setProfilePicturePath(String profilePicturePath) { this.profilePicturePath = profilePicturePath; }

    public String getGoal() { return goal; }
    public void setGoal(String goal) { this.goal = goal; }

    public Integer getRecommendedCalories() { return recommendedCalories; }
    public void setRecommendedCalories(Integer recommendedCalories) { this.recommendedCalories = recommendedCalories; }
}
