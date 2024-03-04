package org.tolga.dto.request;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tolga.repository.enums.EGender;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CustomerRequestDto {
    @NotBlank(message = "Name field cannot be blank.")
    @Size(min = 3, max = 40, message = "Name must be between 3 and 40 characters.")
    private String name;

    @NotBlank(message = "Surname field cannot be blank.")
    @Size(min = 3, max = 40, message = "Surname must be between 3 and 40 characters.")
    private String surname;

    @NotNull(message = "Phone number field cannot be null." )
    @Size(min = 11, max = 11, message = "Phone number must be 11 characters.")
    @Pattern(regexp = "[0-9]+", message = "Phone number must contain only digits.")
    private String phoneNumber;

    @Email(message = "Please enter a valid email address.")
    @Size(min = 3, max = 40, message = "Email must be between 3 and 40 characters.")
    @NotBlank(message = "Email field cannot be blank.")
    private String email;

    private String address;

    private LocalDate dateOfBirth;

    private String photo;
    @Enumerated(EnumType.STRING)
    private EGender gender;

    @NotBlank(message = "Password field cannot be blank.")
    @Pattern(message = "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.",
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,32}$")
    private String password;


}
