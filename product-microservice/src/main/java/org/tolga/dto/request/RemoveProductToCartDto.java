package org.tolga.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RemoveProductToCartDto {
    private Long customerId;
    private String productName;
    private int quantity;
    private Double price;
}
