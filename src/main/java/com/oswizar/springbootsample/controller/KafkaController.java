package com.oswizar.springbootsample.controller;

import com.oswizar.springbootsample.message.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kafka")
@RequiredArgsConstructor
public class KafkaController {

    private final KafkaProducerService producerService;

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestParam String topic,
                                              @RequestParam String message) {
        try {
            producerService.sendMessage(topic, message);
            return ResponseEntity.ok("Message sent successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to send message: " + e.getMessage());
        }
    }

    @PostMapping("/send-with-key")
    public ResponseEntity<String> sendMessageWithKey(@RequestParam String topic,
                                                     @RequestParam String key,
                                                     @RequestParam String message) {
        try {
            producerService.sendMessage(topic, key, message);
            return ResponseEntity.ok("Message sent successfully with key: " + key);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to send message: " + e.getMessage());
        }
    }
}

