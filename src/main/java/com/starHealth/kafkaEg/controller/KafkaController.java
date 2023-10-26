package com.starHealth.kafkaEg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.starHealth.kafkaEg.producer.MessageProducer;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class KafkaController {
	
	@Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private MessageProducer messageProducer;

    @PostMapping("/send")
    public String sendMessage(@RequestParam("message") String message) {
        messageProducer.sendMessage("my-topic", message);
        return "Message sent: " + message;
    }
    
//    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping("/sendData")
    public ResponseEntity<String> sendTrackingData(@RequestBody String trackingData) {
        // Send the tracking data to the Kafka topic
        kafkaTemplate.send("my-topic", trackingData);
        System.out.println(trackingData);
        return ResponseEntity.ok("Tracking data sent to Kafka topic.");
    }

}
