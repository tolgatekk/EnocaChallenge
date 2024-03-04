package org.tolga.service;


import org.springframework.stereotype.Service;
import org.tolga.dto.request.CustomerRequestDto;
import org.tolga.dto.response.CustomerResponseDto;
import org.tolga.exception.CustomerServiceException;
import org.tolga.exception.ErrorType;
import org.tolga.mapper.ICustomerMapper;
import org.tolga.rabbitmq.model.CartModel;
import org.tolga.rabbitmq.model.CustomerModel;
import org.tolga.rabbitmq.model.RegisterModel;
import org.tolga.rabbitmq.producer.CartProducer;
import org.tolga.rabbitmq.producer.CustomerProducer;
import org.tolga.repository.ICustomerRepository;
import org.tolga.repository.entity.Customer;
import org.tolga.utility.ServiceManager;


@Service
public class CustomerService extends ServiceManager<Customer, Long> {

    private final ICustomerRepository customerRepository;
    private final CustomerProducer customerProducer;
    private final CartProducer cartProducer;

    public CustomerService(ICustomerRepository customerRepository, CustomerProducer customerProducer, CartProducer cartProducer) {
        super(customerRepository);
        this.customerRepository = customerRepository;
        this.customerProducer = customerProducer;
        this.cartProducer = cartProducer;
    }

    public CustomerResponseDto addCustomer(CustomerRequestDto dto) {
        if (customerRepository.existsByEmail(dto.getEmail())) {
            throw new CustomerServiceException(ErrorType.EMAIL_ALREADY_EXISTS);
        }
        if (customerRepository.existsByPhoneNumber(dto.getPhoneNumber())) {
            throw new CustomerServiceException(ErrorType.PHONE_NUMBER_ALREADY_EXISTS);
        }

        Customer customer = ICustomerMapper.INSTANCE.customerRequestDtoToCustomer(dto);
        save(customer);

        CustomerModel customerModel=CustomerModel.builder()
                .email(customer.getEmail())
                .name(customer.getName())
                .surname(customer.getSurname())
                .phoneNumber(customer.getPhoneNumber())
                .password(dto.getPassword())
                .build();

        customerProducer.convertAndSend(customerModel);


        return ICustomerMapper.INSTANCE.customerToCustomerResponseDto(customer);
    }

    public void customerRegister(RegisterModel registerModel){
        Customer customer = Customer.builder()
                .authId(registerModel.getAuthId())
                .name(registerModel.getName())
                .surname(registerModel.getSurname())
                .phoneNumber(registerModel.getPhoneNumber())
                .email(registerModel.getEmail())
                .build();
        save(customer);
        CartModel cartModel = CartModel.builder()
                .customerId(customer.getId())
                .build();
        cartProducer.convertAndSend(cartModel);

    }




}
