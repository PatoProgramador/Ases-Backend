package com.ases.api.repository;

import com.ases.api.constants.UserRole;
import com.ases.api.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, String> {
    Optional<UserModel> findByIdAndIsDeletedIsFalse(String id);
    Optional<UserModel> findByEmailAndIsDeletedIsFalse(String email);
    List<UserModel> findByRoleAndIsDeletedIsFalse(UserRole role);
}
