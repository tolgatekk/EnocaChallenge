package org.tolga.dto.response;





import lombok.*;
import org.tolga.repository.enums.EGender;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CustomerResponseDto {
    private Long id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String address;
    private LocalDate dateOfBirth;
    private String photo;
    private EGender gender;


}
