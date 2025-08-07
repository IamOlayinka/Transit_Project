package Serverlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Command.GenerateReportCommand;
import Command.ReportCommand;
import Command.ReportInvoker;
import Command.EmailReportCommand;
import SimpleFactory.Report;
import SimpleFactory.ReportFactory;

/**
 * Servlet implementation class GenerateReportServlet
 */
@WebServlet("/GenerateReportServlet")
public class GenerateReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerateReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String type = req.getParameter("type");
        String email = req.getParameter("email");

        try {
            Report report = ReportFactory.createReport(type); 
            
            ReportCommand command = new GenerateReportCommand(report);
            
            if (email != null && !email.trim().isEmpty()) {
                command = new EmailReportCommand(report, email);
            } else {
                command = new GenerateReportCommand(report);
            }
            
            ReportInvoker invoker = new ReportInvoker();
            invoker.setCommand(command);

            String output = invoker.runCommand();

            resp.setContentType("text/plain");
            resp.getWriter().write(output);

        } catch (Exception e) {
            resp.sendError(400, "Invalid report type or internal error");
        }
    }
}
