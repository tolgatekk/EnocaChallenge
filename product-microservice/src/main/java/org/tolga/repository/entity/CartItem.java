package org.tolga.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.security.cert.CertPathBuilder;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
@Builder
public class CartItem {
//name yerine id



    @Column(name = "product_name")
    private String productName;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private Double price;


}