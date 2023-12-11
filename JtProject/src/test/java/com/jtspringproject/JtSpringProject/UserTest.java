package com.jtspringproject.JtSpringProject;

import com.jtspringproject.JtSpringProject.models.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testGetterAndSetter() {
        User user = new User();

        // Set values using setters
        user.setId(1);
        user.setUsername("testUser");
        user.setEmail("test@example.com");
        user.setPassword("testPassword");
        user.setRole("ROLE_USER");
        user.setAddress("Test Address");

        // Verify values using getters
        assertEquals(1, user.getId());
        assertEquals("testUser", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("testPassword", user.getPassword());
        assertEquals("ROLE_USER", user.getRole());
        assertEquals("Test Address", user.getAddress());
    }

    @Test
    public void testEqualsAndHashCode() {
        User user1 = new User(1, "testUser", "test@example.com", "testPassword", "ROLE_USER", "Test Address");
        User user2 = new User(1, "testUser", "test@example.com", "testPassword", "ROLE_USER", "Test Address");
        User user3 = new User(2, "anotherUser", "another@example.com", "anotherPassword", "ROLE_ADMIN", "Another Address");

        // Test equals method
        assertTrue(user1.equals(user2));
        assertFalse(user1.equals(user3));

        // Test hashCode method
        assertEquals(user1.hashCode(), user2.hashCode());
        assertNotEquals(user1.hashCode(), user3.hashCode());
    }

}
