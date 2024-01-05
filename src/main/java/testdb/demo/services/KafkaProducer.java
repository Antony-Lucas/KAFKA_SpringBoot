package testdb.demo.services;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String Topic = "topic1";
    public void sendMessage(String message){
        kafkaTemplate.send(Topic, message);
    }
}
