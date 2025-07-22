package Serverlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.UserDAO;
import model.User;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String userType = req.getParameter("user_type");

        User user = new User.UserBuilder()
                .setName(name)
                .setEmail(email)
                .setPassword(password)
                .setUserType(userType)
                .build();

        boolean success = new UserDAO().registerUser(user);
        res.sendRedirect(success ? "login.jsp" : "register.jsp?error=1");
    }
}
