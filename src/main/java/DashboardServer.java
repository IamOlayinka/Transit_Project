


import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DashboardServer
 */
@WebServlet("/DashboardServer")
public class DashboardServer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletResponse res;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardServer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
                throws ServletException, IOException {
            
            // Correct use of res (the one passed to the method)
            res.setContentType("text/html");
            PrintWriter out = res.getWriter();
            out.println("<h1>Welcome to Dashboard</h1>");
        }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
