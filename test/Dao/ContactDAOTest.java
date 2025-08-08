package Dao;

import Dao.ContactDAO;
import DaoImpl.ContactDAOImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import DTOs.ContactDTO;

import static org.junit.jupiter.api.Assertions.*;

public class ContactDAOTest {

    private ContactDAO contactDAO;

    @BeforeEach
    public void setUp() {
        contactDAO = new ContactDAOImpl(); 
    }

    @Test
    public void testSaveMessage() {
        ContactDTO message = new ContactDTO();
        message.setName("John Doe");
        message.setEmail("john@example.com");
        message.setMessage("Great job!");

        boolean result = contactDAO.saveMessage(message);
        assertTrue(result, "Message should be saved successfully");
    }
}
