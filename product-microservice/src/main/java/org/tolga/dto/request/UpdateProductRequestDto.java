package org.tolga.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UpdateProductRequestDto {
    private Long id;

    private String productName;

    private Double price;

    private Integer stock;

    private String description;

    private String photo;

    private String brand;


}
