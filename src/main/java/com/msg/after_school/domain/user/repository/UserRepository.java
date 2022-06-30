package com.msg.after_school.domain.user.repository;

import com.msg.after_school.domain.user.data.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findUserByEmail(String email);
    Optional<User> findByRefreshToken(String refreshToken);
}
