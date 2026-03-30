package com.ases.api.contracts;

import com.ases.api.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    Optional<UserModel> findById(String id);
    Optional<UserModel> findByEmail(String email);
    String save(UserModel user);
    List<UserModel> findAll();
}
