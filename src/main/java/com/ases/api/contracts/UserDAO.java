package com.ases.api.contracts;

import com.ases.api.constants.UserRole;
import com.ases.api.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    Optional<UserModel> findById(String id);
    Optional<UserModel> findByEmail(String email);
    List<UserModel> findByRole(UserRole role);
    String save(UserModel user);
}
