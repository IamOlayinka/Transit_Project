package Serverlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import Validator.userValidator;

import DTOs.UserDTO;
import DaoImpl.UserDaoImp;
import model.User;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class RegisterUser extends HttpServlet {
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
        
        UserDTO user = new UserDTO();
                user.setName(name);
                user.setEmail(email);
                user.setPassword(password);
                user.setUserType(userType);
                
        boolean success = new UserDaoImp().registerUser(user);
        res.sendRedirect(success ? "login.jsp" : "register.jsp?error=1");
    }
}
