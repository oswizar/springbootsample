package com.oswizar.springbootsample.controller;

import com.oswizar.springbootsample.model.ResponseResult;
import com.oswizar.springbootsample.message.KafkaProducerService;
import lombok.RequiredArgsConstructor;
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
    public ResponseResult sendMessage(@RequestParam String topic,
                                              @RequestParam String message) {
        try {
            producerService.sendMessage(topic, message);
            return ResponseResult.success("消息发送成功", null);
        } catch (Exception e) {
            return ResponseResult.fail(500, "发送消息失败：" + e.getMessage());
        }
    }

    @PostMapping("/send-with-key")
    public ResponseResult sendMessageWithKey(@RequestParam String topic,
                                                     @RequestParam String key,
                                                     @RequestParam String message) {
        try {
            producerService.sendMessage(topic, key, message);
            return ResponseResult.success("消息发送成功，key: " + key, null);
        } catch (Exception e) {
            return ResponseResult.fail(500, "发送消息失败：" + e.getMessage());
        }
    }
}

