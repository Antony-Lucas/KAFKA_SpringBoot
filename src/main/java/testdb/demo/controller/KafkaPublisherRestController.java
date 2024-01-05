package testdb.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.*;
import testdb.demo.services.KafkaConsumer;
import testdb.demo.services.KafkaProducer;

@RestController
@RequestMapping("/test")
public class KafkaPublisherRestController {
    @Autowired
    private KafkaProducer producer;
    @Autowired
    private KafkaConsumer consumer;

    @PostMapping
    public void endpoint(@RequestBody String message) {
        producer.sendMessage(message);
    }

    @GetMapping(value = "/getdata")
    public String receivedata(){
        String lastMessage = consumer.getLatestMessage();

        if (lastMessage != null || lastMessage.length() > 0){
            return "Ultima mensagem recebida do kafka" + lastMessage;
        }else{
            return "Sem mensagem";
        }
    }
}