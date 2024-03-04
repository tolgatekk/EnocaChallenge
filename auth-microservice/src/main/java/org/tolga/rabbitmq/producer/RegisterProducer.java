package org.tolga.rabbitmq.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.tolga.rabbitmq.model.RegisterModel;

@Service
public class RegisterProducer {
    private final RabbitTemplate rabbitTemplate;

    public RegisterProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    @Value("${rabbitmq.direct-exchange}")
    private String directExchange;

    @Value("${rabbitmq.register-bindingKey}")
    private String registerBindingKey;

    public void convertAndSend(RegisterModel registerModel) {
        rabbitTemplate.convertAndSend(directExchange,registerBindingKey,registerModel);

    }
}
