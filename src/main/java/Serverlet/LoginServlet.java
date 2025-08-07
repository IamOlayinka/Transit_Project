package Serverlet;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DTOs.UserDTO;
import DaoImpl.UserDaoImp;
import model.User;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

        // verifying the hashed password when logging
        private boolean verifyPassword(String inputPassword, String storedHashedPassword) {
            try {
                // Decoding the stored hash password
                byte[] saltAndHash = Base64.getDecoder().decode(storedHashedPassword);
                

                byte[] salt = new byte[16];
                System.arraycopy(saltAndHash, 0, salt, 0, 16);

                MessageDigest md = MessageDigest.getInstance("SHA-256");
                md.update(salt);
                md.update(inputPassword.getBytes("UTF-8"));
                byte[] hashedInput = md.digest();
                
                // Extract stored hash 
                byte[] storedHash = new byte[saltAndHash.length - 16];
                System.arraycopy(saltAndHash, 16, storedHash, 0, storedHash.length);
                
                // Compare the hashes
                return MessageDigest.isEqual(hashedInput, storedHash);
            } catch (Exception e) {
                return false;
            }
        }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        UserDTO user = new UserDaoImp().getUserByEmail(email);

        if (user != null && verifyPassword(password, user.getPassword())) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            session.setAttribute("role", user.getUserType());
            res.sendRedirect("index.jsp");
        } else {
            res.sendRedirect("login.jsp?error=1");
        }
    }
}