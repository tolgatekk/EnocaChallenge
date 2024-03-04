package org.tolga.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tolga.repository.entity.CartItem;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UpdateCartRequestDto {
    private  Long id;
    private List<CartItem>cartItems;
    private BigDecimal price;


}
