package com.example.workpraktika.controller;

import com.example.workpraktika.dto.UserDto;
import com.example.workpraktika.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserServiceImpl userServiceImpl;

    @GetMapping("/registration")
    String registrationPage(@ModelAttribute("userDto") UserDto userDto) {
        return "registration";
    }

    @PostMapping("/registration")
    String saveUser(@Valid @ModelAttribute("userDto") UserDto userDto,
                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        if (!userServiceImpl.isUsernameAvailable(userDto.getUsername())) {
            bindingResult.rejectValue("username", "error.username",
                    "Имя пользователя уже занято!");
            return "registration";
        }

        if (!userServiceImpl.isEmailAvailable(userDto.getEmail())) {
            bindingResult.rejectValue("email", "error.email",
                    "Почта уже занята!");
            return "registration";
        }

        userServiceImpl.save(userDto);
        return "redirect:/login";
    }


}
