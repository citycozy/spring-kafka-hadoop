package com.sku.springkafkahadoop.kafka;

import com.sku.springkafkahadoop.kafka.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaRestController {

    private final KafkaProducerService kafkaProducerService;

    @Autowired
    public KafkaRestController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping("/send-message")
    public void sendMessage(@RequestBody String message) {
        kafkaProducerService.sendMessage(message);
    }

}
