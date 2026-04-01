package com.ases.api.model;

import com.ases.api.constants.UserRole;
import com.ases.api.dtos.UserRegistrationDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;
    private String phone;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private Boolean isDeleted;

    public UserModel(UserRegistrationDTO userRegistrationDTO) {
        this.name = userRegistrationDTO.getName();
        this.email = userRegistrationDTO.getEmail();
        this.password = userRegistrationDTO.getPassword();
        this.phone = userRegistrationDTO.getPhone();
        this.role = userRegistrationDTO.getRole();
    }
}
