package org.tolga.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tolga.repository.entity.OrderItem;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PlaceOrderRequestDto {
    private Long customerId;
    private List<OrderItem> orderItems;
}
