package DTOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserDTOTest {

    @Test
    public void testGettersAndSetters() {
        UserDTO user = new UserDTO();

        int id = 101;
        String name = "Alice";
        String email = "alice@example.com";
        String password = "password123";
        String userType = "admin";

        user.setId(id);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setUserType(userType);

        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertEquals(userType, user.getUserType());
    }
}
