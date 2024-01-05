package testdb.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.HtmlUtils;
import reactor.core.publisher.Flux;
import org.springframework.http.MediaType;
import testdb.demo.entities.Greeting;
import testdb.demo.entities.HelloMessage;
import testdb.demo.entities.Teacher;
import testdb.demo.repositories.TeacherRepositories;

import java.net.URI;
import java.time.Duration;
import java.util.List;

@Controller
public class StreamingController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception{
        Thread.sleep(1000);
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
}
