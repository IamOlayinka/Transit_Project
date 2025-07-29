package DaoImpl;

import dataaccessLayer.Datasource;
import model.User;
import java.sql.*;

import DTOs.UserDTO;

public class UserDaoImp {
    public boolean registerUser(UserDTO user) {
        String sql = "INSERT INTO users (name, email, password, user_type) VALUES (?, ?, ?, ?)";
        try {
        	Connection conn = Datasource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getUserType());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // You should log this properly
            return false;
        }
    }

    public UserDTO login(String email, String password) {
    	UserDTO user = new UserDTO();
    	
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        try  {
        	Connection conn = Datasource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	
            	user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setUserType(rs.getString("user_type"));
            	
                return user;
                        
                        
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

