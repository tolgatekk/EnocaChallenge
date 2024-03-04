package org.tolga.rabbitmq.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.tolga.rabbitmq.model.CartModel;
import org.tolga.rabbitmq.model.CustomerModel;

@Service
public class CartProducer {

    private final RabbitTemplate rabbitTemplate;

    public CartProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    @Value("${rabbitmq.direct-exchange}")
    private String directExchange;

    @Value("${rabbitmq.cart-bindingKey}")
    private String cartBindingKey;
    public void convertAndSend(CartModel cartModel) {
        rabbitTemplate.convertAndSend(directExchange,cartBindingKey,cartModel);

    }
}
