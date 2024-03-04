package org.tolga.dto.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AddProductToCartDto {
    private Long customerId;
    private String productName;
    private int quantity;
    private Double price;

}
