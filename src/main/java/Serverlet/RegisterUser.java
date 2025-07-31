package Serverlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTOs.UserDTO;
import DaoImpl.UserDaoImp;
import model.User;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class RegisterUser extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String userType = req.getParameter("role");

        UserDTO user = new UserDTO();
                user.setName(name);
                user.setEmail(email);
                user.setPassword(password);
                user.setUserType(userType);
                
        boolean success = new UserDaoImp().registerUser(user);
        res.sendRedirect(success ? "login.jsp" : "register.jsp?error=1");
    }
}
