package com.example.workpraktika.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {
    private String phone;
    @NotEmpty(message = "Почта не может быть пустой!")
    @Pattern(regexp = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$", message = "Некорректный адрес электронной почты")
    private String email;
    @NotEmpty(message = "Логин пользователя не может быть пустым!")
    @Pattern(regexp = "^[A-Za-zА-Яа-я0-9_-][A-Za-zА-Яа-я0-9_-]{6,18}$", message = "Логин пользователя может содержать только буквы кириллицы или латиницы, а также цифры от 0 до 9 и знаки \"-\" и \"_\"")
    @Size(min = 6, max = 18, message = "Логин должен содержать от 6 до 18 символов")
    private String username;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+]).{8,20}$",
            message = "Пароль должен содержать 8-20 символов, включая минимум одну цифру, одну букву в верхнем и нижнем регистре, один спецсимвол (!@#$%^&*()_+)")
    @Size(min = 8, max = 20, message = "Пароль должен быть не короче 8 символов и не длиннее 20")
    private String password;
}