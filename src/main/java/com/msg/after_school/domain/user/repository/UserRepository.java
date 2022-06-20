package com.msg.after_school.domain.user.repository;

import com.msg.after_school.domain.user.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findUserByEmail(String email);
}
