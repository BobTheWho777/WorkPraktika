package com.example.workpraktika.service;

import com.example.workpraktika.dto.UserDto;
import com.example.workpraktika.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    void save(UserDto userDto);

    User findByUsername(String username);

    List<User> findAllUser();

    List<User> findByName(String name);

    User findById(Long id);

    void updateUser(User user);

    void deleteUser(Long id);

    boolean isUsernameAvailable(String username);

    boolean isEmailAvailable(String email);
}

