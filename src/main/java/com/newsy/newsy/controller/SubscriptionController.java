package com.newsy.newsy.controller;

import com.newsy.newsy.dto.SubscribeRequest;
import com.newsy.newsy.service.SubscriptionService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
@CrossOrigin(origins = {"${FRONTEND_URL:http://localhost:4200}"})
public class SubscriptionController {

    private final SubscriptionService service;

    @Value("${FRONTEND_URL:http://localhost:4200}")
    private String frontendUrl;

    @PostMapping("/subscribe")
    public ResponseEntity<?> subscribe(@Valid @RequestBody SubscribeRequest req) {
        String res = service.subscribe(req);
        return switch (res) {
            case "verification_sent" -> ResponseEntity.ok(Map.of("status", "ok", "message", "verification_sent"));
            case "already_subscribed" -> ResponseEntity.ok(Map.of("status", "ok", "message", "already_subscribed"));
            case "maximum_subscribers_reached" -> ResponseEntity.status(429).body(Map.of("status", "error", "message", "limit_reached"));
            default -> ResponseEntity.status(500).body(Map.of("status", "error", "message", res));
        };
    }

    @GetMapping("/verify")
    public void verify(@RequestParam("token") String token, HttpServletResponse response) throws IOException {
        String res = service.verify(token);
        if ("verified".equals(res)) {
            response.sendRedirect(frontendUrl + "/subscribed");
        }
    }

    @GetMapping("/unsubscribe")
    public void unsubscribe(@RequestParam("token") String token, HttpServletResponse response) throws IOException {
        String res = service.unsubscribe(token);
        if ("unsubscribed".equals(res)) {
            response.sendRedirect(frontendUrl + "/unsubscribed");
        }
    }
}
