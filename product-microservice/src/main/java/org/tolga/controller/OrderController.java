package org.tolga.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tolga.dto.request.PlaceOrderRequestDto;
import org.tolga.repository.entity.Order;
import org.tolga.service.OrderService;

import java.util.List;

import static org.tolga.constant.ApiUrls.*;


@RestController
@RequestMapping(ORDER)
@RequiredArgsConstructor
public class OrderController {

    private  final OrderService orderService;

    @PostMapping(PLACE_ORDER)
    public ResponseEntity<Order> placeOrder(@RequestBody PlaceOrderRequestDto dto ){
        return ResponseEntity.ok(orderService.placeOrder(dto));
    }
    @GetMapping(GET_ORDER_FOR_CODE+"/{id}")
    public ResponseEntity<String> getOrderByCode(@PathVariable Long id){
        return ResponseEntity.ok(orderService.getOrderByCode(id));
    }
    @GetMapping(GET_ALL_ORDERS_FOR_CUSTOMER+"/{customerId}")
    public ResponseEntity<List<Order>> getAllOrdersForCustomer(@PathVariable Long customerId){
        return ResponseEntity.ok(orderService.getAllOrdersForCustomer(customerId));
    }



}
