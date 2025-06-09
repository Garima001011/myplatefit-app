package com.garima.myplatefit.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class ChatService {
    private final WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:11434")
            .build();
    public String chatWithOllama(String userPrompt) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("model", "llama3.2");  // your local model name
        payload.put("prompt", userPrompt);
        payload.put("stream", false);


        Mono<Map> response = webClient.post()
                .uri("/api/generate")
                .bodyValue(payload)
                .retrieve()
                .bodyToMono(Map.class);
        Map result = response.block();
        return (String) result.get("response");
    }
}
