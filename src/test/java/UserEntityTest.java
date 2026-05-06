package com.daclink;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserEntityTest {

    @Test
    void getId() {
        UserEntity user = new UserEntity("test", "pass", false);
        user.setId(5);
        assertEquals(5, user.getId());
    }

    @Test
    void setId() {
        UserEntity user = new UserEntity("test", "pass", false);
        user.setId(10);
        assertEquals(10, user.getId());
    }

    @Test
    void getUsername() {
        UserEntity user = new UserEntity ("john", "pass", false);
        assertEquals("john", user.getUsername());
    }

    @Test
    void setUsername() {
        UserEntity user = new UserEntity ("john", "pass", false);
        user.setUsername("estrella");
        assertEquals("estrella", user.getUsername());
    }

    @Test
    void getPassword() {
        UserEntity user = new UserEntity("john", "pass123", false);
        assertEquals("pass123", user.getPassword());
    }

    @Test
    void setPassword() {
        UserEntity user = new UserEntity("john", "oldPassword", false);
        user.setPassword("newPassword");
        assertEquals("newPassword", user.getPassword());

    }

    @Test
    void isAdmin() {
        UserEntity user = new UserEntity("john", "pass123", false);
        assertFalse(user.isAdmin());
        UserEntity Admin = new UserEntity("admin", "adminPassword123", true);
        assertTrue(Admin.isAdmin());

    }

    @Test
    void setAdmin() {
        UserEntity user = new UserEntity("john", "pass123", false);
        user.setAdmin(true);
        assertTrue(user.isAdmin());

        user.setAdmin(false);
        assertFalse(user.isAdmin());
    }

    @Test
    void getTotalScore() {
        UserEntity user = new UserEntity("john", "pass123", false);
        assertEquals(0, user.getTotalScore());

    }

    @Test
    void setTotalScore() {
        UserEntity user = new UserEntity("john", "pass123", false);
        user.setTotalScore(1000);
        assertEquals(1000, user.getTotalScore());

        user.setTotalScore(10);
        assertEquals(10, user.getTotalScore());
    }

    @Test
    void testToString() {
        UserEntity user = new UserEntity("john", "pass123", false);
        user.setId(1);
        user.setTotalScore(200);
        String output = user.toString();
        assertTrue(output.contains("ID: 1"));
        assertTrue(output.contains("john"));
        assertTrue(output.contains("totalScore200"));
    }
}