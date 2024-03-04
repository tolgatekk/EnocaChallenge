package org.tolga.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tolga.dto.request.CustomerRequestDto;
import org.tolga.dto.response.CustomerResponseDto;
import org.tolga.service.CustomerService;

import static org.tolga.constant.ApiUrls.*;



@CrossOrigin(maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping(CUSTOMER)
@RequiredArgsConstructor
public class CustomerController {
     private final CustomerService customerService;

    @PostMapping(ADD_CUSTOMER)
    public ResponseEntity<CustomerResponseDto> addCustomer(@RequestBody @Valid CustomerRequestDto dto){
        return ResponseEntity.ok(customerService.addCustomer(dto));



    }



}
