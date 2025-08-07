package Serverlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import Validator.userValidator;
import model.User;
import DTOs.UserDTO;
import DaoImpl.UserDaoImp;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class RegisterUser extends HttpServlet {
	
    private String hashPassword(String password) {
        try {
            // Generate a random salt
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);
            
            // Create MessageDigest instance for SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            
            // Add password bytes to digest
            md.update(password.getBytes("UTF-8"));
            
            // Get the hash's bytes
            byte[] hashedPassword = md.digest();
            
            // Convert to Base64 string (salt + hash)
            byte[] saltAndHash = new byte[salt.length + hashedPassword.length];
            System.arraycopy(salt, 0, saltAndHash, 0, salt.length);
            System.arraycopy(hashedPassword, 0, saltAndHash, salt.length, hashedPassword.length);
            
            return Base64.getEncoder().encodeToString(saltAndHash);
        } catch (Exception e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
    
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String userType = req.getParameter("role");

        Map<String, String> errors = userValidator.validate(req);

        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            req.setAttribute("name", name);
            req.setAttribute("email", email);
            req.setAttribute("userType", userType);
            req.getRequestDispatcher("register.jsp").forward(req, res);
            return;
        }
  
        String hashedPassword = hashPassword(password);
        
        UserDTO user = new UserDTO();
                user.setName(name);
                user.setEmail(email);
                user.setPassword(hashedPassword);
                user.setUserType(userType);
                
        boolean success = new UserDaoImp().registerUser(user);
        res.sendRedirect(success ? "login.jsp" : "register.jsp?error=1");
    }
}
