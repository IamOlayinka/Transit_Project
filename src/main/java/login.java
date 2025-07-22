

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class login
 */
@WebServlet(description = "login api", urlPatterns = { "/login" })
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
	            throws ServletException, IOException {
	        
	        String username = req.getParameter("username");
	        String password = req.getParameter("password");

	        // Dummy authentication logic
	        if ("admin".equals(username) && "1234".equals(password)) {
	            // Success → store user info and redirect to landing page
	            HttpSession session = req.getSession();
	            session.setAttribute("username", username);
	            res.sendRedirect("landing.jsp");
	        } else {
	            // Failure → show error message
	            req.setAttribute("error", "Invalid credentials!");
	            req.getRequestDispatcher("login.jsp").forward(req, res);
	        }
	    }


}
