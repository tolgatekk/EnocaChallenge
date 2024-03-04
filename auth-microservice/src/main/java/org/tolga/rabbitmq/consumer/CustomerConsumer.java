package org.tolga.rabbitmq.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.tolga.rabbitmq.model.CustomerModel;
import org.tolga.repository.entity.Auth;
import org.tolga.service.AuthService;

@Service
@RequiredArgsConstructor
public class CustomerConsumer {
    private final AuthService authService;

    @RabbitListener(queues = "${rabbitmq.customer-queue}")
    public void saveCustomerFromQueue(CustomerModel customerModel) {
        Auth auth = Auth.builder()
                .name(customerModel.getName())
                .surname(customerModel.getSurname())
                .email(customerModel.getEmail())
                .phoneNumber(customerModel.getPhoneNumber())
                .password(customerModel.getPassword())
                .build();
        authService.save(auth);
    }
}
