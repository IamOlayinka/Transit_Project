package Serverlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.IOException;

import Command.EmailReportCommand;
import Command.ReportCommand;
import Command.ReportInvoker;
import SimpleFactory.Report;

import SimpleFactory.ReportFactory;

@WebServlet("/EmailReportServlet")
public class EmailReportServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String type = req.getParameter("type"); // e.g. "fuel", "maintenance", etc.

        try {
            Report report = ReportFactory.createReport(type);
            ReportCommand command = new EmailReportCommand(report, email);

            ReportInvoker invoker = new ReportInvoker();
            invoker.setCommand(command);
            String result = invoker.runCommand();

            resp.setContentType("text/plain");
            resp.getWriter().write(result);

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(500, "Error sending report to email.");
        }
    }
}
