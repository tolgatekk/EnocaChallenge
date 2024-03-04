package org.tolga.rabbitmq.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.tolga.rabbitmq.model.CustomerModel;


@Service
public class CustomerProducer {

    private final RabbitTemplate rabbitTemplate;

    public CustomerProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    @Value("${rabbitmq.direct-exchange}")
    private String directExchange;

    @Value("${rabbitmq.customer-bindingKey}")
    private String customerBindingKey;

    public void convertAndSend(CustomerModel customerModel) {
        rabbitTemplate.convertAndSend(directExchange,customerBindingKey,customerModel);

    }
}
