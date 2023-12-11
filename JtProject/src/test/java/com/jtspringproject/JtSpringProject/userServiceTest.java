package com.jtspringproject.JtSpringProject;

import com.jtspringproject.JtSpringProject.dao.userDao;
import com.jtspringproject.JtSpringProject.models.User;
import com.jtspringproject.JtSpringProject.services.userService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class userServiceTest {

    @Mock
    private userDao userDao;

    @InjectMocks
    private userService service;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUsers() {
        List<User> expectedUsers = Collections.singletonList(new User());

        when(userDao.getAllUsers()).thenReturn(expectedUsers);

        List<User> result = service.getUsers();

        assertEquals(expectedUsers, result);
    }

    @Test
    public void testAddUser() {
        User user = new User();

        when(userDao.saveUser(user)).thenReturn(user);

        User result = service.addUser(user);

        assertEquals(user, result);
    }

    @Test
    public void testUpdateUser() {
        User user = new User();

        when(userDao.saveUser(user)).thenReturn(user);

        User result = service.updateUser(user);

        assertEquals(user, result);
    }

    @Test
    public void testDeleteUser() {
        int userId = 1;

        service.deleteUser(userId);

        verify(userDao, times(1)).deleteUser(userId);
    }

    @Test
    public void testGetUserById() {
        int userId = 1;
        User expectedUser = new User();

        when(userDao.getUserById(userId)).thenReturn(expectedUser);

        User result = service.getUserById(userId);

        assertEquals(expectedUser, result);
    }

}
