package DaoImpl;

import Dao.ContactDAO;
import DTOs.ContactDTO;
import dataaccessLayer.Datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContactDAOImpl implements ContactDAO {

    @Override
    public boolean saveMessage(ContactDTO message) {
        String sql = "INSERT INTO messages (name, email, message) VALUES (?, ?, ?)";

        try (Connection conn = Datasource.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, message.getName());
            ps.setString(2, message.getEmail());
            ps.setString(3, message.getMessage());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}