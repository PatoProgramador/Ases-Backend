package com.ases.api.dtos;

import com.ases.api.constants.UserRole;
import com.ases.api.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private String id;
    private String name;
    private String email;
    private String phone;
    private UserRole role;

    public UserDTO(UserModel userModel) {
        this.id = userModel.getId();
        this.name = userModel.getName();
        this.email = userModel.getEmail();
        this.phone = userModel.getPhone();
        this.role = userModel.getRole();
    }
}
