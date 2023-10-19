package com.tahrioussama.amqp;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class RabbitMQConfig {
   private final ConnectionFactory connectionFactory;

   // This is the template that will allow us to send a message to the queue
   @Bean
   AmqpTemplate amqpTemplate() {
       RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
       rabbitTemplate.setMessageConverter(jacksonConverter());
       return rabbitTemplate;
   }

   // This is a listener will allow us to receive the message from the queue using the jacksonConverter()
   @Bean
   public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory() {
       SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
       factory.setConnectionFactory(connectionFactory);
       factory.setMessageConverter(jacksonConverter());
       return factory;
   }

   @Bean
    MessageConverter jacksonConverter() {
       MessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();
       return jackson2JsonMessageConverter;
   }
}