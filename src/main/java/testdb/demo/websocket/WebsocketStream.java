package testdb.demo.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import testdb.demo.services.DataService;

@Component
@EnableScheduling
public class WebsocketStream {
    @Value("/topic1")
    private String stompTopic;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private DataService service;

    @Scheduled(fixedRate = 2000)
    public void stramCarSalesData(){
        String jsonObject = service.getTeachersNamesAndSubjects();
        messagingTemplate.convertAndSend(stompTopic, jsonObject);
    }
}
