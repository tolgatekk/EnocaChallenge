package org.tolga.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AuthLoginRequestDto  {
    @Email(message = "Please enter a valid email address.")
    @Size(min = 3, max = 40, message = "Email must be between 3 and 40 characters.")
    private String email;

    @NotBlank(message = "Password field cannot be blank.")
    @Pattern(message = "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.",
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,32}$")
    private String password;

}
