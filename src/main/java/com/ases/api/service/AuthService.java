package com.ases.api.service;

import com.ases.api.ErrorsHandlers.AlreadyExistsException;
import com.ases.api.contracts.UserDAO;
import com.ases.api.dtos.UserRegistrationDTO;
import com.ases.api.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public String register(UserRegistrationDTO userDto) {
        Optional<UserModel> existingUser = userDAO.findByEmail(userDto.getEmail());
        if (existingUser.isPresent()) {
            throw new AlreadyExistsException("User with this email already exists");
        }
        UserModel userModel = new UserModel(userDto);
        String encodedPassword = passwordEncoder.encode(userModel.getPassword());
        userModel.setPassword(encodedPassword);
        return userDAO.save(userModel);
    }
}
