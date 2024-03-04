package org.tolga.rabbitmq.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.tolga.rabbitmq.model.CartModel;
import org.tolga.service.CartService;

@Service
@RequiredArgsConstructor
public class CartConsumer  {
    private final CartService cartService;
    @RabbitListener(queues ="${rabbitmq.cart-queue}" )
    public void saveCartFromQueue(CartModel cartModel){
        cartService.saveCart(cartModel);
    }

}
