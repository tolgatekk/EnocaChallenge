package org.tolga.dto.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CreateProductRequestDto {
    @NotBlank(message = "ProductName field cannot be blank.")
    private String productName;

    @NotBlank(message = "Price field cannot be blank.")
    private Double price;

    @NotBlank(message = "Stock field cannot be blank.")
    private Integer stock;

    @NotBlank(message = "description field cannot be blank.")
    private String description;

    private String photo;

    @NotBlank(message = "brand field cannot be blank.")
    private String brand;


}
