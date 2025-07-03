package com.example.workpraktika.controller;

import com.example.workpraktika.dto.UserDto;
import com.example.workpraktika.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private UserServiceImpl userServiceImpl;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private AuthController authController;

    private UserDto userDto;

    @BeforeEach
    void setUp() {
        userDto = new UserDto();
        userDto.setUsername("testuser");
        userDto.setEmail("test@example.com");
        userDto.setPassword("123456");
        userDto.setPhone("1234567890");
    }

    @Test
    void registrationPage_returnsRegistrationView() {
        String view = authController.registrationPage(userDto);

        assertEquals("registration", view);
    }

    @Test
    void saveUser_validInput_redirectsToLogin() {
        when(bindingResult.hasErrors()).thenReturn(false);
        when(userServiceImpl.isUsernameAvailable("testuser")).thenReturn(true);
        when(userServiceImpl.isEmailAvailable("test@example.com")).thenReturn(true);

        String view = authController.saveUser(userDto, bindingResult);

        assertEquals("redirect:/login", view);
        verify(userServiceImpl).save(userDto);
    }

    @Test
    void saveUser_hasValidationErrors_returnsRegistrationView() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String view = authController.saveUser(userDto, bindingResult);

        assertEquals("registration", view);
        verify(userServiceImpl, never()).save(any());
    }

    @Test
    void saveUser_usernameTaken_returnsRegistrationView() {
        when(bindingResult.hasErrors()).thenReturn(false);
        when(userServiceImpl.isUsernameAvailable("testuser")).thenReturn(false);

        String view = authController.saveUser(userDto, bindingResult);

        assertEquals("registration", view);
        verify(bindingResult).rejectValue("username", "error.username", "Имя пользователя уже занято!");
        verify(userServiceImpl, never()).save(any());
    }

    @Test
    void saveUser_emailTaken_returnsRegistrationView() {
        when(bindingResult.hasErrors()).thenReturn(false);
        when(userServiceImpl.isUsernameAvailable("testuser")).thenReturn(true);
        when(userServiceImpl.isEmailAvailable("test@example.com")).thenReturn(false);

        String view = authController.saveUser(userDto, bindingResult);

        assertEquals("registration", view);
        verify(bindingResult).rejectValue("email", "error.email", "Почта уже занята!");
        verify(userServiceImpl, never()).save(any());
    }
}
