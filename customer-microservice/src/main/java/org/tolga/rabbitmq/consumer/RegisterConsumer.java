package org.tolga.rabbitmq.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.tolga.rabbitmq.model.RegisterModel;
import org.tolga.repository.entity.Customer;
import org.tolga.service.CustomerService;

@Service
@RequiredArgsConstructor
public class RegisterConsumer {
    private final CustomerService customerService;

    @RabbitListener(queues = "${rabbitmq.register-queue}")
    public void saveCustomerFromQueue(RegisterModel registerModel) {

        customerService.customerRegister(registerModel);
    }
}
