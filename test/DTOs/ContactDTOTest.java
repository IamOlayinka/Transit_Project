package DTOs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactDTOTest {

    @Test
    public void testConstructorAndGetters() {
        ContactDTO contact = new ContactDTO("Abdullah", "abdullah@email.com", "Hello!");

        assertEquals("Abdullah", contact.getName());
        assertEquals("abdullah@email.com", contact.getEmail());
        assertEquals("Hello!", contact.getMessage());
    }

    @Test
    public void testSetters() {
        ContactDTO contact = new ContactDTO();
        contact.setName("Salami");
        contact.setEmail("salami@email.com");
        contact.setMessage("How are you?");

        assertEquals("Salami", contact.getName());
        assertEquals("salami@email.com", contact.getEmail());
        assertEquals("How are you?", contact.getMessage());
    }
}
