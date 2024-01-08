package testdb.demo.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import testdb.demo.entities.Teacher;
import testdb.demo.repositories.TeacherRepositories;

public class KafkaListenner {
    @Autowired
    private TeacherRepositories teacherRepositories;

    @KafkaListener(topics = "topic1")
    public void readDataStream(@Payload String record) {
        //MongoDB
        if(record!=null && record.length()>0) {
            Teacher object = null;
            try {
                object = new ObjectMapper().readValue(record, Teacher.class);
                teacherRepositories.save(object);
            }catch(Exception e) {
                e.printStackTrace();
            }
        }}
    }
