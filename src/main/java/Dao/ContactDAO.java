package Dao;

import DTOs.ContactDTO;

public interface ContactDAO {
    boolean saveMessage(ContactDTO message);
}
