package DaoImpl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import DaoImpl.UserDaoImp;
import DTOs.UserDTO; 

public class UserDaoImplTest {

    private final UserDaoImp dao = new UserDaoImp();

    @Test
    public void testRegisterUser() {
        UserDTO user = new UserDTO();
        user.setName("JUnit Tester");
        user.setEmail("junit.tester@example.com");
        user.setPassword("test1234");
        user.setUserType("random");

        boolean result = dao.registerUser(user);
        assertTrue(result, "User registration should succeed");
    }
 
    @Test 
    public void testLoginSuccess() {
        String email = "amindu@gmail.com";
        String password = "Amindu";
        UserDTO user = dao.login(email, password);
        assertNotNull(user, "User should be found for valid login");
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
    }

    @Test
    public void testLoginFail() {
        UserDTO user = dao.login("nonexistent@example.com", "wrongpass");
        assertNull(user, "Login should fail with invalid credentials");
    }
}
