package by.betrayal.notificationservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.JsonbMessageConverter;


@Configuration
public class RabbitmqConfig {

    @Value("${spring.rabbitmq.queue.name}")
    private String queueName;

    @Value("${spring.rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${spring.rabbitmq.key}")
    private String routingKey;

    @Bean
    public Queue queue() {
        return new Queue(queueName);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(topicExchange())
                .with(routingKey);
    }

}
