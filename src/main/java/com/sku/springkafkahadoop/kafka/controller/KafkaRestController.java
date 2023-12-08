package com.sku.springkafkahadoop.kafka.controller;

import com.sku.springkafkahadoop.kafka.KafkaProducerService;
import com.sku.springkafkahadoop.kafka.service.KafkaConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class KafkaRestController {

    private final KafkaProducerService kafkaProducerService;
    private final KafkaConsumerService kafkaConsumerService;

    @Autowired
    public KafkaRestController(KafkaProducerService kafkaProducerService, KafkaConsumerService kafkaConsumerService) {
        this.kafkaProducerService = kafkaProducerService;
        this.kafkaConsumerService = kafkaConsumerService;
    }

    @PostMapping("/send-message")
    public void sendMessage(@RequestBody String message) {
        kafkaProducerService.sendMessage(message);
    }

    @GetMapping("/get-messages")
    public List<String> getMessages() {
        return kafkaConsumerService.getMessages();
    }
}
