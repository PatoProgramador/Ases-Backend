package com.ases.api.service;

import com.ases.api.contracts.UserDAO;
import com.ases.api.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public UserDTO getUserById(String userId) {
        return userDAO.findById(userId).map(UserDTO::new).orElse(null);
    }
}
