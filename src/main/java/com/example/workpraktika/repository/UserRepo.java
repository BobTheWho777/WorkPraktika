package com.example.workpraktika.repository;

import com.example.workpraktika.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByUsername(String username);

    List<User> findByUsernameContainingIgnoreCase(String search);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
