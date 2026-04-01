package com.ases.api.controller;

import com.ases.api.constants.UserRole;
import com.ases.api.dtos.UserDTO;
import com.ases.api.dtos.UserUpdateDTO;
import com.ases.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable String userId) {
        UserDTO user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getMyProfile() {
        String email = org.springframework.security.core.context.SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        UserDTO user = userService.getUserByEmail(email);

        return ResponseEntity.ok(user);
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<UserDTO>> getAllUsersByUserRole(@PathVariable UserRole role) {
        List<UserDTO> users = userService.getAllUsersByUserRole(role);
        return ResponseEntity.ok(users);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String userId, @RequestBody UserUpdateDTO userDetails) {
        UserDTO updatedUser = userService.updateUser(userId, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserLogically(@PathVariable String userId) {
        userService.deleteUserLogically(userId);
        return ResponseEntity.noContent().build();
    }
}
