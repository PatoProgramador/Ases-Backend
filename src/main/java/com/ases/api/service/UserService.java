package com.ases.api.service;

import com.ases.api.ErrorsHandlers.NotFoundException;
import com.ases.api.constants.UserRole;
import com.ases.api.contracts.UserDAO;
import com.ases.api.dtos.UserDTO;
import com.ases.api.dtos.UserUpdateDTO;
import com.ases.api.model.UserModel;
import com.ases.api.utils.CustomBeanUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<UserDTO> getAllUsersByUserRole(UserRole role) {
        List<UserModel> users =  userDAO.findByRole(role);
        return users.stream().map(UserDTO::new).toList();
    }

    @Transactional
    public UserDTO updateUser(String userId, UserUpdateDTO userDetails) {
        UserModel user = userDAO.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        CustomBeanUtils.copyNonNullProperties(userDetails, user);

        String updateUserId = userDAO.save(user);
        return getUserById(updateUserId);
    }

    @Transactional
    public void deleteUserLogically(String userId) {
        UserModel user = userDAO.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found to delete"));

        user.setIsDeleted(true);
        userDAO.save(user);
    }
}
