package com.jtspringproject.JtSpringProject;

import com.jtspringproject.JtSpringProject.dao.userDao;
import com.jtspringproject.JtSpringProject.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class userDaoTest {

    @Mock
    private SessionFactory sessionFactory;

    @InjectMocks
    private userDao userDao;

    @Mock
    private Session session;

    @Mock
    private Query<User> query;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(sessionFactory.getCurrentSession()).thenReturn(session);
    }

    @Test
    public void testGetAllUsers() {
        List<User> expectedUsers = Collections.singletonList(new User());

        when(session.createQuery(anyString(), eq(User.class))).thenReturn(query);
        when(query.list()).thenReturn(expectedUsers);

        List<User> result = userDao.getAllUsers();

        assertEquals(expectedUsers, result);
    }

    @Test
    public void testSaveUser() {
        User user = new User();

        User result = userDao.saveUser(user);

        assertEquals(user, result);
        verify(session, times(1)).saveOrUpdate(user);
    }

    @Test
    public void testDeleteUser() {
        int userId = 1;
        User user = new User();
        user.setId(userId);

        when(session.get(eq(User.class), anyInt())).thenReturn(user);

        userDao.deleteUser(userId);

        verify(session, times(1)).delete(user);
    }

    @Test
    public void testGetUserById() {
        int userId = 1;
        User expectedUser = new User();
        expectedUser.setId(userId);

        when(session.get(eq(User.class), anyInt())).thenReturn(expectedUser);

        User result = userDao.getUserById(userId);

        assertEquals(expectedUser, result);
    }

    @Test
    public void testGetUser() {
        String username = "testUser";
        String password = "testPassword";
        User expectedUser = new User();

        when(session.createQuery(anyString(), eq(User.class))).thenReturn(query);
        when(query.setParameter(eq("username"), anyString())).thenReturn(query);
        when(query.getSingleResult()).thenReturn(expectedUser);

        User result = userDao.getUser(username, password);

        assertEquals(expectedUser, result);
    }
}
