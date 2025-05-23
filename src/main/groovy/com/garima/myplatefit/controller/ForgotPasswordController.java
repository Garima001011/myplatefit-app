package com.garima.myplatefit.controller;

import com.garima.myplatefit.model.PasswordResetToken;
import com.garima.myplatefit.model.User;
import com.garima.myplatefit.repository.PasswordResetTokenRepository;
import com.garima.myplatefit.repository.UserRepository;
import com.garima.myplatefit.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Controller
public class ForgotPasswordController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private EmailService emailService;

    @GetMapping("/forgot-password")
    public String showForgotPasswordForm() {
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String handleForgotPassword(@RequestParam("email") String email, Model model) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            // Create token
            String token = UUID.randomUUID().toString();
            PasswordResetToken resetToken = new PasswordResetToken();
            resetToken.setToken(token);
            resetToken.setUser(user);
            resetToken.setExpiryDate(LocalDateTime.now().plusMinutes(30)); // 30-minute expiry
            tokenRepository.save(resetToken);

            // Compose reset link
            String resetLink = "http://localhost:8080/reset-password?token=" + token;
            emailService.sendResetPasswordEmail(email, resetLink);

            model.addAttribute("message", "Reset link sent to your email.");
        } else {
            model.addAttribute("error", "No account found with that email.");
        }

        return "forgot-password";
    }
}
