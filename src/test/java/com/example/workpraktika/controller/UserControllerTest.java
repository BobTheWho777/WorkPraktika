package com.example.workpraktika.controller;

import com.example.workpraktika.impl.UserServiceImpl;
import com.example.workpraktika.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserServiceImpl userService;

    @Mock
    private Model model;

    @InjectMocks
    private UserController userController;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setUsername("admin");
        user.setEmail("admin@example.com");
    }

    @Test
    void userList_noSearch_returnsAllUsers() {
        List<User> users = List.of(user);
        when(userService.findAllUser()).thenReturn(users);

        String view = userController.userList(null, model);

        verify(userService).findAllUser();
        verify(model).addAttribute("user_list", users);
        assertEquals("admin/user_list", view);
    }

    @Test
    void userList_withSearch_returnsFilteredUsers() {
        String search = "admin";
        List<User> filtered = List.of(user);
        when(userService.findByName(search)).thenReturn(filtered);

        String view = userController.userList(search, model);

        verify(userService).findByName(search);
        verify(model).addAttribute("user_list", filtered);
        assertEquals("admin/user_list", view);
    }

    @Test
    void updateUser_addsUserToModel() {
        when(userService.findById(1L)).thenReturn(user);

        String view = userController.updateUser(1L, model);

        verify(userService).findById(1L);
        verify(model).addAttribute("user", user);
        assertEquals("admin/user_update", view);
    }

    @Test
    void saveUpdate_callsServiceAndRedirects() {
        Long id = 1L;
        String username = "admin";
        String email = "admin@example.com";
        String phone = "1234567890";
        String password = "password";
        String role = "ADMIN";

        String view = userController.saveUpdate(id, username, email, phone, password, role);

        verify(userService).updateUserFromForm(id, username, email, phone, password, role);
        assertEquals("redirect:/admin/user/list", view);
    }

    @Test
    void deleteUser_callsServiceAndRedirects() {
        Long id = 1L;

        String view = userController.deleteUser(id);

        verify(userService).deleteUser(id);
        assertEquals("redirect:/admin/user/list", view);
    }
}
