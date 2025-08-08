package DaoImpl;

import dataaccessLayer.Datasource;
import model.User;
import java.sql.*;

import DTOs.UserDTO;

public class UserDaoImp {
    public boolean registerUser(UserDTO user) {
        String sql = "INSERT INTO users (name, email, password, user_type) VALUES (?, ?, ?, ?)";
        try {
        	Connection conn = Datasource.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getUserType());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace(); 
            return false;
        }
    }

    public UserDTO getUserByEmail(String email) {
    	UserDTO user = null;
    	
        String sql = "SELECT * FROM users WHERE email = ?";
        try  {
        	Connection conn = Datasource.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	user = new UserDTO();
            	user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setUserType(rs.getString("user_type"));
   
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    
    public boolean emailExists(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        try (Connection conn = Datasource.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

	public UserDTO login(String email, String password) {
		// TODO Auto-generated method stub (For the test)
		return null; 
	}

}

