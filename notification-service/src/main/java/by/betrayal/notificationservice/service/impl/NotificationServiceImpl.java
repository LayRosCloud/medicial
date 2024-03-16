package by.betrayal.notificationservice.service.impl;


import by.betrayal.notificationservice.entity.PersonEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.ObjectInputStream;

@Service
@EnableRabbit
public class NotificationServiceImpl {


    @RabbitListener(queues = {"${spring.rabbitmq.queue.name}"})
    public void receiveMessage(PersonEntity person) {
        System.out.println(person.getFirstName());
    }
}
