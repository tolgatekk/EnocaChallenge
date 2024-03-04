package org.tolga.rabbitmq.model;

import lombok.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RegisterModel implements Serializable {
    private Long authId;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;


}
