package org.tolga.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.tolga.repository.enums.EGender;
import org.tolga.repository.enums.EStatus;

import java.time.LocalDate;


@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@SuperBuilder
@Table(name = "tbl_customer")
public class Customer extends BaseEntity{

   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Long authId;

    @Column(unique = true, nullable = false, length = 40)
    private String email;

    @Column(unique = true, nullable = false, length = 11)
    private String phoneNumber;

    @Column(nullable = false, length = 40)
    private String name;

    @Column(nullable = false, length = 40)
    private String surname;

    private String address;

    private LocalDate dateOfBirth;

    private String photo;
    @Enumerated(EnumType.STRING)
    private EGender gender;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private EStatus status = EStatus.ACTIVE;

    @Column(unique = true)
    private Long cartId;

}
