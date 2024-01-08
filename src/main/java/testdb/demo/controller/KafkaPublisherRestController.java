package testdb.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.*;
import testdb.demo.services.DataService;
import testdb.demo.services.KafkaConsumer;
import testdb.demo.services.KafkaProducer;

@RestController
@RequestMapping("/test")
public class KafkaPublisherRestController {
    @Autowired
    private KafkaProducer producer;
    @Autowired
    private KafkaConsumer consumer;
    @Autowired
    private DataService dataService;

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

    @GetMapping(value = "/getteacher", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> findLast30DaysSales(){
        try {
            return new ResponseEntity<String>(dataService.getTeachersNamesAndSubjects(), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}