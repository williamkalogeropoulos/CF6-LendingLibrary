package com.williamkalogeropoulos.repository;

import com.williamkalogeropoulos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username); // Find user by username
    List<User> findAll();

    Optional<User> findByEmail(String email);

    Optional<User> findByResetToken(String token);
}
