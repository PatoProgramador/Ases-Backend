package com.ases.api.dtos;

import com.ases.api.constants.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegistrationDTO {
    @NotBlank(message = "The user's full name is required")
    private String name;

    @NotBlank(message = "The email is required")
    @Email(message = "The email format is invalid")
    private String email;

    @NotBlank(message = "The password cannot be empty")
    @Size(min = 8, message = "The password must be at least 8 characters long")
    private String password;

    private String phone;

    @NotNull(message = "The user role is required")
    private UserRole role;
}
