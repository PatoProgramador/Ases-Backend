package com.ases.api.dao;

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
        return userRepository.findById(id);
    }

    @Override
    public Optional<UserModel> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public String save(UserModel user) {
        return userRepository.save(user).getId();
    }

    @Override
    public List<UserModel> findAll() {
        return userRepository.findAll();
    }
}
