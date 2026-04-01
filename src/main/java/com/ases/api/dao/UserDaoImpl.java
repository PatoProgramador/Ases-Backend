package com.ases.api.dao;

import com.ases.api.constants.UserRole;
import com.ases.api.contracts.UserDAO;
import com.ases.api.model.UserModel;
import com.ases.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDAO {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<UserModel> findById(String id) {
        return userRepository.findByIdAndIsDeletedIsFalse(id);
    }

    @Override
    public Optional<UserModel> findByEmail(String email) {
        return userRepository.findByEmailAndIsDeletedIsFalse(email);
    }

    @Override
    public List<UserModel> findByRole(UserRole role) {
        return userRepository.findByRoleAndIsDeletedIsFalse(role);
    }

    @Override
    public String save(UserModel user) {
        return userRepository.save(user).getId();
    }
}
