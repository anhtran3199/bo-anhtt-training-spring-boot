package com.bssd.boffice.application.dto;

import com.bssd.boffice.application.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDto {
    @Pattern(regexp = "^[A-Za-z][A-Za-z0-9_]{7,29}$", message = "Invalid username")
    private String username;

    @Pattern(regexp = "^[A-Za-z][A-Za-z0-9_]{7,29}$", message = "Invalid password")
    private String password;
}
