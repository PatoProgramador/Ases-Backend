package com.ases.api.dtos;

import com.ases.api.constants.UserRole;
import lombok.Data;

@Data
public class UserUpdateDTO {
    private String name;
    private String email;
    private String phone;
    private UserRole role;
}
