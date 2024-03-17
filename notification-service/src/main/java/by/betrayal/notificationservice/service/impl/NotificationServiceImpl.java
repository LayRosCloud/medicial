package by.betrayal.notificationservice.service.impl;


import by.betrayal.notificationservice.entity.PersonEntity;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
@EnableRabbit
public class NotificationServiceImpl {

    @Value("${spring.mail.username}")
    private String publisherMail;

    @Autowired
    private JavaMailSender sender;

    @RabbitListener(queues = {"${spring.rabbitmq.queue.name}"})
    public void receiveMessage(PersonEntity person) {
        var message = new SimpleMailMessage();
        message.setFrom(publisherMail);
        message.setTo("officialleafall@gmail.com");
        String bodyMessage = String.format("Account be registered on with data \nfirstName %s", person.getFirstName());
        message.setText(bodyMessage);
        message.setSubject("New registered account");

        sender.send(message);
    }
}
