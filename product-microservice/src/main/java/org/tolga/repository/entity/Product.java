package org.tolga.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.tolga.repository.enums.EStatus;


@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@SuperBuilder
@Table(name = "tbl_product")
public class Product extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String productName;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private Integer stock;
    private String description;
    private String photo;
    @Column(nullable = false)
    private String brand;
    @Enumerated(EnumType.STRING)
    private EStatus status;


}
