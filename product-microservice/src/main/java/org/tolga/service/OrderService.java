package org.tolga.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.tolga.dto.request.PlaceOrderRequestDto;
import org.tolga.exception.ErrorType;
import org.tolga.exception.OrderServiceException;
import org.tolga.exception.ProductServiceException;
import org.tolga.mapper.IProductMapper;
import org.tolga.repository.IOrderRepository;
import org.tolga.repository.entity.Order;
import org.tolga.repository.entity.OrderItem;
import org.tolga.repository.entity.Product;
import org.tolga.utility.CodeGenerator;
import org.tolga.utility.ServiceManager;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService extends ServiceManager<Order, Long> {

    private final IOrderRepository orderRepository;

    public OrderService(JpaRepository<Order, Long> jpaRepository, IOrderRepository orderRepository) {
        super(jpaRepository);
        this.orderRepository = orderRepository;
    }

    public Order placeOrder(PlaceOrderRequestDto dto) {
      Order order = IProductMapper.INSTANCE.placeOrderRequestDtoToOrder(dto);
        List<OrderItem> orderList = dto.getOrderItems();
        Double totalPrice = 0.0;

        for (OrderItem orderItem : orderList) {
            Double orderItemTotalPrice = orderItem.getPrice() * orderItem.getQuantity();
            totalPrice += orderItemTotalPrice;
            // Double orderPrice = orderItem.getPrice();
            //totalPrice += orderPrice;
        }
        order.setTotalPrice(totalPrice);
    return order;
    }
    public String getOrderByCode(Long id) {
        if (orderRepository.existsById(id)) {
            throw new OrderServiceException(ErrorType.ORDER_NOT_FOUND);
        }
        return "Your Order Code: " + CodeGenerator.generateCode();
    }

    public List<Order> getAllOrdersForCustomer(Long customerId) {
        if (orderRepository.existsByCustomerId(customerId)) {
            throw new OrderServiceException(ErrorType.ORDER_NOT_FOUND);
        }
        return findAll().stream()
                .filter(item -> item.getCustomerId().equals(customerId))
                .collect(Collectors.toList());
    }
}
