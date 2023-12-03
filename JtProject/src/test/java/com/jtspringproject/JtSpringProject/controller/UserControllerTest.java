package com.jtspringproject.JtSpringProject.controller;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import com.jtspringproject.JtSpringProject.controller.UserController;
import com.jtspringproject.JtSpringProject.models.User;

import com.jtspringproject.JtSpringProject.services.userService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

public class UserControllerTest {

    private UserController userController;
    private userService userServices;
    private RedirectAttributes redirectAttributes;

    @Before
    public void setUp() {
        userServices = mock(userService.class);
        userController = new UserController(userServices);
        redirectAttributes = new RedirectAttributesModelMap();
    }

    @Test
    public void testNewUserRegisterWithBlankUsername() {
        User user = new User();
        user.setUsername(""); // Blank username

        ModelAndView modelAndView = userController.newUseRegister(user, redirectAttributes);

        assertEquals("redirect:/BlankUsername", modelAndView.getViewName());
    }

    @Test
    public void testNewUserRegisterWithExistingUsername() {
        User user = new User();
        user.setUsername("existingUser");

        when(userServices.isUserExists("existingUser")).thenReturn(true);

        ModelAndView modelAndView = userController.newUseRegister(user, redirectAttributes);

        assertEquals("redirect:/ExistsUsernameError", modelAndView.getViewName());
    }

    @Test
    public void testNewUserRegisterWithWeakPassword() {
        User user = new User();
        user.setUsername("newUser");
        user.setPassword("weak"); // Weak password

        ModelAndView modelAndView = userController.newUseRegister(user, redirectAttributes);

        assertEquals("redirect:/PasswordError", modelAndView.getViewName());
    }

    @Test
    public void testNewUserRegisterWithExistingEmail() {
        User user = new User();
        user.setUsername("newUser");
        user.setEmail("existing@email.com");
        user.setPassword("1234abcd");

        when(userServices.isEmailExists(user.getEmail())).thenReturn(true);

        ModelAndView modelAndView = userController.newUseRegister(user, redirectAttributes);

        assertEquals("redirect:/ExistsEmailError", modelAndView.getViewName());
    }

    @Test
    public void testNewUserRegisterSuccess() {
        User user = new User();
        user.setUsername("newUser");
        user.setPassword("StrongPassword123");
        user.setEmail("new@email.com");

        when(userServices.isUserExists(anyString())).thenReturn(false);
        when(userServices.isEmailExists(anyString())).thenReturn(false);

        ModelAndView modelAndView = userController.newUseRegister(user, redirectAttributes);

        assertEquals("redirect:/userLogin1", modelAndView.getViewName());
    }

}
