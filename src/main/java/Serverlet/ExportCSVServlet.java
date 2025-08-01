package Serverlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Command.ExportToCSVCommand;
import Command.ReportCommand;
import Command.ReportInvoker;
import SimpleFactory.Report;
import SimpleFactory.ReportFactory;

/**
 * Servlet implementation class ExportCSVServlet
 */
@WebServlet("/ExportCSVServlet")
public class ExportCSVServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExportCSVServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String type = req.getParameter("type");

        try {
            // Create report from factory
            Report report = ReportFactory.createReport(type);
            String csvContent = report.generate();  // simple plain text for now

            // Set headers to trigger file download
            resp.setContentType("text/csv");
            resp.setHeader("Content-Disposition", "attachment; filename=" + type + "_report.csv");

            // Write CSV content to the response
            resp.getWriter().write(csvContent);

        } catch (Exception e) {
            resp.sendError(500, "Failed to export report: " + e.getMessage());
        }
    }
}
