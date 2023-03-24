package io.github.MichaelAnderson19.ResourceServerJwtValidation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LoginRequest {

    private String email;
    private String password;

}
