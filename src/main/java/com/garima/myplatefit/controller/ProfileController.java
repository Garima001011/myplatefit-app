package com.garima.myplatefit.controller;

import com.garima.myplatefit.model.User;
import com.garima.myplatefit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/profile")
    public String showProfile(Model model, Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElse(null);
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(
            @RequestParam("name") String name,
            @RequestParam("weight") Double weight,
            @RequestParam("height") Double height,
            @RequestParam("age") Integer age,
            @RequestParam("gender") String gender,
            @RequestParam("exerciseLevel") String exerciseLevel,
            @RequestParam("goal") String goal,
            @RequestParam(value = "croppedImage", required = false) String croppedImage,
            Authentication authentication
    ) throws IOException {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) return "redirect:/login";

        user.setName(name);
        user.setWeight(weight);
        user.setHeight(height);
        user.setAge(age);
        user.setGender(gender);
        user.setExerciseLevel(exerciseLevel);
        user.setGoal(goal);

        if (croppedImage != null && !croppedImage.isEmpty()) {
            String filename = user.getId() + "_profile.png";
            String base64Data = croppedImage.split(",")[1];
            byte[] imageBytes = java.util.Base64.getDecoder().decode(base64Data);
            Path outputPath = Paths.get("profile-uploads", filename);
            Files.write(outputPath, imageBytes);
            user.setProfilePicturePath(filename);
        }

        userRepository.save(user);
        return "redirect:/profile?success";
    }

    @PostMapping("/profile/change-password")
    public String changePassword(
            @RequestParam("currentPassword") String currentPassword,
            @RequestParam("newPassword") String newPassword,
            Authentication authentication,
            Model model
    ) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElse(null);

        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            model.addAttribute("error", "Current password is incorrect.");
            model.addAttribute("user", user);
            return "profile";
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return "redirect:/profile?passwordChanged";
    }
}