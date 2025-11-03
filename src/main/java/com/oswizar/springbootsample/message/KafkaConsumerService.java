package com.oswizar.springbootsample.message;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumerService {

    @KafkaListener(topics = "demo-topic", groupId = "${spring.kafka.consumer.group-id}")
//    public void consume(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
    public void consume(ConsumerRecord<String, String> record) {
        log.info("Received message: key={}, value={}, topic={}, partition={}, offset={}",
                record.key(), record.value(), record.topic(), record.partition(), record.offset());

        // 业务逻辑处理
        processMessage(record.value());

        // 手动确认偏移量
//        acknowledgment.acknowledge();
    }

    private void processMessage(String message) {
        // 处理收到的消息
        log.info("Processing message: {}", message);
    }
}

