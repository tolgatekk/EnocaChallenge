package org.tolga.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Value("${rabbitmq.direct-exchange}")
    private String directExchange;

    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(directExchange);
    }

    @Value("${rabbitmq.register-queue}")
    private String registerQueueName;
    @Value("${rabbitmq.register-bindingKey}")
    private String registerBindingKey;


    @Bean
    Queue registerQueue(){
        return new Queue(registerQueueName);
    }

    @Bean
    public Binding bindingRegister(Queue registerQueue, DirectExchange directExchange){
        return BindingBuilder.bind(registerQueue).to(directExchange).with(registerBindingKey);
    }


    @Value("${rabbitmq.customer-queue}")
    private String customerQueueName;
    @Value("${rabbitmq.customer-bindingKey}")
    private String customerBindingKey;
    @Bean
    Queue customerQueue(){
        return new Queue(customerQueueName);
    }
    @Bean
    public Binding bindingCustomer(Queue customerQueue, DirectExchange directExchange){
        return BindingBuilder.bind(customerQueue).to(directExchange).with(customerBindingKey);
    }


    @Value("${rabbitmq.cart-queue}")
    private String cartQueueName;
    @Value("${rabbitmq.cart-bindingKey}")
    private String cartBindingKey;
    @Bean
    Queue cartQueue(){
        return new Queue(cartQueueName);
    }
    @Bean
    public Binding bindingCart(Queue cartQueue, DirectExchange directExchange){
        return BindingBuilder.bind(cartQueue).to(directExchange).with(cartBindingKey);
    }
}