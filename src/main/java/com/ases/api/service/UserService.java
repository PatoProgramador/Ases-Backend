package com.ases.api.service;

import com.ases.api.ErrorsHandlers.NotFoundException;
import com.ases.api.contracts.UserDAO;
import com.ases.api.dtos.UserDTO;
import com.ases.api.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public UserDTO getUserById(String userId) {
        UserModel user = userDAO.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
        return new UserDTO(user);
    }

    public UserDTO getUserByEmail(String email) {
        UserModel user = userDAO.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found"));
        return new UserDTO(user);
    }
}
