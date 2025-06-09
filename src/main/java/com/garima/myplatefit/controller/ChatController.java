package com.garima.myplatefit.controller;

import com.garima.myplatefit.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin  // Allow front-end access if needed
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping
    public String chat(@RequestBody String prompt) {
        return chatService.chatWithOllama(prompt);
    }
}
