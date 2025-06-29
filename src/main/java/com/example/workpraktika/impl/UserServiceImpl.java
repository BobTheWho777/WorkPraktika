package com.example.workpraktika.impl;

import com.example.workpraktika.dto.UserDto;
import com.example.workpraktika.model.Role;
import com.example.workpraktika.model.User;
import com.example.workpraktika.repository.UserRepo;
import com.example.workpraktika.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public List<User> findAllUser() {
        return userRepo.findAll();
    }

    @Override
    public void save(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setPhone(userDto.getPhone());
        user.setEmail(userDto.getEmail());
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
    }

    @Override
    public List<User> findByName(String name) {
        return userRepo.findByUsernameContainingIgnoreCase(name);
    }

    @Override
    public User findById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Пользователь с ID: " + id + " не найден!"));
    }

    @Override
    public void updateUser(User user) {
        User updateUser = userRepo.findById(user.getId()).orElse(null);
        if (updateUser == null) {
            throw new IllegalArgumentException("Пользователь не найден!");
        }
        updateUser.setUsername(user.getUsername());
        updateUser.setPhone(user.getPhone());
        userRepo.save(updateUser);
    }

    @Override
    public void deleteUser(Long id) {
        this.userRepo.deleteById(id);
    }

    @Override
    public boolean isUsernameAvailable(String username) {
        return !userRepo.existsByUsername(username);
    }

    @Override
    public boolean isEmailAvailable(String email) {
        return !userRepo.existsByEmail(email);
    }

    public void updateUserFromForm(Long id, String username, String email, String phone, String password, String roleStr) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Пользователь не найден!"));

        user.setUsername(username);
        user.setEmail(email);
        user.setPhone(phone);

        if (password != null && !password.isEmpty()) {
            user.setPassword(passwordEncoder.encode(password));
        }

        user.setRoles(Collections.singleton(Role.valueOf(roleStr)));
        userRepo.save(user);
    }
}