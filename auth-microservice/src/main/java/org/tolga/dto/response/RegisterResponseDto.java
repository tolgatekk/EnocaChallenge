package org.tolga.dto.response;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RegisterResponseDto {
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;

}
