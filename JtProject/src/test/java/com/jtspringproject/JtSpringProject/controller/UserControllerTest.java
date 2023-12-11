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
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    @Test
    public void testGetUserDetailsWithValidSession() throws Exception {
        // Mocking dependencies
        UserService userService = mock(UserService.class);
        UserController userController = new UserController(userService);

        // Creating a user and session
        User mockUser = new User("mockUsername", "mockPassword");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("currentUsername", "mockUsername");
        session.setAttribute("currentpass", "mockPassword");

        // Mocking userService behavior
        when(userService.checkLogin("mockUsername", "mockPassword")).thenReturn(mockUser);

        // Setting up the MockMvc
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        // Performing the GET request
        mockMvc.perform(get("/user/profile").session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("profile"))
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attribute("user", mockUser));
    }

    @Test
    public void testGetUserDetailsWithMissingSessionAttributes() throws Exception {
        // Mocking dependencies
        UserService userService = mock(UserService.class);
        UserController userController = new UserController(userService);

        // Creating a session without required attributes
        MockHttpSession session = new MockHttpSession();

        // Setting up the MockMvc
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        // Performing the GET request
        mockMvc.perform(get("/user/profile").session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    public void testGetUserDetailsWithInvalidUser() throws Exception {
        // Mocking dependencies
        UserService userService = mock(UserService.class);
        UserController userController = new UserController(userService);

        // Creating a session with valid attributes
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("currentUsername", "mockUsername");
        session.setAttribute("currentpass", "mockPassword");

        // Mocking userService behavior for an invalid user
        when(userService.checkLogin("mockUsername", "mockPassword")).thenReturn(null);

        // Setting up the MockMvc
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        // Performing the GET request
        mockMvc.perform(get("/user/profile").session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    public void testUserLogin() throws Exception {
        // Mocking dependencies
        UserService userService = mock(UserService.class);
        UserController userController = new UserController(userService);

        // Setting up the MockMvc
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        // Performing the GET request
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("userLogin"));
    }

}
