package org.tolga.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CustomerModel implements Serializable {
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String password;


}
