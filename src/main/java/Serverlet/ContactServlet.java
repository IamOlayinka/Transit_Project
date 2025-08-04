package Serverlet;

import Dao.ContactDAO;
import DaoImpl.ContactDAOImpl;
import DTOs.ContactDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/ContactServlet")
public class ContactServlet extends HttpServlet {

    private ContactDAO contactDAO = new ContactDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String message = request.getParameter("message");

        ContactDTO contact = new ContactDTO(name, email, message);

        boolean success = contactDAO.saveMessage(contact);

        if (success) {
            request.setAttribute("msg", "Your message has been sent successfully!");
        } else {
            request.setAttribute("msg", "Oops! Something went wrong. Please try again.");
        }

        request.getRequestDispatcher("homepage.jsp").forward(request, response);
    }
}
